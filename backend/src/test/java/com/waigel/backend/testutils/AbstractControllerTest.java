package com.waigel.backend.testutils;

import com.waigel.backend.service.IBANHistoryService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureMockMvc
public class AbstractControllerTest {

  @MockBean public IBANHistoryService ibanHistoryService;
}
