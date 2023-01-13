package com.waigel.backend.models.ibanapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bank {
        @JsonProperty("bank_name")
        private String bankName;

        private String phone;

        private String address;

        private String bic;

        private String city;

        private String state;

        private String zip;

}
