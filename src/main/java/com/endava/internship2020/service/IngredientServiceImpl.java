package com.endava.internship2020.service;

import com.endava.internship2020.exception.OutOfStockException;
import com.endava.internship2020.model.ingredient.Ingredient;
import com.endava.internship2020.model.ingredient.IngredientType;
import com.endava.internship2020.model.ingredient.coffeeBase.CoffeeBase;
import com.endava.internship2020.model.ingredient.sweetener.Sweetener;
import com.endava.internship2020.model.ingredient.topping.Topping;
import com.endava.internship2020.repository.CoffeeBaseRepository;
import com.endava.internship2020.repository.IngredientRepository;
import com.endava.internship2020.repository.SweetenerRepository;
import com.endava.internship2020.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository<Ingredient> ingredientRepository;
    private final CoffeeBaseRepository coffeeBaseRepository;
    private final ToppingRepository toppingRepository;
    private final SweetenerRepository sweetenerRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository<Ingredient> ingredientRepository,
                                 CoffeeBaseRepository coffeeBaseRepository,
                                 ToppingRepository toppingRepository,
                                 SweetenerRepository sweetenerRepository) {
        this.ingredientRepository = ingredientRepository;
        this.coffeeBaseRepository = coffeeBaseRepository;
        this.toppingRepository = toppingRepository;
        this.sweetenerRepository = sweetenerRepository;
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findByName(String name) {
        return ingredientRepository.findByName(name);
    }

    @Override
    public List<CoffeeBase> getCoffeeBases() {
        return coffeeBaseRepository.findAll();
    }

    @Override
    public List<Topping> getToppings() {
        return toppingRepository.findAll();
    }

    @Override
    public List<Sweetener> getSweeteners() {
        return sweetenerRepository.findAll();
    }

    @Override
    @Transactional
    public void reduceAmount(String name, BigDecimal amount) {
        findByName(name).ifPresent(ingredient ->
                ingredient.setAmount(ingredient.getAmount().subtract(amount)));
    }
}
