package com.endava.internship2020.model.beverage;

public class BeverageFactory {
    public static Beverage createBeverage(BeverageType beverageType){
        return switch(beverageType){
            case ESPRESSO -> new Espresso();
            case CAPPUCCINO -> new Cappuccino();
            case COFFEE_LATTE -> new CoffeeLatte();
            case COFFEE_MIEL -> new CoffeeMiel();
            case MACCHIATO -> new Macchiato();
        };
    }
}
