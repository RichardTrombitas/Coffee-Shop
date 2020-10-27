package com.endava.internship2020.controller;

import com.endava.internship2020.exception.CardException;
import com.endava.internship2020.exception.OrderException;
import com.endava.internship2020.exception.OutOfStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class OrderAdvice {
    @ResponseBody
    @ExceptionHandler(CardException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidCardHandler(CardException e) {
        log.error("Order failed - " + e.getMessage());
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidOrderHandler(OrderException e) {
        log.error("Order failed - " + e.getMessage());
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OutOfStockException.class)
    String outOfStockHandler(OutOfStockException e) {
        log.error("Order failed - not enough supplies!");
        return e.getMessage();
    }
}
