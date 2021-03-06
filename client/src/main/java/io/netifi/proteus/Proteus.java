package io.netifi.proteus;

import io.netifi.proteus.rsocket.ProteusSocket;
import io.netifi.proteus.rsocket.RequestHandlingRSocket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.ssl.OpenSsl;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.opentracing.Tracer;
import io.rsocket.Closeable;
import io.rsocket.transport.ClientTransport;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.ipc.netty.tcp.TcpClient;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

/** This is where the magic happens */
public class Proteus implements Closeable {
  private static final Logger logger = LoggerFactory.getLogger(Proteus.class);
  private static final ConcurrentHashMap<String, Proteus> PROTEUS = new ConcurrentHashMap<>();

  static {
    // Set the Java DNS cache to 60 seconds
    java.security.Security.setProperty("networkaddress.cache.ttl", "60");
  }

  private final long accesskey;
  private final String fromGroup;
  private final DestinationNameFactory destinationNameFactory;
  private final ProteusBrokerService brokerService;
  private MonoProcessor<Void> onClose;
  private RequestHandlingRSocket requestHandlingRSocket;

  private Proteus(
      long accessKey,
      String group,
      ByteBuf accessToken,
      boolean keepalive,
      long tickPeriodSeconds,
      long ackTimeoutSeconds,
      int missedAcks,
      DestinationNameFactory destinationNameFactory,
      List<SocketAddress> seedAddresses,
      Function<SocketAddress, ClientTransport> clientTransportFactory,
      int poolSize,
      Supplier<Tracer> tracerSupplier) {
    this.accesskey = accessKey;
    this.onClose = MonoProcessor.create();
    this.fromGroup = group;
    this.requestHandlingRSocket = new RequestHandlingRSocket();
    this.destinationNameFactory = destinationNameFactory;
    this.brokerService =
        new DefaultProteusBrokerService(
            seedAddresses,
            requestHandlingRSocket,
            fromGroup,
            destinationNameFactory,
            clientTransportFactory,
            poolSize,
            keepalive,
            tickPeriodSeconds,
            ackTimeoutSeconds,
            missedAcks,
            accessKey,
            accessToken,
            tracerSupplier.get());
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public void dispose() {
    requestHandlingRSocket.dispose();
    onClose.onComplete();
  }

  @Override
  public boolean isDisposed() {
    return onClose.isDisposed();
  }

  @Override
  public Mono<Void> onClose() {
    return onClose;
  }

  public Proteus addService(ProteusService service) {
    requestHandlingRSocket.addService(service);
    return this;
  }

  public ProteusSocket destination(String destination, String group) {
    return brokerService.destination(destination, group);
  }

  public ProteusSocket group(String group) {
    return brokerService.group(group);
  }

  public long getAccesskey() {
    return accesskey;
  }

  public String getGroupName() {
    return fromGroup;
  }

  public String getDestination() {
    return destinationNameFactory.rootName();
  }

  public static class Builder {
    private String host = DefaultBuilderConfig.getHost();
    private Integer port = DefaultBuilderConfig.getPort();
    private List<SocketAddress> seedAddresses = DefaultBuilderConfig.getSeedAddress();
    private Long accessKey = DefaultBuilderConfig.getAccessKey();
    private String group = DefaultBuilderConfig.getGroup();
    private String destination = DefaultBuilderConfig.getDestination();
    private String accessToken = DefaultBuilderConfig.getAccessToken();
    private byte[] accessTokenBytes = new byte[20];
    private boolean keepalive = DefaultBuilderConfig.getKeepAlive();
    private long tickPeriodSeconds = DefaultBuilderConfig.getTickPeriodSeconds();
    private long ackTimeoutSeconds = DefaultBuilderConfig.getAckTimeoutSeconds();
    private int missedAcks = DefaultBuilderConfig.getMissedAcks();
    private DestinationNameFactory destinationNameFactory;

    private Function<SocketAddress, ClientTransport> clientTransportFactory = null;
    private int poolSize = Runtime.getRuntime().availableProcessors();
    private Supplier<Tracer> tracerSupplier = () -> null;

    public Builder clientTransportFactory(
        Function<SocketAddress, ClientTransport> clientTransportFactory) {
      this.clientTransportFactory = clientTransportFactory;
      return this;
    }

    public Builder poolSize(int poolSize) {
      this.poolSize = poolSize;
      return this;
    }

    public Builder keepalive(boolean useKeepAlive) {
      this.keepalive = useKeepAlive;
      return this;
    }

    public Builder tickPeriodSeconds(long tickPeriodSeconds) {
      this.tickPeriodSeconds = tickPeriodSeconds;
      return this;
    }

    public Builder ackTimeoutSeconds(long ackTimeoutSeconds) {
      this.ackTimeoutSeconds = ackTimeoutSeconds;
      return this;
    }

    public Builder missedAcks(int missedAcks) {
      this.missedAcks = missedAcks;
      return this;
    }

    public Builder host(String host) {
      this.host = host;
      return this;
    }

    public Builder port(int port) {
      this.port = port;
      return this;
    }

    public Builder tracerSupplier(Supplier<Tracer> tracerSupplier) {
      this.tracerSupplier = tracerSupplier;
      return this;
    }

    public Builder seedAddresses(Collection<SocketAddress> addresses) {
      if (addresses instanceof List) {
        this.seedAddresses = (List<SocketAddress>) addresses;
      } else {
        this.seedAddresses = new ArrayList<>(addresses);
      }

      return this;
    }

    public Builder seedAddresses(SocketAddress address, SocketAddress... addresses) {
      List<SocketAddress> list = new ArrayList<>();
      list.add(address);

      if (addresses != null) {
        list.addAll(Arrays.asList(addresses));
      }

      return seedAddresses(list);
    }

    public Builder accessKey(long accessKey) {
      this.accessKey = accessKey;
      return this;
    }

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder group(String group) {
      this.group = group;
      return this;
    }

    public Builder destination(String destination) {
      this.destination = destination;
      return this;
    }

    public Builder destinationNameFactory(DestinationNameFactory destinationNameFactory) {
      this.destinationNameFactory = destinationNameFactory;
      return this;
    }

    public Proteus build() {
      Objects.requireNonNull(accessKey, "account key is required");
      Objects.requireNonNull(accessToken, "account token is required");
      Objects.requireNonNull(group, "group is required");

      if (clientTransportFactory == null) {
        logger.info("Client transport factory not provided; using TCP transport.");
        try {
          final SslProvider sslProvider;
          if (OpenSsl.isAvailable()) {
            logger.info("Native SSL provider is available; will use native provider.");
            sslProvider = SslProvider.OPENSSL_REFCNT;
          } else {
            logger.info("Native SSL provider not available; will use JDK SSL provider.");
            sslProvider = SslProvider.JDK;
          }
          final SslContext sslContext =
              SslContextBuilder.forClient()
                  .trustManager(InsecureTrustManagerFactory.INSTANCE)
                  .sslProvider(sslProvider)
                  .build();
          clientTransportFactory = address -> {
            TcpClient client =
                TcpClient.create(
                    opts ->
                        opts.connectAddress(() -> address)
                            .sslContext(sslContext));
            return TcpClientTransport.create(client);
          };
        } catch (Exception sslException) {
          throw Exceptions.bubble(sslException);
        }
      }

      this.accessTokenBytes = Base64.getDecoder().decode(accessToken);

      if (destinationNameFactory == null) {
        if (destination == null) {
          destination = UUID.randomUUID().toString();
        }

        destinationNameFactory = DestinationNameFactory.from(destination, new AtomicInteger());
      }

      List<SocketAddress> socketAddresses;
      if (seedAddresses == null) {
        Objects.requireNonNull(host, "host is required");
        Objects.requireNonNull(port, "port is required");
        socketAddresses = Collections.singletonList(InetSocketAddress.createUnresolved(host, port));
      } else {
        socketAddresses = seedAddresses;
      }

      logger.info("registering with netifi with group {}, and destination {}", group, destination);

      String proteusKey = accessKey + group + destination;

      return PROTEUS.computeIfAbsent(
          proteusKey,
          _k -> {
            Proteus proteus =
                new Proteus(
                    accessKey,
                    group,
                    Unpooled.wrappedBuffer(accessTokenBytes),
                    keepalive,
                    tickPeriodSeconds,
                    ackTimeoutSeconds,
                    missedAcks,
                    destinationNameFactory,
                    socketAddresses,
                    clientTransportFactory,
                    poolSize,
                    tracerSupplier);
            proteus.onClose.doFinally(s -> PROTEUS.remove(proteusKey)).subscribe();

            return proteus;
          });
    }
  }
}
