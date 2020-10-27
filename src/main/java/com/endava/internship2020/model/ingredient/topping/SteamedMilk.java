package com.endava.internship2020.model.ingredient.topping;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class SteamedMilk extends Topping {

    public SteamedMilk(double amount){
        super(IngredientType.STEAMED_MILK.getName(), BigDecimal.valueOf(1.99), BigDecimal.valueOf(amount));
    }

    public SteamedMilk(BigDecimal amount){
        super(IngredientType.STEAMED_MILK.getName(), BigDecimal.valueOf(1.99), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.STEAMED_MILK;
    }
}
