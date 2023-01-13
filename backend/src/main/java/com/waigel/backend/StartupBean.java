package com.waigel.backend;

import com.waigel.backend.blz.BLZLookupService;
import com.waigel.backend.iban.IBANRegistryLoader;
import io.micrometer.core.instrument.MeterRegistry;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"!test"})
public class StartupBean {

  @Value("${IBAN_API_KEY:not-set}")
  private transient String ibanApiKey;

  private final transient MeterRegistry meterRegistry;

  public StartupBean(final MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }

  /**
   * Register the Data sources for the BLZ lookup service.
   *
   * @throws IOException If datasource file not found
   */
  @Bean
  public void registerDataSources() throws IOException {
    BLZLookupService.registerDataSource(new com.waigel.backend.blz.data.GermanBLZDataSource());
    BLZLookupService.registerDataSource(
        new com.waigel.backend.blz.data.IBANApiFallbackDataSource(ibanApiKey, meterRegistry));
  }

  /**
   * Load the IBAN registry.
   *
   * @throws IOException If registry file not found
   */
  @Bean
  public void registerIBANRegistry() throws IOException {
    try {
      IBANRegistryLoader.loadRegistryFromFile();
    } catch (IOException e) {
      Logger.getGlobal()
          .severe(
              "Could not load IBAN registry from file. "
                  + "Please check if the file exists and is readable.");
      throw new RuntimeException(e);
    }
  }
}
