package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.geo.*;
import ru.netology.i18n.*;
import ru.netology.entity.*;
import ru.netology.sender.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Tests {
    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private MessageSenderImpl messageSender;

    @Test
    void testRussianSender() {
        String ipAddress = "172.16.0.1";
        Map<String, String> headers = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        when(geoService.byIp(ipAddress)).thenReturn(
                new Location("Moscow", Country.RUSSIA, "Street", 1)
        );
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Выполнение
        String result = messageSender.send(headers);

        // Проверка
        assertEquals("Добро пожаловать", result);
        verify(geoService).byIp(ipAddress);
        verify(localizationService, times(2)).locale(Country.RUSSIA);
    }

    @Test
    void testAmericanSender() {
        String ipAddress = "96.12.34.56";
        Map<String, String> headers = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        when(geoService.byIp(ipAddress)).thenReturn(
                new Location("New York", Country.USA, "Main St", 123)
        );
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        // Выполнение
        String result = messageSender.send(headers);

        // Проверка
        assertEquals("Welcome", result);
        verify(geoService).byIp(ipAddress);
        verify(localizationService, times(2)).locale(Country.USA);
    }
}

