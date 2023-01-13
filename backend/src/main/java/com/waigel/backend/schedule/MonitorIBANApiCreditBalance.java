package com.waigel.backend.schedule;

import com.waigel.backend.models.ibanapi.IBANApiBalanceResponse;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class MonitorIBANApiCreditBalance {
  private static final Logger logger = LoggerFactory.getLogger(MonitorIBANApiCreditBalance.class);

  @Value("${IBAN_API_KEY:not-set}")
  private transient String ibanApiKey;

  private static final RestTemplate restTemplate = new RestTemplate();
  private static final String API_URL = "https://api.ibanapi.com/v1/balance";

  private final transient AtomicInteger bankBalance;
  private final transient AtomicInteger basicBalance;

  public MonitorIBANApiCreditBalance(final MeterRegistry meterRegistry) {
    this.bankBalance = meterRegistry.gauge("ibanapi.balance.bank", new AtomicInteger(0));
    this.basicBalance = meterRegistry.gauge("ibanapi.balance.basic", new AtomicInteger(0));
  }

  private IBANApiBalanceResponse executeAPIRequest() {
    try {
      final var requestUrl = API_URL + "?api_key=" + ibanApiKey;
      final var response = restTemplate.getForObject(requestUrl, IBANApiBalanceResponse.class);

      if (response == null || response.getResult() != 200 || response.getData() == null) {
        logger.error(
            "MonitorIBANApiCreditBalance: Error while requesting balance information. Status code: "
                + Objects.requireNonNull(response).getResult());
        return null;
      }
      return response;

    } catch (HttpClientErrorException e) {
      logger.error(
          "MonitorIBANApiCreditBalance: Error while requesting balance information. Status code: {}, message: {}",
          e.getStatusCode(),
          e.getMessage());
      return null;
    }
  }

  @Scheduled(cron = "0 */1 * * * *")
  public void monitorIBANApiCreditBalance() {
    final var balance = executeAPIRequest();
    if (balance == null) {
      logger.error("MonitorIBANApiCreditBalance: Skip monitoring because of error");
      return;
    }
    logger.info(
        "MonitorIBANApiCreditBalance: Remaining bank credits: "
            + balance.getData().getBankBalance()
            + " Basic credits: "
            + balance.getData().getBasicBalance());
    bankBalance.set(balance.getData().getBankBalance());
    basicBalance.set(balance.getData().getBasicBalance());
  }
}
