package com.endava.internship2020.model.ingredient.sweetener;

import com.endava.internship2020.model.ingredient.Ingredient;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public abstract class Sweetener extends Ingredient {

    public Sweetener(String name, BigDecimal basePrice, BigDecimal amount) {
        super(name, basePrice, amount);
    }

}
