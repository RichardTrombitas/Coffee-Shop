package com.endava.internship2020.model.ingredient.topping;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Honey extends Topping {

    public Honey(double amount){
        super(IngredientType.HONEY.getName(), BigDecimal.valueOf(2.99), BigDecimal.valueOf(amount));
    }

    public Honey(BigDecimal amount){
        super(IngredientType.HONEY.getName(), BigDecimal.valueOf(2.99), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.HONEY;
    }
}
