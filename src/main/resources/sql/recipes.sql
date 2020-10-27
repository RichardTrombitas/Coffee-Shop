DELETE FROM `recipes` WHERE 1;
DELETE FROM `coffee_bases` WHERE 1;
DELETE FROM `toppings` WHERE 1;
DELETE FROM `sweeteners` WHERE 1;

INSERT INTO `coffee_bases`(`id`, `amount`, `name`) VALUES (1,1, 'Espresso');
INSERT INTO `recipes`(`id`, `name`, `coffee_base_id`) VALUES (1, 'Espresso', 1);

INSERT INTO `coffee_bases`(`id`, `amount`, `name`) VALUES (2, 1, 'Espresso');
INSERT INTO `recipes`(`id`, `name`, `coffee_base_id`) VALUES (2, 'Cappuccino', 2);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (1, 1, 'Steamed Milk', 2);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (2, 2, 'Milk Foam', 2);

INSERT INTO `coffee_bases`(`id`, `amount`, `name`) VALUES (3, 1, 'Espresso');
INSERT INTO `recipes`(`id`, `name`, `coffee_base_id`) VALUES (3, 'Coffee Latte', 3);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (3, 1.5, 'Steamed Milk', 3);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (4, 1, 'Milk Foam', 3);

INSERT INTO `coffee_bases`(`id`, `amount`, `name`) VALUES (4, 2, 'Black Coffee');
INSERT INTO `recipes`(`id`, `name`, `coffee_base_id`) VALUES (4, 'Coffee Miel', 4);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (5, 1, 'Steamed Milk', 4);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (6, 0.5, 'Honey', 4);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (7, 0.4, 'Cinnamon', 4);

INSERT INTO `coffee_bases`(`id`, `amount`, `name`) VALUES (5, 2, 'Espresso');
INSERT INTO `recipes`(`id`, `name`, `coffee_base_id`) VALUES (5, 'Macchiato', 5);
INSERT INTO `toppings`(`id`, `amount`, `name`, `recipe_id`) VALUES (8, 1, 'Milk Foam', 5);

SELECT R.id,
       R.name   AS 'Recipe',
       C.name   AS 'Coffee Base',
       C.amount AS 'Coffee Base Amount'
FROM `recipes` AS R
INNER JOIN `coffee_bases` as C
ON R.coffee_base_id = C.id
ORDER BY R.name;

SELECT R.id,
       R.name   AS 'Recipe',
       T.name   AS 'Topping',
       T.amount AS 'Topping Amount'
FROM `recipes` AS R
LEFT JOIN `toppings` AS T
ON T.recipe_id = R.id
ORDER BY R.name;

SELECT R.id,
R.name   AS 'Recipe',
S.name   AS 'Sweetener',
S.amount AS 'Sweetener Amount'
FROM `recipes` AS R
LEFT JOIN `sweeteners` AS S
ON S.recipe_id = R.id
ORDER BY R.name;
