package com.waigel.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waigel.backend.blz.BLZLookupService;
import com.waigel.backend.blz.TestBLZDataSource;
import com.waigel.backend.iban.IBANRegistryLoader;
import com.waigel.backend.models.dtos.ValidationRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeAll
    public static void init() throws IOException {
        IBANRegistryLoader.loadRegistryFromFile();
        BLZLookupService.registerDataSource(new TestBLZDataSource("DE"));
    }

    @Test
    @Description("Test validation controller works correctly for valid IBAN")
    public void testValidationControllerForCorrectIBAN() throws Exception {

        final var jsonBody = objectMapper.writeValueAsString(new ValidationRequestDTO(
                "ST68 0001 0001 0051 8453 1011 2",
                Locale.ENGLISH
        ));
        this.mockMvc.perform(post("/iban/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"countryCode\":\"ST\",\"countryName\":\"SÃ£o TomÃ© & PrÃ\u00ADncipe\"," +
                                "\"isSepaCountry\":false,\"isInSwiftRegistry\":true,\"bankCode\":\"0001\"," +
                                "\"blzRecord\":null}"));
    }

    @Test
    @Description("Test validation controller works correctly for valid IBAN and BLZ record is returned")
    public void testValidationControllerForCorrectIBANWithBLZRecord() throws Exception {
        final var jsonBody = objectMapper.writeValueAsString(new ValidationRequestDTO(
                "DE-02-12-03-00-00-00-00 20 2  0  51",
                Locale.ENGLISH
        ));
        this.mockMvc.perform(post("/iban/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string("" +
                        "{\"countryCode\":\"DE\",\"countryName\":\"Germany\",\"isSepaCountry\":true," +
                        "\"isInSwiftRegistry\":true,\"bankCode\":\"12030000\",\"blzRecord\":{\"blz\":\"12030000\"," +
                        "\"bankName\":\"Test Long Name Bank\",\"shortBankName\":\"" +
                        "Test Bank\",\"zipCode\":\"12345\",\"city\":\"Test City\",\"bic\":\"TESTBIC\"}}"));
    }

    @Test
    @Description("Test validation controller returns bad request if IBAN is invalid")
    public void testValidationControllerReturnBadRequestForInvalidIBAN() throws Exception {
        final var jsonBody = objectMapper.writeValueAsString(new ValidationRequestDTO(
                "DE-02-1*2-03-00-00-00-00 202??051",
                Locale.ENGLISH
        ));
        this.mockMvc.perform(post("/iban/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        "{\"code\":\"IBAN_CONTAINS_SPECIAL_CHARS\",\"params\":null}"));
    }
}
