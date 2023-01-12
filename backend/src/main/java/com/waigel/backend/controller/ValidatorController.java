package com.waigel.backend.controller;

import com.waigel.backend.dtos.ValidationRequestDTO;
import com.waigel.backend.iban.CountryCodeInvalidException;
import com.waigel.backend.iban.IBAN;
import com.waigel.backend.iban.IBANParseException;
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
    private final Logger logger = LoggerFactory.getLogger(ValidatorController.class);

    @PostMapping("/validate")
    @Operation(summary = "Validate your IBAN")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> handleValidation(@RequestBody final ValidationRequestDTO validationRequestDTO) throws IBANParseException, CountryCodeInvalidException {
        logger.info("Handling validation request for IBAN: {}", validationRequestDTO.getIban());
        final IBAN iban = new IBAN(validationRequestDTO.getIban());
        return ResponseEntity.ok().build();
    }

}
