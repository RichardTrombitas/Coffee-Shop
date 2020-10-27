package com.endava.internship2020.model.ingredient;

import com.endava.internship2020.model.ingredient.IngredientType;
import com.endava.internship2020.model.ingredient.coffeeBase.BlackCoffeeBase;
import com.endava.internship2020.model.ingredient.coffeeBase.CoffeeBase;
import com.endava.internship2020.model.ingredient.coffeeBase.EspressoBase;
import com.endava.internship2020.model.ingredient.sweetener.BrownSugar;
import com.endava.internship2020.model.ingredient.sweetener.Sugar;
import com.endava.internship2020.model.ingredient.sweetener.Sweetener;
import com.endava.internship2020.model.ingredient.topping.Cinnamon;
import com.endava.internship2020.model.ingredient.topping.Honey;
import com.endava.internship2020.model.ingredient.topping.MilkFoam;
import com.endava.internship2020.model.ingredient.topping.SteamedMilk;
import com.endava.internship2020.model.ingredient.topping.Topping;

import java.math.BigDecimal;

public class IngredientFactory {
    public static CoffeeBase createCoffeeBase(IngredientType ingredientType, BigDecimal amount){
        return switch (ingredientType) {
            case ESPRESSO -> new EspressoBase(amount);
            case BLACK_COFFEE -> new BlackCoffeeBase(amount);
            default -> throw new IllegalStateException("Can't create a CoffeeBase from " +
                    ingredientType.getName() + "!");
        };
    }

    public static Topping createTopping(IngredientType ingredientType, BigDecimal amount){
        return switch (ingredientType) {
            case CINNAMON -> new Cinnamon(amount);
            case HONEY -> new Honey(amount);
            case MILK_FOAM -> new MilkFoam(amount);
            case STEAMED_MILK -> new SteamedMilk(amount);
            default -> throw new IllegalStateException("Can't create a Topping from " +
                    ingredientType.getName() + "!");
        };
    }

    public static Sweetener createSweetener(IngredientType ingredientType, BigDecimal amount){
        return switch (ingredientType) {
            case SUGAR -> new Sugar(amount);
            case BROWN_SUGAR -> new BrownSugar(amount);
            default -> throw new IllegalStateException("Can't create a Sweetener from " +
                    ingredientType.getName() + "!");
        };
    }
}
