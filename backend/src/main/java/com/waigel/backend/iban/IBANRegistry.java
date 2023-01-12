package com.waigel.backend.iban;

import com.waigel.backend.models.registry.IbanCountryStructure;
import com.waigel.backend.models.registry.IbanRegistry;
import lombok.Getter;

@Getter
public class IBANRegistry {
    private final IbanRegistry registry = IBANRegistryLoader.registry;

    public IbanCountryStructure getCountryByCode(final String countryCode) {
        return this.registry.getIbans().stream()
                .filter(c -> c.getCountryCode().equals(countryCode))
                .findFirst()
                .orElse(null);
    }

}
