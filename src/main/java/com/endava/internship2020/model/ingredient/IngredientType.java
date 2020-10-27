package com.endava.internship2020.model.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IngredientType {
    ESPRESSO("Espresso"),
    BLACK_COFFEE("Black Coffee"),
    CINNAMON("Cinnamon"),
    HONEY("Honey"),
    MILK_FOAM("Milk Foam"),
    STEAMED_MILK("Steamed Milk"),
    SUGAR("Sugar"),
    BROWN_SUGAR("Brown Sugar");

    @Getter
    private final String name;
}
