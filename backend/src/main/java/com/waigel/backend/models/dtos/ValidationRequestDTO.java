package com.waigel.backend.models.dtos;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationRequestDTO {
    private String iban;

    @Nullable
    private Locale locale;

}
