package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.breakfast.BreakfastType;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.dinner.DinnerType;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.breakfast.BreakfastPortionsList;
import com.codecool.ehotel.model.breakfast.BreakfastPortionsMap;
import com.codecool.ehotel.model.dinner.DinnerPortionsList;
import com.codecool.ehotel.model.dinner.DinnerPortionsMap;

import java.time.LocalTime;

public interface BuffetService {

    void refill(Buffet buffet, LocalTime timestamp, BreakfastPortionsMap breakfastPortionsMap);

    void refill(Buffet buffet, LocalTime timestamp, DinnerPortionsMap dinnerPortionsMap);

    Boolean consumeFreshest(Buffet buffet, BreakfastType breakfastType);

    Boolean consumeFreshest(Buffet buffet, DinnerType dinnerType);

    int collectWaste(BreakfastPortionsList breakfastPortionsList, MealDurability mealDurability, LocalTime timestamp);

    int collectWaste(DinnerPortionsList dinnerPortionsList, MealDurability mealDurability, LocalTime timestamp);
}
