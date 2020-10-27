package com.endava.internship2020.service;

import com.endava.internship2020.exception.OutOfStockException;
import com.endava.internship2020.model.Order;

public interface CoffeeShopService {

    void checkSupplyStock();

    void placeOrder(Order order) throws OutOfStockException;
}
