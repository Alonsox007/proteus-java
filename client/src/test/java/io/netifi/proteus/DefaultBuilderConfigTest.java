package io.netifi.proteus;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

@Ignore // can't reload system properties
public class DefaultBuilderConfigTest {
  @Test
  public void testShouldFindSingleSeedAddress() {
    System.setProperty("proteus.client.seedAddresses", "localhost:8001");
    List<SocketAddress> seedAddress = DefaultBuilderConfig.getSeedAddress();
    Assert.assertNotNull(seedAddress);
    Assert.assertEquals(1, seedAddress.size());

    InetSocketAddress address = (InetSocketAddress) seedAddress.get(0);
    Assert.assertEquals(8001, address.getPort());
  }

  @Test
  public void testShouldFindMultipleSeedAddresses() {
    System.setProperty(
        "proteus.client.seedAddresses", "localhost:8001,localhost:8002,localhost:8003");
    List<SocketAddress> seedAddress = DefaultBuilderConfig.getSeedAddress();
    Assert.assertNotNull(seedAddress);
    Assert.assertEquals(3, seedAddress.size());

    InetSocketAddress address = (InetSocketAddress) seedAddress.get(0);
    Assert.assertEquals(8001, address.getPort());
  }

  @Test(expected = IllegalStateException.class)
  public void testShouldThrowExceptionForAddressMissingPort() {
    System.setProperty("proteus.client.seedAddresses", "localhost:8001,localhost,localhost:8003");
    List<SocketAddress> seedAddress = DefaultBuilderConfig.getSeedAddress();
  }

  @Test(expected = IllegalStateException.class)
  public void testShouldThrowExceptionForInvalidAddress() {
    System.setProperty("proteus.client.seedAddresses", "no way im valid");
    List<SocketAddress> seedAddress = DefaultBuilderConfig.getSeedAddress();
  }

  @Test
  public void testShouldReturnNull() {
    List<SocketAddress> seedAddress = DefaultBuilderConfig.getSeedAddress();

    Assert.assertNull(seedAddress);
  }
}
