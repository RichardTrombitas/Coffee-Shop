package com.endava.internship2020.model.ingredient.sweetener;

import com.endava.internship2020.model.ingredient.IngredientType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class BrownSugar extends Sweetener {

    public BrownSugar(double amount){
        super(IngredientType.BROWN_SUGAR.getName(), BigDecimal.valueOf(0.25), BigDecimal.valueOf(amount));
    }

    public BrownSugar(BigDecimal amount){
        super(IngredientType.BROWN_SUGAR.getName(), BigDecimal.valueOf(0.25), amount);
    }

    @Override
    public IngredientType getType() {
        return IngredientType.BROWN_SUGAR;
    }
}
