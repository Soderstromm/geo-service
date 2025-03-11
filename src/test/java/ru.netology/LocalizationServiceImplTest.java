package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    private final LocalizationService service = new LocalizationServiceImpl();

    @Test
    void testLocaleForRussia() {
        String result = service.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    void testLocaleForUSA() {
        String result = service.locale(Country.USA);
        assertEquals("Welcome", result);
    }

    @Test
    void testLocaleForGermany() {
        String result = service.locale(Country.GERMANY);
        assertEquals("Welcome", result);
    }

    @Test
    void testLocaleForBrazil() {
        String result = service.locale(Country.BRAZIL);
        assertEquals("Welcome", result);
    }

}
