package com.endava.internship2020.service;

import com.endava.internship2020.model.ingredient.Ingredient;
import com.endava.internship2020.model.ingredient.coffeeBase.CoffeeBase;
import com.endava.internship2020.model.ingredient.sweetener.Sweetener;
import com.endava.internship2020.model.ingredient.topping.Topping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Ingredient saveIngredient(Ingredient ingredient);

    List<Ingredient> getIngredients();

    Optional<Ingredient> findByName(String name);

    List<CoffeeBase> getCoffeeBases();

    List<Topping> getToppings();

    List<Sweetener> getSweeteners();

    void reduceAmount(String name, BigDecimal amount);
}
