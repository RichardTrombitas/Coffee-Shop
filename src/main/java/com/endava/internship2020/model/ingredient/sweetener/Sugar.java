package com.endava.internship2020.model.ingredient.sweetener;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Sugar extends Sweetener {

    public Sugar(double amount){
        super(IngredientType.SUGAR.getName(), BigDecimal.valueOf(0.15), BigDecimal.valueOf(amount));
    }

    public Sugar(BigDecimal amount){
        super(IngredientType.SUGAR.getName(), BigDecimal.valueOf(0.15), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.SUGAR;
    }
}
