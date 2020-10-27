package com.endava.internship2020.controller;

import com.endava.internship2020.dto.mapper.CardMapper;
import com.endava.internship2020.exception.CardException;
import com.endava.internship2020.exception.OutOfStockException;
import com.endava.internship2020.dto.PaymentInfo;
import com.endava.internship2020.model.Order;
import com.endava.internship2020.dto.mapper.OrderMapper;
import com.endava.internship2020.service.CoffeeShopService;
import com.endava.internship2020.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    CoffeeShopService coffeeShopService;
    CardService cardService;
    OrderMapper orderMapper;
    CardMapper cardMapper;

    @Autowired
    public OrderController(CoffeeShopService coffeeShopService,
                           CardService cardService,
                           OrderMapper orderMapper,
                           CardMapper cardMapper) {
        this.coffeeShopService = coffeeShopService;
        this.cardService = cardService;
        this.orderMapper = orderMapper;
        this.cardMapper = cardMapper;
    }

    @PostMapping("/pay")
    public ResponseEntity<Order> pay(@RequestBody PaymentInfo paymentInfo)
            throws OutOfStockException, CardException {
        cardService.validateCard(cardMapper.convertToEntity(paymentInfo.getCard()));
        Order order = orderMapper.orderDtoToOrder(paymentInfo.getOrder());
        coffeeShopService.placeOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
