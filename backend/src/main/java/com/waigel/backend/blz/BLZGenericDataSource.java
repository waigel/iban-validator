package com.waigel.backend.blz;

import com.waigel.backend.models.BLZRecord;

import java.io.IOException;

public abstract class BLZGenericDataSource {

    abstract public BLZRecord getBLZRecord(String blz);

    abstract public void init() throws IOException;

    abstract public String getCountryCode();

}
