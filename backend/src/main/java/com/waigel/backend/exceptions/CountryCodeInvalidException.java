package com.waigel.backend.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class CountryCodeInvalidException extends ErrorException {
    @Serial
    private static final long serialVersionUID = -855692827755752554L;

    public CountryCodeInvalidException(final Message message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
