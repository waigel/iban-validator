package com.waigel.backend;

import com.waigel.backend.blz.BLZLookupService;
import com.waigel.backend.iban.IBANRegistryLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.logging.Logger;

@Configuration
@Profile({ "!test" })
public class StartupBean {

    @Value("${IBAN_API_KEY}")
    private transient String ibanApiKey;

    /**
     * Register the Data sources for the BLZ lookup service.
     *
     * @throws IOException If datasource file not found
     */
    @Bean
    public void registerDataSources() throws IOException {
        BLZLookupService.registerDataSource(new com.waigel.backend.blz.data.GermanBLZDataSource());
        BLZLookupService.registerDataSource(new com.waigel.backend.blz.data.IBANApiFallbackDataSource(ibanApiKey));
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
            Logger.getGlobal().severe("Could not load IBAN registry from file. " +
                    "Please check if the file exists and is readable.");
            throw new RuntimeException(e);
        }
    }


}
