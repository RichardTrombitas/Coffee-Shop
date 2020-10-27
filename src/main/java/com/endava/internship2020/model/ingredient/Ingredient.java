package com.endava.internship2020.model.ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Inheritance
@Getter
@NoArgsConstructor
@Table(name = "ingredients")
public abstract class Ingredient {
    @Id
    @GeneratedValue
    protected long id;

    protected String name;
    protected BigDecimal basePrice;

    @Setter
    protected BigDecimal amount;

    public Ingredient(String name, BigDecimal basePrice, BigDecimal amount) {
        this.name = name;
        this.basePrice = basePrice;
        this.amount = amount;
    }

    public BigDecimal getTotalPrice() {
        return basePrice.multiply(amount);
    }

    public abstract IngredientType getType();

    @Override
    public String toString() {
        if (amount.equals(BigDecimal.ZERO)) {
            return name + " - " + basePrice + " RON";
        }
        return amount + " " + name + " - "
                + getTotalPrice()
                + " RON";
    }
}
