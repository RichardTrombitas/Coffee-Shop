package com.endava.internship2020.model.ingredient.topping;

import com.endava.internship2020.model.ingredient.Ingredient;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public abstract class Topping extends Ingredient {

    public Topping(String name, BigDecimal basePrice, BigDecimal amount) {
        super(name, basePrice, amount);
    }

}
