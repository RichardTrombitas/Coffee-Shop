package com.endava.internship2020.model;

import com.endava.internship2020.model.ingredient.coffeeBase.CoffeeBase;
import com.endava.internship2020.model.ingredient.sweetener.Sweetener;
import com.endava.internship2020.model.ingredient.topping.Topping;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Recipe {

    private String name;
    private CoffeeBase coffeeBase;
    private List<Topping> toppings = new ArrayList<>();
    private List<Sweetener> sweeteners = new ArrayList<>();

    public Recipe(String name, CoffeeBase coffeeBase, List<Topping> toppings, List<Sweetener> sweeteners) {
        this.name = name;
        this.coffeeBase = coffeeBase;
        this.toppings = toppings;
        this.sweeteners = sweeteners;
    }

    public Recipe(String name, CoffeeBase coffeeBase) {
        this.name = name;
        this.coffeeBase = coffeeBase;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void addSweetener(Sweetener sweetener) {
        sweeteners.add(sweetener);
    }

}
