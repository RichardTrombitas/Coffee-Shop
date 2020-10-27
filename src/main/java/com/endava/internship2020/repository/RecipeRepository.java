package com.endava.internship2020.repository;

import com.endava.internship2020.dto.RecipeDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<RecipeDto, Long> {
    @Override
    List<RecipeDto> findAll();

    Optional<RecipeDto> findByName(String name);
}
