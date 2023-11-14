package com.codecool.ehotel.model;

import com.codecool.ehotel.model.breakfast.BreakfastPortion;
import com.codecool.ehotel.model.breakfast.BreakfastType;
import com.codecool.ehotel.model.dinner.DinnerPortion;
import com.codecool.ehotel.model.dinner.DinnerType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Buffet {
    private final List<BreakfastPortion> breakfastPortions;
    private final List<DinnerPortion> dinnerPortions;

    public Buffet() {
        dinnerPortions = new ArrayList<>();
        breakfastPortions = new ArrayList<>();
    }

    public void addPortion(BreakfastType breakfastType, LocalTime timestamp) {
        breakfastPortions.add(new BreakfastPortion(breakfastType, timestamp));
    }

    public void addPortion(DinnerType dinnerType, LocalTime timestamp) {
        dinnerPortions.add(new DinnerPortion(dinnerType, timestamp));
    }

    public List<BreakfastPortion> getBreakfastPortions() {
        return breakfastPortions;
    }

    public List<DinnerPortion> getDinnerPortions() {
        return dinnerPortions;
    }
}