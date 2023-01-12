package com.waigel.backend.blz.data;

import com.waigel.backend.blz.BLZGenericDataSource;
import com.waigel.backend.iban.IBANRegistryLoader;
import com.waigel.backend.models.BLZRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class GermanBLZDataSource extends BLZGenericDataSource {

    private final HashMap<String, BLZRecord> blzMap = new HashMap<>();

    @Override
    public String getCountryCode() {
        return "DE";
    }

    @Override
    public void init() throws IOException {
        final var inputStream = GermanBLZDataSource.class.getClassLoader().getResourceAsStream("blz-de.csv");
        final String[] lines = new String(Objects.requireNonNull(inputStream).readAllBytes()).split(System.lineSeparator());
        for (final String line : lines) {
            String[] keyValue = line.split(",");
            //remove quotes
            for (int i = 0; i < keyValue.length; i++) {
                keyValue[i] = keyValue[i].replace("\"", "");
            }
            blzMap.putIfAbsent(keyValue[0], new BLZRecord(keyValue[0],
                    keyValue[2], keyValue[5], keyValue[3], keyValue[4], keyValue[7]));
        }


    }

    @Override
    public BLZRecord getBLZRecord(String blz) {
        return blzMap.get(blz);
    }


}
