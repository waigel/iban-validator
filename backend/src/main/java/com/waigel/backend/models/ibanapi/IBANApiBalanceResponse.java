package com.waigel.backend.models.ibanapi;

@lombok.Data
public class IBANApiBalanceResponse {
  private int result;
  private String message;
  private BalanceData data;
}
