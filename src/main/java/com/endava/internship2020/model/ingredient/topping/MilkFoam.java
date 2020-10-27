package com.endava.internship2020.model.ingredient.topping;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class MilkFoam extends Topping {

    public MilkFoam(double amount){
        super(IngredientType.MILK_FOAM.getName(), BigDecimal.valueOf(1.4), BigDecimal.valueOf(amount));
    }

    public MilkFoam(BigDecimal amount){
        super(IngredientType.MILK_FOAM.getName(), BigDecimal.valueOf(1.4), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.MILK_FOAM;
    }
}
