package com.waigel.backend.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ErrorResponseBody {
    private final String code;
    private List<Serializable> params = null;

    public ErrorResponseBody(final String code, final List<Serializable> params) {
        this.code = code;
        this.params = params;
    }

    public ErrorResponseBody(final String code) {
        this.code = code;
    }

}
