package com.endava.internship2020.service;

import com.endava.internship2020.exception.OutOfStockException;
import com.endava.internship2020.model.CoffeeShop;
import com.endava.internship2020.model.Order;
import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.beverage.Beverage;
import com.endava.internship2020.model.ingredient.Ingredient;
import com.endava.internship2020.model.ingredient.IngredientType;
import com.endava.internship2020.model.ingredient.coffeeBase.CoffeeBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CoffeeShopServiceImpl implements CoffeeShopService {

    private IngredientService ingredientService;
    private RecipeService recipeService;
    private OrderService orderService;

    @Autowired
    public void setIngredientService(IngredientService ingredientService,
                                     RecipeService recipeService, OrderService orderService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.orderService = orderService;
    }

    @Override
    public void checkSupplyStock() {
        printSupplies();
        if (!enoughSuppliesForThreeCoffees()) {
            System.out.println("\n");
            log.warn("There aren't enough supplies for the next 3 coffees!");
        }
    }

    @Override
    public void placeOrder(Order order) throws OutOfStockException {
        if (!checkAvailability(order)) {
            throw new OutOfStockException("We don't have enough supplies for your order!");
        }
        order.getBeverages().forEach(this::buyBeverage);

        log.info("Order successful: \n" + order +
                "\n\nShop's profit for the day: " + CoffeeShop.getProfit() + " RON");
        orderService.save(order);
    }

    private void printSupplies() {
        System.out.println("\nThe following supplies are in stock:");

        System.out.println("\n\tCoffee bases:");
        ingredientService.getCoffeeBases().forEach(coffeeBase ->
                System.out.println("\t\t" + coffeeBase.getName() + ": " + coffeeBase.getAmount()));

        System.out.println("\n\tToppings:");
        ingredientService.getToppings().forEach(topping ->
                System.out.println("\t\t" + topping.getName() + ": " + topping.getAmount()));

        System.out.println("\n\tSweeteners:");
        ingredientService.getSweeteners().forEach(sweetener ->
                System.out.println("\t\t" + sweetener.getName() + ": " + sweetener.getAmount()));
    }

    private boolean enoughSuppliesForThreeCoffees() {
        final BigDecimal THRESHOLD = BigDecimal.valueOf(3);

        return ingredientService.getCoffeeBases().stream()
                .allMatch(coffeeBase ->
                        getHighestCoffeeBaseAmount(coffeeBase.getType()).multiply(THRESHOLD)
                                .compareTo(coffeeBase.getAmount()) <= 0)
                &&
                ingredientService.getToppings().stream()
                        .allMatch(topping ->
                                getHighestToppingAmount(topping.getType()).multiply(THRESHOLD)
                                        .compareTo(topping.getAmount()) <= 0)
                &&
                ingredientService.getSweeteners().stream()
                        .allMatch(sweetener ->
                                getHighestSweetenerAmount(sweetener.getType()).multiply(THRESHOLD)
                                        .compareTo(sweetener.getAmount()) <= 0);
    }

    private BigDecimal getHighestCoffeeBaseAmount(IngredientType ingredientType) {

        Optional<BigDecimal> maxAmount = Optional.of(recipeService.getRecipes().stream()
                .map(Recipe::getCoffeeBase)
                .filter(coffeeBase -> coffeeBase.getName().equals(ingredientType.getName()))
                .map(Ingredient::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO));

        return maxAmount.get();

    }

    private BigDecimal getHighestToppingAmount(IngredientType ingredientType) {
        return Optional.of(recipeService.getRecipes().stream()
                .map(Recipe::getToppings)
                .map(toppingList -> toppingList.stream()
                        .filter(topping -> topping.getName().equals(ingredientType.getName()))
                        .map(Ingredient::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO))
                .get();
    }

    private BigDecimal getHighestSweetenerAmount(IngredientType ingredientType) {
        return Optional.of(recipeService.getRecipes().stream()
                .map(Recipe::getSweeteners)
                .map(sweetenerList -> sweetenerList.stream()
                        .filter(sweetener -> sweetener.getName().equals(ingredientType.getName()))
                        .map(Ingredient::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO))
                .get();
    }

    private boolean notEnoughIngredients(EnumMap<IngredientType, BigDecimal> requiredIngredients) {
        List<Ingredient> ingredients = ingredientService.getIngredients();
        for (IngredientType ingredientType : requiredIngredients.keySet()) {
            Optional<Ingredient> ingredient = ingredients.stream()
                    .filter(ing -> ing.getType() == ingredientType)
                    .findFirst();
            BigDecimal stockAmount = ingredient.isEmpty() ? BigDecimal.ZERO : ingredient.get().getAmount();
            if (requiredIngredients.get(ingredientType).compareTo(stockAmount) > 0)
                return true;
        }
        return false;
    }

    private boolean checkAvailability(Order order) {
        EnumMap<IngredientType, BigDecimal> requiredCoffeeBases = new EnumMap<>(IngredientType.class);
        EnumMap<IngredientType, BigDecimal> requiredToppings = new EnumMap<>(IngredientType.class);
        EnumMap<IngredientType, BigDecimal> requiredSweeteners = new EnumMap<>(IngredientType.class);

        for (Beverage beverage : order.getBeverages()) {
            Recipe recipe = beverage.getRecipe();

            CoffeeBase coffeeBase = recipe.getCoffeeBase();
            requiredCoffeeBases.put(coffeeBase.getType(),
                    requiredCoffeeBases.getOrDefault(coffeeBase.getType(), BigDecimal.ZERO)
                            .add(coffeeBase.getAmount()));

            if (notEnoughIngredients(requiredCoffeeBases))
                return false;

            recipe.getToppings().forEach(topping -> requiredToppings.put(topping.getType(),
                    requiredToppings.getOrDefault(topping.getType(), BigDecimal.ZERO)
                            .add(topping.getAmount())));

            if (notEnoughIngredients(requiredToppings))
                return false;

            recipe.getSweeteners().forEach(sweetener -> requiredSweeteners.put(sweetener.getType(),
                    requiredSweeteners.getOrDefault(sweetener.getType(), BigDecimal.ZERO)
                            .add(sweetener.getAmount())));

            if (notEnoughIngredients(requiredSweeteners))
                return false;
        }
        return true;
    }

    private void buyBeverage(Beverage beverage) {
        Recipe recipe = beverage.getRecipe();

        ingredientService.reduceAmount(recipe.getCoffeeBase().getName(), recipe.getCoffeeBase().getAmount());

        recipe.getToppings().forEach(topping ->
                ingredientService.reduceAmount(topping.getName(), topping.getAmount()));

        recipe.getSweeteners().forEach(sweetener ->
                ingredientService.reduceAmount(sweetener.getName(), sweetener.getAmount()));

        CoffeeShop.addProfit(beverage.getPrice());
    }
}
