package com.codecool.ehotel.service.manager;

import com.codecool.ehotel.model.dinner.DinnerPortionsList;
import com.codecool.ehotel.model.dinner.DinnerPortionsMap;
import com.codecool.ehotel.model.dinner.DinnerType;
import com.codecool.ehotel.model.guest.Guest;
import com.codecool.ehotel.model.guest.GuestType;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codecool.ehotel.model.MealDurability.MEDIUM;
import static com.codecool.ehotel.model.MealDurability.SHORT;

public class DinnerManager extends MealManager {

    private final DinnerPortionsList dinnerPortionsList;


    public DinnerManager(List<List<Guest>> todayGuestsGroups, LocalTime timestamp) {

        super(todayGuestsGroups, timestamp);

        this.dinnerPortionsList = new DinnerPortionsList(Buffet.getDinnerPortions());

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
            foodWasteCost += BuffetService.collectWaste(dinnerPortionsList, SHORT, timestamp);
            timestamp = timestamp.plusMinutes(30);
            cyclesLeft--;

            System.out.println("\n-------------------------------");
        }
        System.out.println("End of day! Discarded meals: ");

        foodWasteCost += BuffetService.collectWaste(dinnerPortionsList, SHORT, timestamp);
        foodWasteCost += BuffetService.collectWaste(dinnerPortionsList, MEDIUM, timestamp);

        logger.logInfo("This evening, there were " + unhappyGuestsSum + " unhappy guests, with a food waste cost of " + foodWasteCost + " RON.");
    }

    private boolean isGuestHappy(Guest guest) {
        List<DinnerType> mealPreferencesList = guest.getGuestType().getDinnerPreferences();

        for (DinnerType dinnerType : mealPreferencesList) {
            if (BuffetService.consumeFreshest(Buffet, dinnerType)) {
                logger.logInfo(guest.firstName() + " " + guest.lastName() + " is happy (The guest ate " + dinnerType + ").");
                return true;
            }
        }
        logger.logInfo(guest.firstName() + " " + guest.lastName() + " is unhappy, none of their meal preferences are available.");
        return false;
    }

    protected DinnerPortionsMap getOptimalPortions() {

        Map<DinnerType, Integer> optimalPortions = new HashMap<>();
        for (Map.Entry<GuestType, Integer> pair : numberOfRemainingGuestsByType.entrySet()) {


            int average = (int) Math.ceil((double) pair.getValue() / cyclesLeft);
//            if (average >= unhappinessCost) {
//                logger.logInfo(pair.getKey().getRandomDinnerType(pair.getKey()) + "; " + average);
//                optimalPortions.put(pair.getKey().getRandomDinnerType(pair.getKey()), average);
//            }
            optimalPortions.put(pair.getKey().getRandomDinnerType(pair.getKey()), average);
        }
        logger.logInfo("Today's portions: " + optimalPortions + ".");

        System.out.println("\n");
        return new DinnerPortionsMap(optimalPortions);
    }
}