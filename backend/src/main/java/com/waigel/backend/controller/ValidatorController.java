package com.waigel.backend.controller;

import com.waigel.backend.exceptions.CountryCodeInvalidException;
import com.waigel.backend.exceptions.IBANParseException;
import com.waigel.backend.iban.IBAN;
import com.waigel.backend.models.dtos.IBANValidationResponseDTO;
import com.waigel.backend.models.dtos.ValidationRequestDTO;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iban")
@Tag(name = "Validator", description = "Validate your IBAN")
public class ValidatorController {
  private static final Logger logger = LoggerFactory.getLogger(ValidatorController.class);
  private final transient Counter requestCounter;

  public ValidatorController(final MeterRegistry meterRegistry) {
    this.requestCounter =
        Counter.builder("iban.validation.requests")
            .description("Number of requests to the IBAN validation endpoint")
            .register(meterRegistry);
  }

  @PostMapping("/validate")
  @Operation(summary = "Validate your IBAN")
  @CrossOrigin(origins = "*")
  public ResponseEntity<IBANValidationResponseDTO> handleValidation(
      @RequestBody final ValidationRequestDTO validationRequestDTO)
      throws IBANParseException, CountryCodeInvalidException {
    logger.info("Handling validation request for IBAN: {}", validationRequestDTO.getIban());
    requestCounter.increment();
    final var iban = new IBAN(validationRequestDTO.getIban(), validationRequestDTO.getLocale());
    return ResponseEntity.ok(iban.getValidationResponse());
  }
}
