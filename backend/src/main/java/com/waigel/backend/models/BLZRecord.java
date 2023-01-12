package com.waigel.backend.models;


public record BLZRecord(String blz, String bankName, String shortBankName,
                        String zipCode, String city, String bic) {
}
