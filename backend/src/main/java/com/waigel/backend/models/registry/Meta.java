package com.waigel.backend.models.registry;

import lombok.Data;

@Data
public class Meta {
  private String iban_registry_version;
  private String last_update;
}
