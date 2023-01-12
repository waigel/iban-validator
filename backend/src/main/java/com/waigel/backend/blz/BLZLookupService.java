package com.waigel.backend.blz;

import com.waigel.backend.models.BLZRecord;

import java.io.IOException;
import java.util.HashMap;

public class BLZLookupService {

    private static final HashMap<String, BLZGenericDataSource> dataSources = new HashMap<>();

    public static void registerDataSource(final BLZGenericDataSource dataSource) throws IOException {
        final String countryCode = dataSource.getCountryCode();
        if (countryCode == null) {
            throw new RuntimeException("Country code must not be null");
        }
        if (dataSources.containsKey(countryCode)) {
            throw new RuntimeException("Country code already registered");
        }
        dataSource.init();
        dataSources.put(dataSource.getCountryCode(), dataSource);
    }

    private static Boolean isCountryCodeSupported(final String countryCode) {
        return dataSources.containsKey(countryCode);
    }

    public static BLZRecord getBLZRecord(String countryCode, String blz) {
        if (!isCountryCodeSupported(countryCode)) {
            return null;
        }
        return dataSources.get(countryCode).getBLZRecord(blz);
    }

}
