package com.endava.internship2020.model.beverage;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.ingredient.Ingredient;
import com.endava.internship2020.model.ingredient.sweetener.Sweetener;
import com.endava.internship2020.model.ingredient.topping.Topping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Beverage {
    @Setter
    private String name;

    @Setter
    private Recipe recipe;

    private BigDecimal price;

    public Beverage(Recipe recipe) {
        if(recipe.getName() != null)
            name = recipe.getName();
        this.recipe = recipe;
        this.price = computePrice();
    }

    public Beverage(String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
        this.price = computePrice();
    }

    private BigDecimal computePrice() {
        BigDecimal price = BigDecimal.ZERO;

        price = price.add(recipe.getCoffeeBase().getTotalPrice());

        price = price.add(recipe.getToppings().stream()
            .map(Ingredient::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add));

        price = price.add(recipe.getSweeteners().stream()
                .map(Ingredient::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        return price;
    }

    public void addTopping(Topping topping) {
        recipe.addTopping(topping);
        price = price.add(topping.getTotalPrice());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(50);
        sb.append(name).append(" - ")
                .append(price).append(" RON")
                .append("\n\tcoffee base: ")
                .append(recipe.getCoffeeBase().getAmount())
                .append(" ")
                .append(recipe.getCoffeeBase().getName());

        if (!recipe.getToppings().isEmpty()) {
            sb.append(",\n\ttoppings:");
            for (Topping topping : recipe.getToppings()) {
                sb.append(" ")
                        .append(topping.getAmount())
                        .append(" ")
                        .append(topping.getName())
                        .append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        }

        if (!recipe.getSweeteners().isEmpty()) {
            sb.append(",\n\tsweeteners:");
            for (Sweetener sweetener : recipe.getSweeteners()) {
                sb.append(" ")
                        .append(sweetener.getAmount())
                        .append(" ")
                        .append(sweetener.getName())
                        .append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        }

        sb.append("\n");
        return sb.toString();
    }

}
