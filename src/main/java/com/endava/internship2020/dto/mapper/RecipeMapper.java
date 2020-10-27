package com.endava.internship2020.dto.mapper;

import com.endava.internship2020.dto.ingredient.CoffeeBaseDto;
import com.endava.internship2020.dto.RecipeDto;
import com.endava.internship2020.dto.ingredient.SweetenerDto;
import com.endava.internship2020.dto.ingredient.ToppingDto;
import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.ingredient.IngredientFactory;
import com.endava.internship2020.model.ingredient.IngredientType;
import com.endava.internship2020.model.ingredient.coffeeBase.CoffeeBase;
import com.endava.internship2020.model.ingredient.sweetener.Sweetener;
import com.endava.internship2020.model.ingredient.topping.Topping;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe recipeDtoToRecipe(RecipeDto recipeDto);

    default CoffeeBase toCoffeeBase(CoffeeBaseDto coffeeBaseDto) {
        return IngredientFactory.createCoffeeBase(IngredientType.valueOf(coffeeBaseDto.getName()
                .toUpperCase()
                .replaceAll(" ", "_")), BigDecimal.valueOf(coffeeBaseDto.getAmount()));
    }

    default Topping toTopping(ToppingDto toppingDto) {
        return IngredientFactory.createTopping(IngredientType.valueOf(toppingDto.getName()
                .toUpperCase()
                .replaceAll(" ", "_")), BigDecimal.valueOf(toppingDto.getAmount()));
    }

    default Sweetener toSweetener(SweetenerDto sweetenerDto) {
        return IngredientFactory.createSweetener(IngredientType.valueOf(sweetenerDto.getName()
                .toUpperCase()
                .replaceAll(" ", "_")), BigDecimal.valueOf(sweetenerDto.getAmount()));
    }
}
