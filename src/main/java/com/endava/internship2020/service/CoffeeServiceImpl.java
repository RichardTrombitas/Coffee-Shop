package com.endava.internship2020.service;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.beverage.Beverage;
import org.springframework.stereotype.Service;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    @Override
    public Beverage createBeverage(Recipe recipe) {
        return new Beverage(recipe);
    }
}
