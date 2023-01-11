package com.waigel.backend.iban;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IBANParseTest {
    @Test()
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN is too short")
    public void throwExceptionWhenIbanIsToShort() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE8937");
        });
        assertEquals("IBAN is too short (min 15)", exception.getMessage());
    }

    @Test()
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN is too long")
    public void throwExceptionWhenIbanIsToLong() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE893245557884955956594596498589464654945646459");
        });
        assertEquals("IBAN is too long (max 34)", exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN is null")
    public void throwExceptionWhenIbanIsNull() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN(null);
        });
        assertEquals("IBAN is null", exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the check digits are not numeric")
    public void throwExceptionWhenCheckDigitsAreNotNumeric() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DEAB120300000000202051");
        });
        assertEquals("Check digits are not numeric", exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the first check digit are not numeric")
    public void throwExceptionWhenFirstCheckDigitAreNotNumeric() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DEA1120300000000202051");
        });
        assertEquals("Check digits are not numeric", exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the last check digit are not numeric")
    public void throwExceptionWhenLastCheckDigitAreNotNumeric() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE1B120300000000202051");
        });
        assertEquals("Check digits are not numeric", exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN contains special chars")
    public void throwExceptionWhenContainsSpecialChars() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE1B1203000000002_2051");
        });
        assertEquals("IBAN contains special chars. Allowed AZ09", exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser not throws an exception if the IBAN contains '-'")
    public void trowNoExceptionIfContainsLine() {
        assertDoesNotThrow(() -> {
            new IBAN("AT-02-34-00-00-00-02-61-38-00");
        });
    }
}
