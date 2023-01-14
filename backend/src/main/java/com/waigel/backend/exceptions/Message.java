package com.waigel.backend.exceptions;

import com.waigel.backend.validation.iban.IBAN;
import java.util.Locale;

public enum Message {
  IBAN_LENGTH_TOO_SHORT(String.format("IBAN is too short (min %d)", IBAN.MIN_IBAN_LENGTH)),
  IBAN_LENGTH_TOO_LONG(String.format("IBAN is too long (max %d)", IBAN.MAX_IBAN_LENGTH)),
  IBAN_IS_NULL("IBAN is null"),
  CHECK_DIGITS_NOT_NUMERIC("Check digits are not numeric"),
  COUNTRY_CODE_INVALID("Country code is invalid"),
  IBAN_CONTAINS_SPECIAL_CHARS("IBAN contains special chars. Allowed AZ09"),
  CHECK_DIGITS_MISMATCH("Check digits mismatch"),
  IBAN_INPUT_IS_EMPTY("IBAN input is empty"),
  IBAN_COUNTRY_NOT_IN_REGISTRY("IBAN country not in registry"),
  IBAN_REGISTRY_LENGTH_MISMATCH("IBAN registry length mismatch");

  private final String message;

  Message(final String consoleMessage) {
    this.message = consoleMessage;
  }

  public String getConsoleMessage() {
    return this.message;
  }

  public String getCode() {
    return name().toUpperCase(Locale.ENGLISH);
  }
}
