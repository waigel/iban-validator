package com.waigel.backend.iban;

import com.waigel.backend.blz.BLZLookupService;
import com.waigel.backend.exceptions.CountryCodeInvalidException;
import com.waigel.backend.exceptions.IBANParseException;
import com.waigel.backend.exceptions.IBANRegistryException;
import com.waigel.backend.exceptions.Message;
import com.waigel.backend.models.dtos.IBANValidationResponseDTO;
import com.waigel.backend.models.registry.IbanCountryStructure;
import com.waigel.backend.utils.LatinEncoding;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Locale;

public class IBAN implements Serializable {
    // Static fields
    public static final int MAX_IBAN_LENGTH = 34;
    public static final int MIN_IBAN_LENGTH = 15;

    private Country country;
    private String checkDigits;
    private String content;
    private String ibanInput;
    private IbanCountryStructure ibanCountryStructure;
    private String bankCode;

    private Locale locale = Locale.ENGLISH;

    public IBAN(final String ibanInput, final Locale locale) throws IBANParseException, CountryCodeInvalidException {
        this.locale = locale;
        if (ibanInput == null)
            throw new IBANParseException(Message.IBAN_IS_NULL);
        this.ibanInput = ibanInput.toUpperCase().trim()
                .replace("-", "")
                .replace(" ", "");
        this.validateLength();
        this.validateNotContainsSpecialChars();
        this.parse();
        this.validateCheckNumberIsNumeric();
        this.validateCheckDigits();
        this.validateAndExtractInformationFromIBANRegistry();
    }

    /**
     * Check the length of the IBAN
     *
     * @throws IBANParseException if the length is not between 15 and 34
     */
    private void validateLength() throws IBANParseException {
        if (this.ibanInput.length() == 0)
            throw new IBANParseException(Message.IBAN_INPUT_IS_EMPTY);
        if (ibanInput.length() < MIN_IBAN_LENGTH)
            throw new IBANParseException(Message.IBAN_LENGTH_TOO_SHORT);
        if (ibanInput.length() > MAX_IBAN_LENGTH)
            throw new IBANParseException(Message.IBAN_LENGTH_TOO_LONG);
    }

    /**
     * The check number need to be numeric
     *
     * @throws IBANParseException if the check digits are not numeric
     */
    private void validateCheckNumberIsNumeric() throws IBANParseException {
        final String checkDigits = this.ibanInput.substring(2, 4);
        if (!checkDigits.matches("[0-9]+"))
            throw new IBANParseException(Message.CHECK_DIGITS_NOT_NUMERIC);
    }

    private void validateNotContainsSpecialChars() throws IBANParseException {
        if (!this.ibanInput.matches("[A-Z0-9]+"))
            throw new IBANParseException(Message.IBAN_CONTAINS_SPECIAL_CHARS);
    }

    /**
     * Extract information like the country code
     * and the check number from the IBAN
     */
    private void parse() throws CountryCodeInvalidException {
        this.country = new Country(this.ibanInput.substring(0, 2), this.locale);
        this.checkDigits = this.ibanInput.substring(2, 4);
        this.content = LatinEncoding.replace(this.ibanInput.substring(4));
    }

    private void validateCheckDigits() throws IBANParseException {
        if (this.checkDigits == null)
            throw new InternalError("INTERNAL - Check digits are null. You forget to parse?");

        final String iban = this.content + this.country.getAsAlpha2() + this.checkDigits;
        final BigInteger ibanAsBigInt = new BigInteger(iban);
        //Modulo
        final BigInteger modulo = ibanAsBigInt.mod(new BigInteger("97"));
        //Check if the modulo is 1
        final String checkNumber = modulo.toString();
        if (!checkNumber.equals("1"))
            throw new IBANParseException(Message.CHECK_DIGITS_MISMATCH);
    }

    private void validateAndExtractInformationFromIBANRegistry(){
        final var registry = new IBANRegistry();
        final var ibanCountryStructure = registry.getCountryByCode(this.country.countryCode());
        if (ibanCountryStructure == null){
            throw new IBANParseException(Message.IBAN_COUNTRY_NOT_IN_REGISTRY);
        }
        // Check with correct length for ibanCountry
        if (ibanCountryStructure.getLength()!= 0){
            if (this.ibanInput.length() != ibanCountryStructure.getLength()){
                throw new IBANRegistryException(Message.IBAN_REGISTRY_LENGTH_MISMATCH, this.country);
            }
        }
        if (ibanCountryStructure.getEmbeds() != null && ibanCountryStructure.getEmbeds().getBankCode() != null){
            final var bankCodeStructure = ibanCountryStructure.getEmbeds().getBankCode();
            final var bankCodePosition = bankCodeStructure.getPosition();
            final var bankCodeLength = bankCodeStructure.getLength();
            this.bankCode = this.ibanInput.substring(bankCodePosition, bankCodePosition + bankCodeLength);
        }
        this.ibanCountryStructure = ibanCountryStructure;
    }

    public IBANValidationResponseDTO getValidationResponse() {
        return new IBANValidationResponseDTO(
                this.country.countryCode(),
                this.country.getCountryName(),
                this.ibanCountryStructure.getFlags().isSepaCountry(),
                this.ibanCountryStructure.getFlags().isInSwiftRegistry(),
                this.bankCode,
                BLZLookupService.getBLZRecord(country.countryCode(), this.bankCode)
        );
    }
}
