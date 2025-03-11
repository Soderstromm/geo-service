package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {

    private final GeoService geoService = new GeoServiceImpl();

    @Test
    void testMOSCOW_IP() {
        Location result = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        assertNotNull(result);
        assertEquals(Country.RUSSIA, result.getCountry());
        assertEquals("Moscow", result.getCity());
        assertEquals("Lenina", result.getStreet());
        assertEquals(15, result.getBuiling());
    }
    @Test
    void testNEW_YORK_IP() {
        Location result = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        assertNotNull(result);
        assertEquals(Country.USA, result.getCountry());
        assertEquals("New York", result.getCity());
        assertEquals(" 10th Avenue", result.getStreet());
        assertEquals(32, result.getBuiling());
    }

    @Test
    void testRussianIP() {
        String ipAddress = "172.16.0.1";
        Location result = geoService.byIp(ipAddress);
        assertNotNull(result);
        assertEquals(Country.RUSSIA, result.getCountry());
        assertEquals("Moscow", result.getCity());
        assertNull(result.getStreet());
        assertEquals(0, result.getBuiling());
    }

    @Test
    void testAmericanIP() {
        String ipAddress = "96.12.34.56";
        Location result = geoService.byIp(ipAddress);
        assertNotNull(result);
        assertEquals(Country.USA, result.getCountry());
        assertEquals("New York", result.getCity());
        assertNull(result.getStreet());
        assertEquals(0, result.getBuiling());
    }

    @Test
    void testLocalhost() {
        Location result = geoService.byIp(GeoServiceImpl.LOCALHOST);
        assertNotNull(result);
        assertNull(result.getCountry());
        assertNull(result.getCity());
        assertNull(result.getStreet());
        assertEquals(0, result.getBuiling());
    }
}
