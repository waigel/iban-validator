package com.waigel.backend.models.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Embeds {
  @JsonProperty("bank_code")
  private BankCodeStructure bankCode;

  @JsonProperty("branch_code")
  private BranchCodeStructure branchCode;
}
