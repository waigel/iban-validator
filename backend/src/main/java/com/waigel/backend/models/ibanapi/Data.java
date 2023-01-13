package com.waigel.backend.models.ibanapi;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Data {
  @JsonProperty("country_code")
  private String countryCode;

  @JsonProperty("iso_alpha3")
  private String isoAlpha3;

  @JsonProperty("country_name")
  private String countryName;

  @JsonProperty("currency_code")
  private String currencyCode;

  @JsonProperty("sepa_member")
  private String sepaMember;

  private Sepa sepa;

  private String bban;

  @JsonProperty("bank_account")
  private String bankAccount;

  private Bank bank;
}
