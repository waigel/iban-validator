package com.waigel.backend.blz.data;

import com.waigel.backend.blz.BLZGenericDataSource;
import com.waigel.backend.models.BLZRecord;
import com.waigel.backend.models.ibanapi.IBANApiValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

public class IBANApiFallbackDataSource extends BLZGenericDataSource {

    private final Logger logger = LoggerFactory.getLogger(IBANApiFallbackDataSource.class);
    private final HashMap<String, BLZRecord> cache = new HashMap<>();

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String API_URL = "https://api.ibanapi.com/v1/validate/";
    private final String API_KEY;


    public IBANApiFallbackDataSource(final String apiKey) {
        logger.info("IBANApiFallbackDataSource: Initialized");
        API_KEY = apiKey;
    }

    private BLZRecord executeAPIRequest(final String iban) {

        final var requestUrl = API_URL +
                iban +
                "?api_key=" +
                API_KEY;

        logger.info("IBANApiFallbackDataSource: Requesting information for IBAN: " + iban);

        try {
            final var response = restTemplate.getForObject(requestUrl, IBANApiValidationResponse.class);
            if (response == null || response.getResult() != 200 || response.getData() == null) {
                logger.info("IBANApiFallbackDataSource: No information found for IBAN: " + iban);
                return null;
            }

            final var data = response.getData();
            final var blzRecord = new BLZRecord(
                    data.getBankAccount(),
                    data.getBank().getBankName(),
                    data.getBank().getBankName(),
                    data.getBank().getZip(),
                    data.getBank().getCity(),
                    data.getBank().getBic()
            );
            // Save the result to cache
            logger.info("IBANApiFallbackDataSource: Saving information for IBAN: " + iban + " to cache");
            cache.putIfAbsent(iban, blzRecord);
            return blzRecord;
        } catch (Exception e) {
            logger.error("IBANApiFallbackDataSource: Error while requesting remote information for IBAN: " + iban);
            return null;
        }
    }

    @Override
    public BLZRecord getBLZRecord(final String iban) {
        // First get result from cache, to avoid unnecessary API calls
        if (cache.containsKey(iban)) {
            logger.info("IBANApiFallbackDataSource: Cache hit for IBAN " + iban);
            return cache.getOrDefault(iban, null);
        }
        return executeAPIRequest(iban);
    }

    @Override
    public void init() throws IOException {
        // Do nothing here
        logger.info("IBANApiFallbackDataSource: Initialized //do nothing here");
    }

    @Override
    public String getCountryCode() {
        return "FALLBACK";
    }
}