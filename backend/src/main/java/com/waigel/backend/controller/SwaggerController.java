package com.waigel.backend.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Hidden
public class SwaggerController {
  @RequestMapping(
      value = {"", "/"},
      method = RequestMethod.GET)
  public void swaggerOnRootLevel(
      final HttpServletResponse httpServletResponse, final HttpServletRequest request) {
    httpServletResponse.setHeader(
        "Location", String.format("%s/swagger-ui/index.html", request.getContextPath()));
    httpServletResponse.setStatus(302);
  }
}
