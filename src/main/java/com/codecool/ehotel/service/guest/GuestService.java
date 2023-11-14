package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.guest.Guest;

import java.time.LocalDate;
import java.util.List;

public interface GuestService {

    void generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd);

    List<Guest> getGuestsForDay(LocalDate date);

    List<List<Guest>> splitGuestsIntoCycles(int cycles);
}
