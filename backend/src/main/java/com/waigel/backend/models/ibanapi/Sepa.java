package com.waigel.backend.models.ibanapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sepa {
  @JsonProperty("sepa_credit_transfer")
  private String sepaCreditTransfer;

  @JsonProperty("sepa_direct_debit")
  private String sepaDirectDebit;

  @JsonProperty("sepa_sdd_core")
  private String sepaSddCore;

  @JsonProperty("sepa_b2b")
  private String sepaB2b;

  @JsonProperty("sepa_card_clearing")
  private String sepaCardClearing;
}
