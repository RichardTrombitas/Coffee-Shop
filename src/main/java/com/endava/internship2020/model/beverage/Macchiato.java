package com.endava.internship2020.model.beverage;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.ingredient.coffeeBase.EspressoBase;
import com.endava.internship2020.model.ingredient.topping.MilkFoam;

public class Macchiato extends Beverage {
    public Macchiato(){
        super(BeverageType.MACCHIATO.getName(),
                new Recipe(BeverageType.MACCHIATO.getName(), new EspressoBase(2)));
        addTopping(new MilkFoam(1));
    }
}
