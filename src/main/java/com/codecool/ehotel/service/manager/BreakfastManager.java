package com.codecool.ehotel.service.manager;

import com.codecool.ehotel.model.breakfast.BreakfastPortionsList;
import com.codecool.ehotel.model.breakfast.BreakfastPortionsMap;
import com.codecool.ehotel.model.breakfast.BreakfastType;
import com.codecool.ehotel.model.guest.Guest;
import com.codecool.ehotel.model.guest.GuestType;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codecool.ehotel.model.MealDurability.MEDIUM;
import static com.codecool.ehotel.model.MealDurability.SHORT;

public class BreakfastManager extends MealManager {

    private final BreakfastPortionsList breakfastPortionsList;


    public BreakfastManager(List<List<Guest>> todayGuestsGroups, LocalTime timestamp) {

        super(todayGuestsGroups, timestamp);

        this.breakfastPortionsList = new BreakfastPortionsList(Buffet.getBreakfastPortions());

    }

    public void serve() {
        for (int i = 0; i < cycles; i++) {
            logger.logInfo("Cycle: " + timestamp);
            BuffetService.refill(Buffet, timestamp, getOptimalPortions());

            for (Guest guest : todayGuestsGroups.get(i)) {
                if (!isGuestHappy(guest)) {
                    unhappyGuestsSum++;
                }
            }
            foodWasteCost += BuffetService.collectWaste(breakfastPortionsList, SHORT, timestamp);
            timestamp = timestamp.plusMinutes(30);
            cyclesLeft--;

            System.out.println("\n-------------------------------");
        }
        System.out.println("End of morning! Discarded meals: ");

        foodWasteCost += BuffetService.collectWaste(breakfastPortionsList, SHORT, timestamp);
        foodWasteCost += BuffetService.collectWaste(breakfastPortionsList, MEDIUM, timestamp);

        logger.logInfo("\nThis morning, there were " + unhappyGuestsSum + " unhappy guests, with a food waste cost of " + foodWasteCost + " RON.");
    }

    private boolean isGuestHappy(Guest guest) {
        List<BreakfastType> mealPreferencesList = guest.getGuestType().getBreakfastPreferences();

        for (BreakfastType breakfastType : mealPreferencesList) {
            if (BuffetService.consumeFreshest(Buffet, breakfastType)) {
                logger.logInfo(guest.firstName() + " " + guest.lastName() + " is happy (The guest ate " + breakfastType + ").");
                return true;
            }
        }
        logger.logInfo(guest.firstName() + " " + guest.lastName() + " is unhappy, none of their meal preferences are available.");
        return false;
    }

    protected BreakfastPortionsMap getOptimalPortions() {

        Map<BreakfastType, Integer> optimalPortions = new HashMap<>();
        for (Map.Entry<GuestType, Integer> pair : numberOfRemainingGuestsByType.entrySet()) {


            int average = (int) Math.ceil((double) pair.getValue() / cyclesLeft);
//            if (average >= unhappinessCost) {
//                logger.logInfo(pair.getKey().getRandomBreakfastType(pair.getKey()) + "; " + average);
//                optimalPortions.put(pair.getKey().getRandomBreakfastType(pair.getKey()), average);
//            }
            optimalPortions.put(pair.getKey().getRandomBreakfastType(pair.getKey()), average);
        }
        logger.logInfo("Today's portions: " + optimalPortions + ".");
        System.out.println("\n");
        return new BreakfastPortionsMap(optimalPortions);
    }
}