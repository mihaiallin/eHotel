package com.codecool.ehotel.model.guest;

import java.time.LocalDate;

public record Guest(String firstName, String lastName, GuestType guestType, LocalDate checkIn, LocalDate checkOut) {
    public LocalDate checkIn() {
        return checkIn;
    }

    public LocalDate checkOut() {
        return checkOut;
    }

    public GuestType getGuestType() {
        return guestType;
    }
}
