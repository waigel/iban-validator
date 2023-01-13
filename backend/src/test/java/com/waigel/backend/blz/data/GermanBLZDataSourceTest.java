package com.waigel.backend.blz.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GermanBLZDataSourceTest {

  private static GermanBLZDataSource dataSource;

  @BeforeAll
  public static void init() {
    dataSource = new GermanBLZDataSource();
  }

  @Test
  @DisplayName("Test German BLZ data source is successfully init and no exception thrown.")
  public void testGermanBLZDataSourceDataInit() throws IOException {
    dataSource.init();
  }

  @Test
  @DisplayName("Test German BLZ data source works correctly for valid BLZ")
  public void testGermanBLZDataSourceWorksCorrectlyForValidBLZ() throws IOException {
    dataSource.init();
    final var blzRecord = dataSource.getBLZRecord("10010010");
    assert blzRecord != null;
    assertEquals("10010010", blzRecord.blz());
    assertEquals("Postbank Ndl der Deutsche Bank", blzRecord.bankName());
    assertEquals("Postbank Ndl Deutsche Bank", blzRecord.shortBankName());
    assertEquals("10559", blzRecord.zipCode());
    assertEquals("Berlin", blzRecord.city());
    assertEquals("PBNKDEFFXXX", blzRecord.bic());
  }
}
