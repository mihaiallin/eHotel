package com.codecool.ehotel.model.breakfast;

import java.time.LocalTime;

public record BreakfastPortion(BreakfastType breakfastType, LocalTime timestamp) {
}