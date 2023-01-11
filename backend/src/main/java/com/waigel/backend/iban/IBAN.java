package com.waigel.backend.iban;

import com.waigel.backend.exceptions.Message;
import com.waigel.backend.utils.LatinEncoding;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class IBAN implements Serializable {

    // Static fields
    public static final int MAX_IBAN_LENGTH = 34;
    public static final int MIN_IBAN_LENGTH = 15;


    private CountryCode countryCode;
    private String checkDigits;
    private String content;
    private String ibanInput;

    public IBAN(final String ibanInput) throws IBANParseException, CountryCodeInvalidException {
        if (ibanInput == null)
            throw new IBANParseException(Message.IBAN_IS_NULL);
        this.ibanInput = ibanInput.toUpperCase().trim().replace("-", "");
        this.validateLength();
        this.validateNotContainsSpecialChars();
        this.validateCheckNumberIsNumeric();
        this.parse();
        this.validateCheckDigits();
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
        this.countryCode = new CountryCode(this.ibanInput.substring(0, 2));
        this.checkDigits = this.ibanInput.substring(2, 4);
        this.content = LatinEncoding.replace(this.ibanInput.substring(4));
    }

    private void validateCheckDigits() throws IBANParseException {
        if (this.checkDigits == null)
            throw new InternalError("INTERNAL - Check digits are null. You forget to parse?");
        final String iban = this.content + this.countryCode.getAsAlpha2() + this.checkDigits;
        //Bigint
        final BigInteger ibanAsBigInt = new BigInteger(iban);
        //Modulo
        final BigInteger modulo = ibanAsBigInt.mod(new BigInteger("97"));
        //Check#
        final String checkNumber = modulo.toString();
        if (!checkNumber.equals("1"))
            throw new IBANParseException(Message.CHECK_DIGITS_MISMATCH);
    }

}
