package com.waigel.backend.controller;

import com.waigel.backend.exceptions.CountryCodeInvalidException;
import com.waigel.backend.exceptions.IBANParseException;
import com.waigel.backend.models.dtos.IBANValidationResponseDTO;
import com.waigel.backend.models.dtos.ValidationRequestDTO;
import com.waigel.backend.service.IBANHistoryService;
import com.waigel.backend.validation.iban.IBAN;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
@Tag(name = "Validator", description = "Validate your IBAN and get information about it")
public class ValidatorController {
  private static final Logger logger = LoggerFactory.getLogger(ValidatorController.class);
  private final transient Counter requestCounter;
  private final transient IBANHistoryService ibanHistoryService;

  public ValidatorController(
      final MeterRegistry meterRegistry, final IBANHistoryService ibanHistoryService) {
    this.ibanHistoryService = ibanHistoryService;
    this.requestCounter =
        Counter.builder("iban.validation.requests")
            .description("Number of requests to the IBAN validation endpoint")
            .register(meterRegistry);
  }

  @PostMapping("/validate")
  @Operation(summary = "Validate your IBAN")
  @CrossOrigin(origins = "*")
  public ResponseEntity<IBANValidationResponseDTO> handleValidation(
      @RequestBody final ValidationRequestDTO validationRequestDTO,
      final HttpServletRequest request)
      throws IBANParseException, CountryCodeInvalidException {
    logger.info("Handling validation request for IBAN: {}", validationRequestDTO.getIban());
    requestCounter.increment(); // Increment the request counter for prometheus
    final var iban = new IBAN(validationRequestDTO.getIban(), validationRequestDTO.getLocale());
    ibanHistoryService.add(iban, request.getRemoteAddr());
    return ResponseEntity.ok(iban.getValidationResponse());
  }
}
