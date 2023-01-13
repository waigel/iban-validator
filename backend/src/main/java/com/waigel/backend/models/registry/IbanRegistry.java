package com.waigel.backend.models.registry;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.util.List;
import lombok.Data;

@Data
public class IbanRegistry {
  @JsonUnwrapped private Meta meta;
  private List<IbanCountryStructure> ibans;
}
