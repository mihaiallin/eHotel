package com.codecool.ehotel.model.dinner;

public class Ingredient {
    private String name;
    private String freshness;
    private String expiryDate;

    public Ingredient(String name, String freshness, String expiryDate) {
        this.name = name;
        this.freshness = freshness;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public String getFreshness() {
        return freshness;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
