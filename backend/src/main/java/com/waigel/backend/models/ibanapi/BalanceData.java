package com.waigel.backend.models.ibanapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BalanceData {
  @JsonProperty("basic_balance")
  private int basicBalance;

  @JsonProperty("bank_balance")
  private int bankBalance;

  private String expiry_date;
}
