package com.waigel.backend.exceptions;

import com.waigel.backend.iban.IBANCountry;
import org.springframework.http.HttpStatus;

import java.util.List;

public class IBANRegistryException extends ErrorException {
    public IBANRegistryException(final Message message, final IBANCountry country) {
        super(message, List.of(country.getCountryName(), country.countryCode()));

    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
