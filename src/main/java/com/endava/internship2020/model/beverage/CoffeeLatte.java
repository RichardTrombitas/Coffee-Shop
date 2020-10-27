package com.endava.internship2020.model.beverage;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.ingredient.coffeeBase.EspressoBase;
import com.endava.internship2020.model.ingredient.topping.MilkFoam;
import com.endava.internship2020.model.ingredient.topping.SteamedMilk;

public class CoffeeLatte extends Beverage {
    public CoffeeLatte(){
        super(BeverageType.COFFEE_LATTE.getName(),
                new Recipe(BeverageType.COFFEE_LATTE.getName(), new EspressoBase(1)));
        addTopping(new SteamedMilk(1.5));
        addTopping(new MilkFoam(1));
    }
}
