package com.waigel.backend.exceptions;

import com.waigel.backend.iban.CountryCodeInvalidException;
import com.waigel.backend.iban.IBANParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(IBANParseException.class)
    public ResponseEntity<ErrorResponseBody>  handleIBANParseException(final IBANParseException ex) {
        logger.error("IBANParseException: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponseBody(ex.getCode(), null), ex.getHttpStatus());
    }

    @ExceptionHandler(CountryCodeInvalidException.class)
    public ResponseEntity<ErrorResponseBody> handleCountryCodeInvalidException(final CountryCodeInvalidException ex) {
        logger.error("CountryCodeInvalidException: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponseBody(ex.getCode(), null), ex.getHttpStatus());
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponseBody> handleServerError(final ErrorException ex) {
        return new ResponseEntity<>(ex.getErrorResponseBody(), ex.getHttpStatus());
    }


}
