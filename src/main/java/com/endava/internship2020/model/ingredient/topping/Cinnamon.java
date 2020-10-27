package com.endava.internship2020.model.ingredient.topping;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Cinnamon extends Topping {

    public Cinnamon(double amount){
        super(IngredientType.CINNAMON.getName(), BigDecimal.valueOf(2.3), BigDecimal.valueOf(amount));
    }

    public Cinnamon(BigDecimal amount){
        super(IngredientType.CINNAMON.getName(), BigDecimal.valueOf(2.3), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.CINNAMON;
    }
}
