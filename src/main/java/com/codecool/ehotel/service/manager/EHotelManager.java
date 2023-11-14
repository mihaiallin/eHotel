package com.codecool.ehotel.service.manager;

import com.codecool.ehotel.model.guest.Guest;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EHotelManager {

    private final LocalDate seasonStart;
    private final LocalDate seasonEnd;

    public EHotelManager(LocalDate seasonStart, LocalDate seasonEnd) {
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
    }

    public void run () {
        GuestService guestService = new GuestServiceImpl(seasonStart, seasonEnd);

        LocalDate currentDate = seasonStart;

        while (!currentDate.isAfter(seasonEnd)) {
            List<List<Guest>> todayGuestsGroups = guestService.splitGuestsIntoCycles(8);

            BreakfastManager breakfastManager = new BreakfastManager(todayGuestsGroups, LocalTime.of(6,0));
            DinnerManager dinnerManager = new DinnerManager(todayGuestsGroups, LocalTime.of(18,0));
            Logger logger = new ConsoleLogger();

            System.out.println("\n--------------------------------------------------------------------\n");
            System.out.println("\nSEASON DAY " + currentDate + " START.");
            logger.logInfo("Breakfast serving starts now!");
            logger.logInfo("Today's guests: " + todayGuestsGroups);
            System.out.println("\n");

            breakfastManager.serve();

            System.out.println("\n--------------------------------------------------------------------");
            logger.logInfo("Dinner serving starts now!");
            System.out.println("\n");

            dinnerManager.serve();

            System.out.println("\n-------------------------------");
            System.out.println("\nSEASON DAY " + currentDate + " ENDED.");
            System.out.println("\n-------------------------------");

            currentDate = currentDate.plusDays(1);
        }
    }
}
