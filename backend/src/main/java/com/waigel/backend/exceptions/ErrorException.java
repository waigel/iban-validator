package com.waigel.backend.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public abstract class ErrorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1264424628442265651L;
    private List<Serializable> params = null;
    private final String code;

    public ErrorException(final Message message, final List<Serializable> params) {
        super(message.getCode());
        this.code = message.getCode();
        this.params = params;
    }

    public ErrorException(final Message message) {
        this(message, null);
    }

    public ErrorException(final String code, final List<Serializable> params) {
        super(code);
        this.code = code;
        this.params = params;
    }

    public ErrorResponseBody getErrorResponseBody() {
        return new ErrorResponseBody(this.code, params);
    }

    public abstract HttpStatus getHttpStatus();

}
