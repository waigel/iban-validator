package com.waigel.backend.blz;

import com.waigel.backend.models.BLZRecord;
import java.io.IOException;

public abstract class BLZGenericDataSource {

  public abstract BLZRecord getBLZRecord(String blz);

  public abstract void init() throws IOException;

  public abstract String getCountryCode();
}
