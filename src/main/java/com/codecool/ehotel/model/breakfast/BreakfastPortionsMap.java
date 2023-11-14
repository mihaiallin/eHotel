package com.codecool.ehotel.model.breakfast;

import java.util.Map;

public record BreakfastPortionsMap(Map<BreakfastType, Integer> breakfastPortions) {
}
