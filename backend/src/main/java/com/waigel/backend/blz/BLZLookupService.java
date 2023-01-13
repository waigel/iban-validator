package com.waigel.backend.blz;

import com.waigel.backend.blz.data.GermanBLZDataSource;
import com.waigel.backend.models.BLZRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class BLZLookupService {
    private static final Logger logger = LoggerFactory.getLogger(BLZLookupService.class);
    private static final HashMap<String, BLZGenericDataSource> dataSources = new HashMap<>();

    public static void registerDataSource(final BLZGenericDataSource dataSource) throws IOException {
        final String countryCode = dataSource.getCountryCode();
        if (countryCode == null) {
            throw new RuntimeException("Country code must not be null");
        }
        if (!dataSources.containsKey(countryCode)) {
            dataSources.remove(countryCode);
            logger.warn("BLZLookupService: Replacing data source for country code {}", countryCode);
        }
        dataSource.init();
        dataSources.putIfAbsent(dataSource.getCountryCode(), dataSource);
        logger.info("BLZLookupService: Registered data source for country code {}", countryCode);
    }

    public static Boolean isCountryCodeSupported(final String countryCode) {
        return dataSources.containsKey(countryCode);
    }

    public static BLZRecord getBLZRecord(String countryCode, String blz) {
        if (!isCountryCodeSupported(countryCode)) {
            logger.warn("BLZLookupService: No data source for country code {} registered.", countryCode);
            return null;
        }
        logger.info("BLZLookupService: Looking up BLZ {} for country code {}", blz, countryCode);
        return dataSources.getOrDefault(countryCode, null).getBLZRecord(blz);
    }

    public static BLZRecord getBLZRecordByIBAN(String iban) {
        if (isCountryCodeSupported("FALLBACK")) {
            logger.info("BLZLookupService: Looking up BLZ for IBAN {}", iban);
            return dataSources.get("FALLBACK").getBLZRecord(iban);
        }
        logger.warn("BLZLookupService: No fallback data source registered");
        return null;
    }

}
