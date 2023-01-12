package com.waigel.backend.exceptions;

import com.waigel.backend.exceptions.ErrorException;
import com.waigel.backend.exceptions.Message;
import org.springframework.http.HttpStatus;

public class CountryCodeInvalidException extends ErrorException {
    public CountryCodeInvalidException(final Message message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
