package com.endava.internship2020.validator;

import com.endava.internship2020.exception.CardException;
import com.endava.internship2020.model.Card;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class CardValidator {

    public void validateCard(Card card) throws CardException {
        validateCardHolder(card.getCardHolder());
        validateCvv(card.getCvv());
        validateExpiryDate(card.getExpiryDate());
        validateCardNumber(card.getCardNumber());
    }

    private void validateCardNumber(String cardNumber) throws CardException {
        if(cardNumber == null)
            throw new CardException("Card number was not provided!");

        int length = cardNumber.length();

        if(length < 8 || length > 19)
            throw new CardException("Invalid card number length!");

        int checkDigit = Character.getNumericValue(cardNumber.charAt(length - 1));
        int sum = 0;
        int parity = (length - 2) % 2;
        for (int i = length - 2; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            sum += i % 2 == parity ? (digit * 2 > 9 ? digit * 2 - 9 : digit * 2) : digit;
        }

        if(!((sum * 9) % 10 == checkDigit))
            throw new CardException("Invalid card number!");
    }

    private void validateExpiryDate(YearMonth expiryDate) throws CardException {
        if(expiryDate == null)
            throw new CardException("Expiry date was not provided!");

        if(YearMonth.now().isAfter(expiryDate))
            throw new CardException("The card is expired!");
    }

    private void validateCvv(Integer cvv) throws CardException {
        if(cvv == null)
            throw new CardException("CVV was not provided!");

        if(cvv < 100 || cvv > 9999)
            throw new CardException("Invalid cvv!");
    }

    private void validateCardHolder(String cardHolder) throws CardException {
        if(cardHolder == null)
            throw new CardException("Card holder was not provided!");

        if(cardHolder.isEmpty())
            throw new CardException("Invalid card holder name!");
    }
}
