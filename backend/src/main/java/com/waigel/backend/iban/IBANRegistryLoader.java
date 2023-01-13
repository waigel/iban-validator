package com.waigel.backend.iban;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.waigel.backend.models.registry.IbanRegistry;
import lombok.Getter;

import java.io.IOException;

@Getter
public class IBANRegistryLoader {
    public static IbanRegistry registry;

    public static void loadRegistryFromFile() throws IOException {
        final var inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("IBANRegistry.yml");
        final var mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        registry = mapper.readValue(inputStream, IbanRegistry.class);
    }
}
