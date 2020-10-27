package com.endava.internship2020.repository;

import com.endava.internship2020.model.ingredient.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository<T extends Ingredient> extends CrudRepository<T, Long> {
    @Override
    List<T> findAll();

    Optional<T> findByName(String name);
}
