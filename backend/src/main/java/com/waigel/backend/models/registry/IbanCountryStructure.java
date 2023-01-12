package com.waigel.backend.models.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IbanCountryStructure {
    private String name;
    @JsonProperty("country_code")
    private String countryCode;
    private Integer length;
    private Flags flags;
    private Embeds embeds;
}
