package com.endava.internship2020.model.beverage;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.ingredient.coffeeBase.EspressoBase;
import com.endava.internship2020.model.ingredient.topping.MilkFoam;
import com.endava.internship2020.model.ingredient.topping.SteamedMilk;

public class Cappuccino extends Beverage {
    public Cappuccino(){
        super(BeverageType.CAPPUCCINO.getName(),
                new Recipe(BeverageType.CAPPUCCINO.getName(), new EspressoBase(1)));
        addTopping(new SteamedMilk(1));
        addTopping(new MilkFoam(2));
    }
}
