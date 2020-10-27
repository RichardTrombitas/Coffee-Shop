package com.endava.internship2020.service;

import com.endava.internship2020.exception.CardException;
import com.endava.internship2020.model.Card;
import com.endava.internship2020.validator.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private CardValidator cardValidator;

    @Autowired
    public void setCardValidator(CardValidator cardValidator){
        this.cardValidator = cardValidator;
    }

    @Override
    public void validateCard(Card card) throws CardException {
        cardValidator.validateCard(card);
    }
}
