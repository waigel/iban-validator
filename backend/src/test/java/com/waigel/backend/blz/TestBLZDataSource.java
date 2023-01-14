package com.waigel.backend.blz;

import com.waigel.backend.models.BLZRecord;
import com.waigel.backend.validation.blz.BLZGenericDataSource;
import java.io.IOException;

public class TestBLZDataSource extends BLZGenericDataSource {
  private final String countryCode;

  public TestBLZDataSource(final String countryCode) {
    this.countryCode = countryCode;
  }

  @Override
  public BLZRecord getBLZRecord(String blz) {
    return new BLZRecord(blz, "Test Long Name Bank", "Test Bank", "12345", "Test City", "TESTBIC");
  }

  @Override
  public void init() throws IOException {}

  @Override
  public String getCountryCode() {
    return countryCode;
  }
}
