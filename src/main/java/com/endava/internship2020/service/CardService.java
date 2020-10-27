package com.endava.internship2020.service;

import com.endava.internship2020.exception.CardException;
import com.endava.internship2020.model.Card;

public interface CardService {
    void validateCard(Card card) throws CardException;
}
