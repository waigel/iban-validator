package com.waigel.backend.blz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BLZLookupServiceTest {

    @BeforeAll
    static void init() throws IOException {
        BLZLookupService.registerDataSource(new TestBLZDataSource("TEST"));
    }

    @Test
    @DisplayName("Test BLZ data source is successfully registered")
    public void testBLZDataSourceIsSuccessfullyRegistered() {
        final var blzRecord = BLZLookupService.getBLZRecord("TEST", "12345678");
        assert blzRecord != null;
        assertEquals("12345678", blzRecord.blz());
        assertEquals("Test Long Name Bank", blzRecord.bankName());
        assertEquals("Test Bank", blzRecord.shortBankName());
        assertEquals("12345", blzRecord.zipCode());
        assertEquals("Test City", blzRecord.city());
        assertEquals("TESTBIC", blzRecord.bic());

    }

    @Test
    @DisplayName("Test BLZ data source is not registered for country TEST2")
    public void testBLDataSourceIsNotRegisteredAndShouldReturnNull() {
        final var blzRecord = BLZLookupService.getBLZRecord("TEST2", "12345678");
        assertNull(blzRecord);
    }
}
