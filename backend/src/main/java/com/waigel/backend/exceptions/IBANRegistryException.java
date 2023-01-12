package com.waigel.backend.exceptions;

import com.waigel.backend.iban.Country;
import org.springframework.http.HttpStatus;

import java.util.List;

public class IBANRegistryException extends ErrorException {
    public IBANRegistryException(final Message message, final Country country) {
        super(message, List.of(country.getCountryName(), country.countryCode()));

    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
