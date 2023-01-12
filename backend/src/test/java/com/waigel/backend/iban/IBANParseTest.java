package com.waigel.backend.iban;

import com.waigel.backend.exceptions.CountryCodeInvalidException;
import com.waigel.backend.exceptions.IBANParseException;
import com.waigel.backend.exceptions.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IBANParseTest {

    @BeforeAll
    static void init() throws IOException {
        //Load registry
        IBANRegistryLoader.loadRegistryFromFile();
    }
    @Test()
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN is too short")
    public void throwExceptionWhenIbanIsToShort() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE8937", null);
        });
        assertEquals(Message.IBAN_LENGTH_TOO_SHORT.getCode(), exception.getMessage());
    }

    @Test()
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN is too long")
    public void throwExceptionWhenIbanIsToLong() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE893245557884955956594596498589464654945646459", null);
        });
        assertEquals(Message.IBAN_LENGTH_TOO_LONG.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN is null")
    public void throwExceptionWhenIbanIsNull() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN(null, null);
        });
        assertEquals(Message.IBAN_IS_NULL.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN is empty")
    public void throwExceptionWhenIbanIsEmpty() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("", null);
        });
        assertEquals(Message.IBAN_INPUT_IS_EMPTY.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the check digits are not numeric")
    public void throwExceptionWhenCheckDigitsAreNotNumeric() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DEAB120300000000202051", null);
        });
        assertEquals(Message.CHECK_DIGITS_NOT_NUMERIC.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the first check digit are not numeric")
    public void throwExceptionWhenFirstCheckDigitAreNotNumeric() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DEA1120300000000202051", null);
        });
        assertEquals(Message.CHECK_DIGITS_NOT_NUMERIC.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the last check digit are not numeric")
    public void throwExceptionWhenLastCheckDigitAreNotNumeric() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE1B120300000000202051", null);
        });
        assertEquals(Message.CHECK_DIGITS_NOT_NUMERIC.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN contains special chars")
    public void throwExceptionWhenContainsSpecialChars() {
        final Exception exception = assertThrows(IBANParseException.class, () -> {
            new IBAN("DE1B1203000000002_2051", null);
        });
        assertEquals(Message.IBAN_CONTAINS_SPECIAL_CHARS.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser throws an exception if the IBAN contains invalid country code")
    public void throwExceptionWhenContainsInvalidCountryCode() {
        final Exception exception = assertThrows(CountryCodeInvalidException.class, () -> {
            new IBAN("YY1B12030000000022051", null);
        });
        assertEquals(Message.COUNTRY_CODE_INVALID.getCode(), exception.getMessage());
    }

    @Test
    @DisplayName("Test if the IBAN parser not throws an exception if the IBAN contains '-'")
    public void trowNoExceptionIfContainsLine() {
        assertDoesNotThrow(() -> {
            new IBAN("AT-02-34-00-00-00-02-61-38-00", null);
        });
    }
}
