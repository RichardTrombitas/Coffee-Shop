package com.endava.internship2020.util;

import com.endava.internship2020.service.CoffeeShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckSupplyStockRunnable implements Runnable{

    CoffeeShopService coffeeShopService;

    @Autowired
    public CheckSupplyStockRunnable(CoffeeShopService coffeeShopService){
        this.coffeeShopService = coffeeShopService;
    }

    @Override
    public void run() {
        System.out.println("\n--------------- SCHEDULED TASK ----------------");
        coffeeShopService.checkSupplyStock();
        System.out.println("\n-----------------------------------------------");
    }
}
