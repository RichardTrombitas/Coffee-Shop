package com.endava.internship2020.service;

import com.endava.internship2020.dto.RecipeDto;
import com.endava.internship2020.dto.mapper.RecipeMapper;
import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Transactional
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::recipeDtoToRecipe)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Recipe> findByName(String name){
        Optional<RecipeDto> recipeDto = recipeRepository.findByName(name);
        return recipeDto.map(recipeMapper::recipeDtoToRecipe);
    }

}
