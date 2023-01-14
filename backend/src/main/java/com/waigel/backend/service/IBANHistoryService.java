package com.waigel.backend.service;

import com.waigel.backend.entities.IBANHistory;
import com.waigel.backend.repositories.IBANHistoryRepository;
import com.waigel.backend.validation.iban.IBAN;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IBANHistoryService {
  private static final Logger logger = LoggerFactory.getLogger(IBANHistoryService.class);
  private final transient IBANHistoryRepository ibanHistoryRepository;

  public IBANHistoryService(final IBANHistoryRepository ibanHistoryRepository) {
    this.ibanHistoryRepository = ibanHistoryRepository;
  }

  private String getRequestIp(final HttpServletRequest request) {
    var ip = request.getHeader("X-FORWARDED-FOR");
    if (ip == null) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public IBANHistory add(@NotNull final IBAN iban, final HttpServletRequest request) {
    logger.info("IBANHistoryService: Registering IBAN in history: {}", iban.toString());
    return ibanHistoryRepository.save(
        new IBANHistory(iban.toString(), this.getRequestIp(request), iban.getBLZ()));
  }
}
