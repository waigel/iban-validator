package com.waigel.backend.models.ibanapi;

import java.util.List;

@lombok.Data
public class IBANApiValidationResponse {
  private int result;

  private String message;

  private List<Validation> validations;

  private int experimental;

  private Data data;
}
