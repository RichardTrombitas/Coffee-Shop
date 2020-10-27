package com.endava.internship2020;

import com.endava.internship2020.exception.OutOfStockException;
import com.endava.internship2020.model.CoffeeShop;
import com.endava.internship2020.model.Customer;
import com.endava.internship2020.model.Order;
import com.endava.internship2020.model.Recipe;
import com.endava.internship2020.model.beverage.Beverage;
import com.endava.internship2020.model.ingredient.IngredientFactory;
import com.endava.internship2020.model.ingredient.coffeeBase.CoffeeBase;
import com.endava.internship2020.model.ingredient.sweetener.Sweetener;
import com.endava.internship2020.model.ingredient.topping.Topping;
import com.endava.internship2020.service.CoffeeService;
import com.endava.internship2020.service.CoffeeShopService;
import com.endava.internship2020.service.IngredientService;
import com.endava.internship2020.service.RecipeService;
import com.endava.internship2020.util.CheckSupplyStockScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Component
public class Console {
    private final List<Beverage> shoppingCart = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private Customer customer;
    private final CoffeeService coffeeService;
    private final CoffeeShopService coffeeShopService;
    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final CheckSupplyStockScheduler scheduler;
    private final CoffeeShop coffeeShop;

    @Autowired
    public Console(CoffeeService coffeeService,
                   CoffeeShopService coffeeShopService,
                   IngredientService ingredientService,
                   RecipeService recipeService,
                   CheckSupplyStockScheduler scheduler,
                   CoffeeShop coffeeShop) {
        this.coffeeService = coffeeService;
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.coffeeShopService = coffeeShopService;
        this.scheduler = scheduler;
        this.coffeeShop = coffeeShop;
    }

    private void printBeverages() {
        List<Recipe> recipes = recipeService.getRecipes();
        System.out.println("We have the best coffee in town:\n");
        IntStream.rangeClosed(1, recipes.size())
                .forEach(i -> {
                    System.out.print(i + ". ");
                    System.out.println(new Beverage(recipes.get(i - 1)).toString());
                });
    }

    private void printMenu() {
        System.out.println("\nChoose an operation:");
        System.out.println("\t1 - Show list of coffees");
        System.out.println("\t2 - Choose one of our coffees");
        System.out.println("\t3 - Create your own coffee");
        System.out.println("\t4 - Check stock of supplies");
        System.out.println("\t5 - Toggle scheduled supply stock checking " +
                (scheduler.isEnabled() ? "(enabled)" : "(disabled)"));
        if (shoppingCart.size() > 0) {
            System.out.println("\t6 - See shopping cart");
            System.out.println("\t7 - Clear shopping cart");
            System.out.println("\t8 - Place order");
        }
        System.out.println("\t0 - Exit");
    }

    private Beverage chooseBeverage() {
        List<Recipe> recipes = recipeService.getRecipes();
        System.out.println("\nEnter the number of the desired coffee:");
        try {
            int coffeeIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (coffeeIndex >= recipes.size() || coffeeIndex < 0) {
                throw new IOException("Invalid number");
            }
            return new Beverage(recipes.get(coffeeIndex));
        } catch (NumberFormatException | IOException e) {
            System.out.println("\nInvalid input! Try again.\n");
            return chooseBeverage();
        }
    }

    private boolean isPickUp() {
        String input = "";
        while (!(input.equals("1") || input.equals("2"))) {
            System.out.println("\nDo you prefer pick-up or delivery?");
            System.out.println("1 - pick-up");
            System.out.println("2 - delivery");
            input = scanner.nextLine();
        }

        return input.equals("1");
    }

    private void placeOrder() {

        boolean isPickUp = isPickUp();

        Order order = new Order(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")),
                customer, isPickUp, shoppingCart);
        try {
            coffeeShopService.placeOrder(order);
            System.out.println("\nThank you for your purchase, " + customer.getName() + "!");
        } catch (OutOfStockException e) {
            System.out.println("\n" + e.getMessage());
        }
        shoppingCart.clear();
    }

    private BigDecimal chooseShots() {
        System.out.println("\nEnter the number of shots:");
        try {
            int shots = Integer.parseInt(scanner.nextLine());
            if (shots <= 0)
                throw new IOException("Invalid amount!");
            return BigDecimal.valueOf(shots);
        } catch (NumberFormatException | IOException e) {
            System.out.println("\nInvalid amount! Try again.\n");
            return chooseShots();
        }
    }

    private CoffeeBase chooseCoffeeBase() {
        List<CoffeeBase> coffeeBases = List.copyOf(ingredientService.getCoffeeBases());

        System.out.println("\nChoose a coffee base: ");
        IntStream.rangeClosed(1, coffeeBases.size())
                .forEach(i -> System.out.println(i + ". " +
                        IngredientFactory.createCoffeeBase(coffeeBases.get(i - 1).getType(), BigDecimal.ZERO)));

        try {
            int input = Integer.parseInt(scanner.nextLine()) - 1;

            if (input < 0 || input >= coffeeBases.size())
                throw new IOException("Invalid input!");

            return IngredientFactory.createCoffeeBase(coffeeBases.get(input).getType(), chooseShots());

        } catch (NumberFormatException | IOException e) {
            System.out.println("\nInvalid input! Try again.\n");
            return chooseCoffeeBase();
        }
    }

