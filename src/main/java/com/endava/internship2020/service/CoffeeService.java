package com.endava.internship2020.service;

import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.beverage.Beverage;

public interface CoffeeService {

    Beverage createBeverage(Recipe recipe);

}
