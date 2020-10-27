package com.endava.internship2020.model.ingredient.coffeeBase;

import com.endava.internship2020.model.ingredient.Ingredient;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public abstract class CoffeeBase extends Ingredient {

    public CoffeeBase(String name, BigDecimal basePrice, BigDecimal amount) {
        super(name, basePrice, amount);
    }

}
