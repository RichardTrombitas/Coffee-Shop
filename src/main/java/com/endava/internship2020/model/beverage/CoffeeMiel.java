package com.endava.internship2020.model.beverage;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.ingredient.coffeeBase.BlackCoffeeBase;
import com.endava.internship2020.model.ingredient.topping.Cinnamon;
import com.endava.internship2020.model.ingredient.topping.Honey;
import com.endava.internship2020.model.ingredient.topping.SteamedMilk;

public class CoffeeMiel extends Beverage {
    public CoffeeMiel(){
        super(BeverageType.COFFEE_MIEL.getName(),
                new Recipe(BeverageType.COFFEE_MIEL.getName(), new BlackCoffeeBase(2)));
        addTopping(new SteamedMilk(1));
        addTopping(new Honey(0.5));
        addTopping(new Cinnamon(0.4));
    }
}
