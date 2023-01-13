package com.waigel.backend.models.dtos;

public record IBANValidationResponseDTO(
    String countryCode,
    String countryName,
    Boolean isSepaCountry,
    Boolean isInSwiftRegistry,
    String bankCode,
    com.waigel.backend.models.BLZRecord blzRecord) {}
