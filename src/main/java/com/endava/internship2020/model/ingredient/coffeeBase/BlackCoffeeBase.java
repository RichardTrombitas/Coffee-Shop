package com.endava.internship2020.model.ingredient.coffeeBase;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class BlackCoffeeBase extends CoffeeBase {

    public BlackCoffeeBase(double amount){
        super(IngredientType.BLACK_COFFEE.getName(), BigDecimal.valueOf(5.49), BigDecimal.valueOf(amount));
    }

    public BlackCoffeeBase(BigDecimal amount) {
        super(IngredientType.BLACK_COFFEE.getName(), BigDecimal.valueOf(5.49), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.BLACK_COFFEE;
    }
}
