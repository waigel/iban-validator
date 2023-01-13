package com.waigel.backend.models.dtos;

import jakarta.annotation.Nullable;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationRequestDTO {
  private String iban;

  @Nullable private Locale locale;
}
