package com.waigel.backend;

import com.waigel.backend.blz.BLZLookupService;
import com.waigel.backend.iban.IBANRegistryLoader;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.Logger;

@OpenAPIDefinition(
        info = @Info(
                title = "IBAN Validator API",
                version = "1.0.o",
                description = "With this API you can validate your IBAN and fetch additional information about the Bank and account behind it.",
                contact = @Contact(url = "https://iban.waigel.com", name = "Johannes Waigel", email = "johannes@waigel.me")
        )
)
@SpringBootApplication
public class IBANValidatorBackendApplication {


    /**
     * Register the Data sources for the BLZ lookup service.
     * @throws IOException If datasource file not found
     */
    public static void registerDataSources() throws IOException {
        BLZLookupService.registerDataSource(new com.waigel.backend.blz.data.GermanBLZDataSource());
    }

    public static void registerIBANRegistry() throws IOException {
        // Load IBAN registry once by start of this application
        try {
            IBANRegistryLoader.loadRegistryFromFile();
        } catch (IOException e) {
            Logger.getGlobal().severe("Could not load IBAN registry from file. " +
                    "Please check if the file exists and is readable.");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        registerIBANRegistry();
        registerDataSources();

        SpringApplication.run(IBANValidatorBackendApplication.class, args);
    }

}
