package com.waigel.backend.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class IBANParseException extends ErrorException {
    @Serial
    private static final long serialVersionUID = -5426171517281228684L;

    public IBANParseException(final Message message) {
        super(message);

    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
