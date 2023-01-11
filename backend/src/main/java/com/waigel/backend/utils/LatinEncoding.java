package com.waigel.backend.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Utils to encode latin chars in a string to the numbers. This is required for the country code
 * and any letter in the bank account.
 */
public class LatinEncoding {
    private static final List<String> lookupChars = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public static String replace(final String inputString) {
        final String[] inputStringArray = inputString.split("");
        final StringBuilder outputString = new StringBuilder();
        for (final String s : inputStringArray) {
            if (!s.matches("[0-9]+")) {
                outputString.append(lookupChars.indexOf(s) + 10);
            } else {
                outputString.append(s);
            }
        }
        return outputString.toString();
    }
}
