package com.codecool.ehotel.model.dinner;

import java.time.LocalTime;

public record DinnerPortion(DinnerType dinnerType, LocalTime timestamp) {
}
