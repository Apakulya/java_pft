package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTest {

  @Test
  public  void testMyIP(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap().getGeoIP("178.162.22.159");
    assertEquals(geoIP.getCountryCode(),"RUS");
  }
}
