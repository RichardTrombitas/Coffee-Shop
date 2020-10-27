package com.endava.internship2020.model.ingredient.coffeeBase;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class EspressoBase extends CoffeeBase {

    public EspressoBase(double amount){
        super(IngredientType.ESPRESSO.getName(), BigDecimal.valueOf(4.99), BigDecimal.valueOf(amount));
    }

    public EspressoBase(BigDecimal amount) {
        super(IngredientType.ESPRESSO.getName(), BigDecimal.valueOf(4.99), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.ESPRESSO;
    }
}
