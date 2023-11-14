package com.codecool.ehotel.model.dinner;

import com.codecool.ehotel.model.MealDurability;

import static com.codecool.ehotel.model.MealDurability.*;

public enum DinnerType {
    CHICKEN_OR_VEGAN_NUGGETS(70, SHORT, new Ingredient[]{
            new Ingredient("Chicken Nuggets", "Fresh", "2023-07-15"),
            new Ingredient("Vegan Nuggets", "Fresh", "2023-07-15")
    }),
    SPAGHETTI_WITH_OR_WITHOUT_MEATBALLS(70, SHORT, new Ingredient[]{
            new Ingredient("Spaghetti", "Fresh", "2023-07-14"),
            new Ingredient("Meatballs", "Fresh", "2023-07-14")
    }),
    GRILLED_CHEESE_SANDWICH(100, SHORT, new Ingredient[]{
            new Ingredient("Bread", "Fresh", "2023-07-13"),
            new Ingredient("Cheese", "Fresh", "2023-07-14")
    }),
    TACOS_WITH_GROUND_BEEF_AND_CHEESE(40, SHORT, new Ingredient[]{
            new Ingredient("Tacos", "Fresh", "2023-07-13"),
            new Ingredient("Ground Beef", "Fresh", "2023-07-13"),
            new Ingredient("Cheese", "Fresh", "2023-07-14")
    }),
    PIZZA_WITH_OR_WITHOUT_PEPPERONI_AND_CHEESE(20, MEDIUM, new Ingredient[]{
            new Ingredient("Pizza Dough", "Fresh", "2023-07-13"),
            new Ingredient("Pepperoni", "Fresh", "2023-07-14"),
            new Ingredient("Cheese", "Fresh", "2023-07-14")
    }),
    SPINACH_QUICHE(20, MEDIUM, new Ingredient[]{
            new Ingredient("Spinach", "Fresh", "2023-07-14"),
            new Ingredient("Eggs", "Fresh", "2023-07-15"),
            new Ingredient("Cheese", "Fresh", "2023-07-14")
    }),
    BEEF_BURGER(10, MEDIUM, new Ingredient[]{
            new Ingredient("Beef Patty", "Fresh", "2023-07-14"),
            new Ingredient("Burger Buns", "Fresh", "2023-07-13")
    }),
    EASY_FRIED_RICE(10, LONG, new Ingredient[]{
            new Ingredient("Rice", "Fresh", "2023-07-15"),
            new Ingredient("Vegetables", "Fresh", "2023-07-14"),
            new Ingredient("Eggs", "Fresh", "2023-07-15")
    }),
    MAC_AND_CHEESE(30, LONG, new Ingredient[]{
            new Ingredient("Macaroni", "Fresh", "2023-07-14"),
            new Ingredient("Cheese", "Fresh", "2023-07-14")
    });

    private final int cost;
    private final MealDurability mealDurability;
    private final Ingredient[] ingredients;

    DinnerType(int cost, MealDurability mealDurability, Ingredient[] ingredients) {
        this.cost = cost;
        this.mealDurability = mealDurability;
        this.ingredients = ingredients;
    }

    public int getCost() {
        return cost;
    }

    public MealDurability getDurability() {
        return mealDurability;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }
}
