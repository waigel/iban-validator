package com.waigel.backend.exceptions;

import java.io.Serial;
import org.springframework.http.HttpStatus;

public class CountryCodeInvalidException extends ErrorException {
  @Serial private static final long serialVersionUID = -855692827755752554L;

  public CountryCodeInvalidException(final Message message) {
    super(message);
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
