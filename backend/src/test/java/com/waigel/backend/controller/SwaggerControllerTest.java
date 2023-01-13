package com.waigel.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SwaggerControllerTest {
  @Autowired private transient MockMvc mockMvc;

  @Test
  @Description("Test swagger controller returns 302")
  public void testSwaggerControllerReturnsCorrectLocationHeader() throws Exception {
    this.mockMvc
        .perform(get("/"))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "/swagger-ui/index.html"));
  }
}
