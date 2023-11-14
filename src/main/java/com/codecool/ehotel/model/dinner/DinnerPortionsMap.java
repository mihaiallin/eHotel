package com.codecool.ehotel.model.dinner;

import java.util.Map;

public record DinnerPortionsMap(Map<DinnerType, Integer> dinnerPortions) {
}
