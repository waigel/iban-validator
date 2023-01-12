package com.waigel.backend.iban;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.waigel.backend.models.registry.IbanRegistry;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class IBANRegistryLoader {
    public static IbanRegistry registry;

    public static void loadRegistryFromFile() throws IOException {
     //   final Path path = Paths.get("src", "main", "resources", "IBANRegistry.yml");
        final var inputStream = IBANRegistryLoader.class.getClassLoader().getResourceAsStream("IBANRegistry.yml");
        final var mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        registry = mapper.readValue(inputStream, IbanRegistry.class);
    }
}
