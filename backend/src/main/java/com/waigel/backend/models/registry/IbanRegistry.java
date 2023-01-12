package com.waigel.backend.models.registry;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.List;

@Data
public class IbanRegistry {
    @JsonUnwrapped
    private Meta meta;
    private List<IbanCountryStructure> ibans;
}

