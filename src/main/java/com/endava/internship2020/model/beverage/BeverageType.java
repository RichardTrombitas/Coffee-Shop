package com.endava.internship2020.model.beverage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BeverageType {
    ESPRESSO("Espresso"),
    CAPPUCCINO("Cappuccino"),
    COFFEE_LATTE("Coffee Latte"),
    COFFEE_MIEL("Coffee Miel"),
    MACCHIATO("Macchiato");

    @Getter
    private final String name;
}
