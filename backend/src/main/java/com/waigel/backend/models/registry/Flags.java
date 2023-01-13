package com.waigel.backend.models.registry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flags {
    @JsonProperty("sepa_country")
    private Boolean sepaCountry;
    @JsonProperty("in_swift_registry")
    private Boolean inSwiftRegistry;

    public Boolean isSepaCountry() {
        return sepaCountry;
    }

    public Boolean isInSwiftRegistry() {
        return inSwiftRegistry;
    }
}
