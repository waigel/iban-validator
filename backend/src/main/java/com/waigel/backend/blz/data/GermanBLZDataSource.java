package com.waigel.backend.blz.data;

import com.waigel.backend.blz.BLZGenericDataSource;
import com.waigel.backend.models.BLZRecord;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GermanBLZDataSource extends BLZGenericDataSource {
  private static final Logger logger = LoggerFactory.getLogger(GermanBLZDataSource.class);

  private final transient HashMap<String, BLZRecord> blzMap = new HashMap<>();

  @Override
  public String getCountryCode() {
    return "DE";
  }

  @Override
  public void init() throws IOException {
    final var inputStream =
        Thread.currentThread().getContextClassLoader().getResourceAsStream("blz-de.csv");
    final String[] lines =
        new String(Objects.requireNonNull(inputStream).readAllBytes())
            .split(System.lineSeparator());
    for (final String line : lines) {
      String[] keyValue = line.split(",");
      // remove quotes
      for (int i = 0; i < keyValue.length; i++) {
        keyValue[i] = keyValue[i].replace("\"", "");
      }
      blzMap.putIfAbsent(
          keyValue[0],
          new BLZRecord(
              keyValue[0], keyValue[2], keyValue[5], keyValue[3], keyValue[4], keyValue[7]));
    }
    logger.info("GermanBLZDataSource: Loaded {} BLZ records", blzMap.size());
  }

  @Override
  public BLZRecord getBLZRecord(String blz) {
    logger.info("GermanBLZDataSource: Looking up BLZ {}", blz);
    return blzMap.getOrDefault(blz, null);
  }
}
