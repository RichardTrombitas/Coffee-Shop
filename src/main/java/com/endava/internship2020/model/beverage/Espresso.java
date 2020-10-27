package com.endava.internship2020.model.beverage;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.ingredient.coffeeBase.EspressoBase;

public class Espresso extends Beverage {
    public Espresso(){
        super(BeverageType.ESPRESSO.getName(),
                new Recipe(BeverageType.ESPRESSO.getName(),new EspressoBase(1)));
    }
}
