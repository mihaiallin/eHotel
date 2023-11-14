package com.codecool.ehotel.service.manager;

import com.codecool.ehotel.model.breakfast.BreakfastType;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.guest.Guest;
import com.codecool.ehotel.model.guest.GuestType;
import com.codecool.ehotel.model.breakfast.BreakfastPortionsMap;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealManager {

    protected final BuffetService BuffetService;
    protected final Buffet Buffet;
    protected final Logger logger;
    protected final int unhappinessCost;
    protected final List<List<Guest>> todayGuestsGroups;
    protected final Map<GuestType, Integer> numberOfRemainingGuestsByType;
    protected int foodWasteCost;
    protected int cycles;
    protected int unhappyGuestsSum;
    protected LocalTime timestamp;
    protected int cyclesLeft;

    public MealManager(List<List<Guest>> todayGuestsGroups, LocalTime timestamp) {
        this.foodWasteCost = 0;
        this.unhappyGuestsSum = 0;
        this.unhappinessCost = 1;
        this.timestamp = timestamp;
        this.cycles = 8;
        this.cyclesLeft = cycles;

        this.BuffetService = new BuffetServiceImpl();
        this.Buffet = new Buffet();
        this.numberOfRemainingGuestsByType = new HashMap<>();
        this.logger = new ConsoleLogger();

        this.todayGuestsGroups = todayGuestsGroups;
        for (List<Guest> guestGroup : todayGuestsGroups) {
            for (Guest guest : guestGroup) {
                numberOfRemainingGuestsByType.put(guest.getGuestType(), numberOfRemainingGuestsByType.getOrDefault(guest.getGuestType(), 0) + 1);
            }
        }
    }
}