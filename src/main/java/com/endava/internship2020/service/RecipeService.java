package com.endava.internship2020.service;

import com.endava.internship2020.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> getRecipes();

    Optional<Recipe> findByName(String name);
}
