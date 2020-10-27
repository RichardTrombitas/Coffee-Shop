package com.endava.internship2020.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CoffeeShop {
    @Value("${coffeeShopName}")
    @Getter
    private String name;

    @Getter
    private static BigDecimal profit = BigDecimal.ZERO;

    public static void addProfit(BigDecimal profit) {
        CoffeeShop.profit = CoffeeShop.profit.add(profit);
    }

}

