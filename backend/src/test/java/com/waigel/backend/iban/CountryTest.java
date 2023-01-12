package com.waigel.backend.iban;

import com.waigel.backend.exceptions.CountryCodeInvalidException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryTest {

    private static final HashMap<String, Integer> testData = new HashMap<>();

    @BeforeAll
    static void init() throws IOException {
        //Read test data from test resources folder
        final Path path = Paths.get("src", "test", "resources", "countryCodeAlpha2Test.csv");
        final String[] lines = Files.readString(path).split(System.lineSeparator());
        for (final String line : lines) {
            final String[] keyValue = line.split(",");
            testData.put(keyValue[0], Integer.parseInt(keyValue[1]));
        }
    }

    @Test()
    @DisplayName("Country code 'DE' should be convert correctly")
    public void convertCountryCodeDECorrectly() throws CountryCodeInvalidException {
        final var cc = new Country("DE", null);
        assertEquals(1314, cc.getAsAlpha2());
    }

    @Test()
    @DisplayName("Test should convert all country codes correctly")
    public void convertAllCountryCodesCorrectly() throws CountryCodeInvalidException {
        for (final var entry : testData.entrySet()) {
            assertEquals(entry.getValue(), new Country(entry.getKey(), null).getAsAlpha2());
        }
    }
}
