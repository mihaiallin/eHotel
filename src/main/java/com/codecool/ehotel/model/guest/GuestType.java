package com.codecool.ehotel.model.guest;

import com.codecool.ehotel.model.breakfast.BreakfastType;
import com.codecool.ehotel.model.dinner.DinnerType;

import java.util.List;

import static com.codecool.ehotel.model.breakfast.BreakfastType.*;
import static com.codecool.ehotel.model.dinner.DinnerType.*;

public enum GuestType {

    BUSINESS(List.of(SCRAMBLED_EGGS, FRIED_BACON, CROISSANT), List.of(CHICKEN_OR_VEGAN_NUGGETS, BEEF_BURGER, SPINACH_QUICHE)),
    TOURIST(List.of(SUNNY_SIDE_UP, FRIED_SAUSAGE, MASHED_POTATO, BUN, MUFFIN), List.of(TACOS_WITH_GROUND_BEEF_AND_CHEESE, EASY_FRIED_RICE, GRILLED_CHEESE_SANDWICH)),
    KID(List.of(PANCAKE, MUFFIN, CEREAL, MILK), List.of(PIZZA_WITH_OR_WITHOUT_PEPPERONI_AND_CHEESE, SPAGHETTI_WITH_OR_WITHOUT_MEATBALLS, MAC_AND_CHEESE));

    private final List<BreakfastType> breakfastPreferences;
    private final List<DinnerType> dinnerPreferences;

    GuestType(List<BreakfastType> mealPreferences, List<DinnerType> dinnerPreferences) {
        this.breakfastPreferences = mealPreferences;
        this.dinnerPreferences = dinnerPreferences;
    }

    public List<BreakfastType> getBreakfastPreferences() {
        return breakfastPreferences;
    }

    public List<DinnerType> getDinnerPreferences() {
        return dinnerPreferences;
    }

    public BreakfastType getRandomBreakfastType(GuestType guestType) {
        int random = (int) (Math.random() * guestType.getBreakfastPreferences().size() - 1);
        return guestType.getBreakfastPreferences().get(random);
    }

    public DinnerType getRandomDinnerType(GuestType guestType) {
        int random = (int) (Math.random() * guestType.getDinnerPreferences().size() - 1);
        return guestType.getDinnerPreferences().get(random);
    }
}
