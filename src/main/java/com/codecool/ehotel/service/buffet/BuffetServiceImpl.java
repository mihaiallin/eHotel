package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.breakfast.BreakfastPortion;
import com.codecool.ehotel.model.breakfast.BreakfastPortionsList;
import com.codecool.ehotel.model.breakfast.BreakfastPortionsMap;
import com.codecool.ehotel.model.breakfast.BreakfastType;
import com.codecool.ehotel.model.dinner.DinnerPortion;
import com.codecool.ehotel.model.dinner.DinnerPortionsList;
import com.codecool.ehotel.model.dinner.DinnerPortionsMap;
import com.codecool.ehotel.model.dinner.DinnerType;
import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codecool.ehotel.model.MealDurability.MEDIUM;
import static com.codecool.ehotel.model.MealDurability.SHORT;

public class BuffetServiceImpl implements BuffetService {

    private final Logger logger;

    public BuffetServiceImpl() {
        logger = new ConsoleLogger();
    }

    @Override
    public void refill(Buffet buffet, LocalTime timestamp, BreakfastPortionsMap breakfastPortionsMap) {
        for (Map.Entry<BreakfastType, Integer> entry : breakfastPortionsMap.breakfastPortions().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                buffet.addPortion(entry.getKey(), timestamp);
            }
        }
        //logger.logInfo("Buffet has been refilled at " + timestamp + ".");
    }

    @Override
    public void refill(Buffet buffet, LocalTime timestamp, DinnerPortionsMap dinnerPortionsMap) {
        for (Map.Entry<DinnerType, Integer> entry : dinnerPortionsMap.dinnerPortions().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                buffet.addPortion(entry.getKey(), timestamp);
            }
        }
        //logger.logInfo("Buffet has been refilled at " + timestamp + ".");
    }

    @Override
    public Boolean consumeFreshest(Buffet buffet, BreakfastType breakfastType) {
        List<BreakfastPortion> meals = buffet.getBreakfastPortions();
        for (int i = meals.size() - 1; i >= 0; i--) {
            //logger.logInfo("!!!!!!!!!!!!!!!!!TEEEEEEEEST: " + (meals.get(i).breakfastType() == breakfastType));
            if (meals.get(i).breakfastType() == breakfastType) {
                meals.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean consumeFreshest(Buffet buffet, DinnerType dinnerType) {
        List<DinnerPortion> meals = buffet.getDinnerPortions();
        for (int i = meals.size() - 1; i >= 0; i--) {
            if (meals.get(i).dinnerType() == dinnerType) {
                meals.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int collectWaste(BreakfastPortionsList breakfastPortionsList, MealDurability mealDurability, LocalTime timestamp) {
        int sum = 0;
        List<BreakfastPortion> breakfastPortionsCopy = new ArrayList<>(breakfastPortionsList.breakfastPortions());
        for (BreakfastPortion meal : breakfastPortionsCopy) {
            if (meal.breakfastType().getDurability() == SHORT && mealDurability == SHORT) {
                if (timestamp.equals(LocalTime.of(9, 30)) || timestamp.equals(meal.timestamp().plusMinutes(90))) {
                    sum += meal.breakfastType().getCost();
                    logger.logInfo(meal.breakfastType() + " (durability SHORT) has been removed.");
                    breakfastPortionsList.breakfastPortions().remove(meal);
                }
            } else if (meal.breakfastType().getDurability() == MEDIUM && mealDurability == MEDIUM) {
                sum += meal.breakfastType().getCost();
                logger.logInfo(meal.breakfastType() + " (durability MEDIUM) " + " has been removed.");
                breakfastPortionsList.breakfastPortions().remove(meal);
            }
        }
        return sum;
    }

    @Override
    public int collectWaste(DinnerPortionsList dinnerPortionsList, MealDurability mealDurability, LocalTime timestamp) {
        int sum = 0;
        List<DinnerPortion> dinnerPortionsCopy = new ArrayList<>(dinnerPortionsList.dinnerPortions());
        for (DinnerPortion meal : dinnerPortionsCopy) {
            if (meal.dinnerType().getDurability() == SHORT && mealDurability == SHORT) {
                if (timestamp.equals(LocalTime.of(9, 30)) || timestamp.equals(meal.timestamp().plusMinutes(90))) {
                    sum += meal.dinnerType().getCost();
                    logger.logInfo(meal.dinnerType() + " (durability SHORT) " + " has been removed.");
                    dinnerPortionsList.dinnerPortions().remove(meal);
                }
            } else if (meal.dinnerType().getDurability() == MEDIUM && mealDurability == MEDIUM) {
                sum += meal.dinnerType().getCost();
                logger.logInfo(meal.dinnerType() + " has been removed.");
                dinnerPortionsList.dinnerPortions().remove(meal);
            }
        }
        return sum;
    }
}