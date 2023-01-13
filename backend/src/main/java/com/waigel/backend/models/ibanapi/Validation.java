package com.waigel.backend.models.ibanapi;

import lombok.Data;

@Data
public class Validation {
    private int result;
    private String message;
}