package com.waigel.backend.iban;

import com.waigel.backend.exceptions.Message;
import com.waigel.backend.utils.LatinEncoding;

import java.util.Arrays;
import java.util.Locale;

public class CountryCode {
    private final String countryCode;

    private String[] avaibleCountryCodes = Locale.getISOCountries();

    public CountryCode(final String countryCode) throws CountryCodeInvalidException {
        if (!Arrays.asList(avaibleCountryCodes).contains(countryCode))
            throw new CountryCodeInvalidException(Message.COUNTRY_CODE_INVALID);
        this.countryCode = countryCode;
    }

    public Integer getAsAlpha2() {
        return Integer.parseInt(LatinEncoding.replace(countryCode));
    }
}
