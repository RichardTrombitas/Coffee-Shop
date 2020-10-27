package com.endava.internship2020.model;

import lombok.Data;

import java.time.YearMonth;

@Data
public class Card {
    private String cardNumber;
    private String cardHolder;
    private YearMonth expiryDate;
    private Integer cvv;
}