    private BigDecimal chooseAmount() {
        System.out.println("\nEnter the amount (ex. 0.5 for half, 2 for double, etc.):");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0)
                throw new IOException("Invalid amount!");
            return BigDecimal.valueOf(amount);
        } catch (NumberFormatException | IOException e) {
            System.out.println("\nInvalid amount! Try again.\n");
            return chooseAmount();
        }
    }

    private Topping chooseTopping() {
        List<Topping> toppings = List.copyOf(ingredientService.getToppings());

        System.out.println("\nChoose a topping: ");

        IntStream.rangeClosed(1, toppings.size())
                .forEach(i -> System.out.println(i + ". " +
                        IngredientFactory.createTopping(toppings.get(i - 1).getType(), BigDecimal.ZERO)));

        try {
            int input = Integer.parseInt(scanner.nextLine()) - 1;

            if (input < 0 || input >= toppings.size())
                throw new IOException("Invalid input!");

            return IngredientFactory.createTopping(toppings.get(input).getType(), chooseAmount());

        } catch (NumberFormatException | IOException e) {
            System.out.println("\nInvalid input! Try again.\n");
            return chooseTopping();

        }
    }

    private Sweetener chooseSweetener() {
        List<Sweetener> sweeteners = List.copyOf(ingredientService.getSweeteners());

        System.out.println("\nChoose a topping: ");

        IntStream.rangeClosed(1, sweeteners.size())
                .forEach(i -> System.out.println(i + ". " +
                        IngredientFactory.createSweetener(sweeteners.get(i - 1).getType(), BigDecimal.ZERO)));

        try {
            int input = Integer.parseInt(scanner.nextLine()) - 1;

            if (input < 0 || input >= sweeteners.size())
                throw new IOException("Invalid input!");

            return IngredientFactory.createSweetener(sweeteners.get(input).getType(), chooseAmount());

        } catch (NumberFormatException | IOException e) {
            System.out.println("\nInvalid input! Try again.\n");
            return chooseSweetener();
        }
    }

    private void addToppings(Recipe recipe) {
        while (true) {
            System.out.println("\nWould you like to add an extra topping?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");

            switch (scanner.nextLine()) {
                case "1" -> recipe.addTopping(chooseTopping());
                case "2" -> {
                    return;
                }
                default -> System.out.println("\nInvalid input! Try again.\n");
            }
        }
    }

    private void addSweeteners(Recipe recipe) {
        while (true) {
            System.out.println("\nWould you like to add an extra sweetener?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");

            switch (scanner.nextLine()) {
                case "1" -> recipe.addSweetener(chooseSweetener());
                case "2" -> {
                    return;
                }
                default -> System.out.println("\nInvalid input! Try again.\n");
            }
        }
    }

    private Beverage createCustomBeverage() {
        CoffeeBase coffeeBase = chooseCoffeeBase();
        Recipe recipe = new Recipe("Custom recipe", coffeeBase);
        addToppings(recipe);
        addSweeteners(recipe);
        Beverage customBeverage = coffeeService.createBeverage(recipe);
        customBeverage.setName(customer.getName() + "'s Coffee");
        return customBeverage;
    }

    private void seeShoppingCart() {
        System.out.println("\nShopping cart:");
        BigDecimal total = BigDecimal.ZERO;
        for (Beverage beverage : shoppingCart) {
            System.out.println(beverage);
            total = total.add(beverage.getPrice());
        }

        System.out.println("Total: " + total + " RON");
    }

    private void addToShoppingCart(Beverage beverage) {
        shoppingCart.add(beverage);
        System.out.println("\nCoffee added to shopping cart.\n");
    }

    private void clearShoppingCart() {
        shoppingCart.clear();
        System.out.println("\nShopping cart cleared.\n");
    }

    private void handleUserInput() {
        System.out.println("Your choice:");

        String input = scanner.nextLine();

        switch (input) {
            case "1" -> {
                printBeverages();
                printMenu();
                handleUserInput();
            }
            case "2" -> {
                addToShoppingCart(chooseBeverage());
                printMenu();
                handleUserInput();
            }
            case "3" -> {
                addToShoppingCart(createCustomBeverage());
                printMenu();
                handleUserInput();
            }
            case "4" -> {
                coffeeShopService.checkSupplyStock();
                printMenu();
                handleUserInput();
            }
            case "5" -> {
                scheduler.toggleScheduledSupplyStockCheck();
                printMenu();
                handleUserInput();
            }
            case "6" -> {
                if (shoppingCart.isEmpty()) {
                    System.out.println("\nInvalid input, try again.\n");
                    handleUserInput();
                }
                seeShoppingCart();
                printMenu();
                handleUserInput();
            }
            case "7" -> {
                if (shoppingCart.isEmpty()) {
                    System.out.println("\nInvalid input, try again.\n");
                    handleUserInput();
                }
                clearShoppingCart();
                printMenu();
                handleUserInput();
            }
            case "8" -> {
                if (shoppingCart.isEmpty()) {
                    System.out.println("\nInvalid input, try again.\n");
                    handleUserInput();
                }
                placeOrder();
                printMenu();
                handleUserInput();
            }
            case "0" -> {
                System.out.println("\nThank you for visiting us! Have a nice day!\n");
                scanner.close();
                System.exit(0);
            }
            default -> {
                System.out.println("\nInvalid input, try again.\n");
                handleUserInput();
            }
        }
    }

    public void start() {
        System.out.println("\nPlease enter your name: ");
        customer = new Customer(scanner.nextLine());
        System.out.println("\nWelcome to " + coffeeShop.getName() + "!");
        printBeverages();
        printMenu();
        handleUserInput();
    }
}
