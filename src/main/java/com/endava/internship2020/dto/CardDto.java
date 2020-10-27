package com.endava.internship2020.dto;

import lombok.Data;

@Data
public class CardDto {
    private final String cardNumber;
    private final String cardHolder;
    private final String expiryDate;
    private final Integer cvv;
}
