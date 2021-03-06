package io.netifi.proteus.rsocket.transport;

import io.netifi.proteus.rsocket.WeightedRSocket;
import io.rsocket.DuplexConnection;
import io.rsocket.transport.ClientTransport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;

import java.net.InetSocketAddress;

public class WeightedClientTransportSupplierTest {
  @Test
  public void testShouldDecrementActiveCountOnComplete() {

    MonoProcessor<Void> onClose = MonoProcessor.create();
    DuplexConnection duplexConnection = Mockito.mock(DuplexConnection.class);
    Mockito.when(duplexConnection.onClose()).thenReturn(onClose);

    ClientTransport transport = Mockito.mock(ClientTransport.class);
    Mockito.when(transport.connect()).thenReturn(Mono.just(duplexConnection));

    WeightedClientTransportSupplier supplier =
        new WeightedClientTransportSupplier(
            InetSocketAddress.createUnresolved("localhost", 8081), address -> transport);

    DirectProcessor<WeightedRSocket> p = DirectProcessor.create();
    DuplexConnection block = supplier.apply(p).get().connect().block();

    int i = supplier.activeConnections();

    Assert.assertEquals(1, i);

    onClose.onComplete();
    block.onClose().block();

    i = supplier.activeConnections();

    Assert.assertEquals(0, i);
  }
}
