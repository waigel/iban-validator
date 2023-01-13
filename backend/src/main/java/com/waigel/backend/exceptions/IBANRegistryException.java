package com.waigel.backend.exceptions;

import com.waigel.backend.iban.IBANCountry;
import java.io.Serial;
import java.util.List;
import org.springframework.http.HttpStatus;

public class IBANRegistryException extends ErrorException {
  @Serial private static final long serialVersionUID = -6551485089553830214L;

  public IBANRegistryException(final Message message, final IBANCountry country) {
    super(message, List.of(country.getCountryName(), country.countryCode()));
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
