package com.endava.internship2020.dto.mapper;

import com.endava.internship2020.dto.CardDto;
import com.endava.internship2020.exception.CardException;
import com.endava.internship2020.model.Card;
import org.mapstruct.Mapper;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card convertToEntity(CardDto cardDto);

    default YearMonth stringToYearMonth(String date) throws CardException {
        try {
            return YearMonth.parse(date, DateTimeFormatter.ofPattern("MM[.][ ][/][-]yy"));
        } catch (DateTimeException e){
            throw new CardException(e.getMessage());
        } catch (NullPointerException e){
            throw new CardException("Expiry date was not provided!");
        }
    }
}
