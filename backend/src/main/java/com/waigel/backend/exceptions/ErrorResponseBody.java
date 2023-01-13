package com.waigel.backend.exceptions;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

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
