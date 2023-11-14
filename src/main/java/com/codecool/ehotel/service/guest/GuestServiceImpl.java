package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.guest.Guest;
import com.codecool.ehotel.model.guest.GuestType;
import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;


public class GuestServiceImpl implements GuestService {
    private static final String[] FIRST_NAMES = {"Adrian", "Alexandra", "Alina", "Andrei", "Ana", "Angela", "Antonia", "Aurelia", "Bogdan", "Carmen", "Catalin", "Cristina", "Daniel", "Delia", "Dorin", "Elena", "Florentina", "Gabriel", "Georgiana", "Horatiu", "Ileana", "Ion", "Irina", "Laura", "Lucian", "Mariana", "Mihaela", "Nicolae", "Oana", "Octavia", "Paula", "Radu", "Roxana", "Sorin", "Simona", "Tudor", "Valentina", "Victor", "Viorica"};
    private static final String[] LAST_NAMES = {"Popescu", "Ionescu", "Stan", "Dumitrescu", "Stoica", "Popa", "Andrei", "Radu", "Mihai", "Marin", "Diaconu", "Neagu", "Gheorghe", "Constantinescu", "Dumitru", "Grigore", "Petrescu", "Vasile", "Ion", "Enache", "Cojocaru", "Andreescu", "Dragomir", "Marin", "Chiriac", "Georgescu", "Serban", "Barbu", "Dobre", "Dumitrache", "Florea", "Munteanu", "Ilie", "Păun", "Dima", "Bălănescu", "Stanciu", "Stanescu", "Iancu", "Lupu"};
    private final List<Guest> guests;
    private final Logger logger;
    public GuestServiceImpl(LocalDate seasonStart, LocalDate seasonEnd) {
        this.guests = new ArrayList<>();
        this.logger = new ConsoleLogger();
        for (int i = 0; i < 40; i++) {
            generateRandomGuest(seasonStart, seasonEnd);
        }
        logger.logInfo("List of all guests: " + guests);
    }

    @Override
    public void generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        String randomFirstName = getRandomName(FIRST_NAMES);
        String randomLastName = getRandomName(LAST_NAMES);
        GuestType randomGuestType = getRandomGuestType();

        int randomStayLength = (int) (Math.random() * 6) + 1;
        LocalDate checkIn = getRandomCheckInDate(seasonStart, seasonEnd, randomStayLength);
        LocalDate checkOut = checkIn.plusDays(randomStayLength - 1);

        if (checkOut.getMonthValue() > checkIn.getMonthValue() || checkOut.getDayOfMonth() == seasonEnd.getDayOfMonth()) {
            checkOut = checkIn.with(TemporalAdjusters.lastDayOfMonth());
        }
        guests.add(new Guest(randomFirstName, randomLastName, randomGuestType, checkIn, checkOut));
    }

    private String getRandomName(String[] list) {
        int randomIndex = (int) (Math.random() * list.length);
        return list[randomIndex];
    }

    private GuestType getRandomGuestType() {
        GuestType[] guestTypes = GuestType.values();
        int randomIndex = (int) (Math.random() * guestTypes.length);
        return guestTypes[randomIndex];
    }

    private LocalDate getRandomCheckInDate(LocalDate seasonStart, LocalDate seasonEnd, int randomStayLength) {
        int seasonLength = (int) (ChronoUnit.DAYS.between(seasonStart, seasonEnd) + 1);
        int maxCheckInDay = seasonLength - randomStayLength;
        int randomCheckInDay = (int) (Math.random() * maxCheckInDay);
        return seasonStart.plusDays(randomCheckInDay);
    }

    @Override
    public List<Guest> getGuestsForDay(LocalDate date) {
        List<Guest> guestForDay = new ArrayList<>();
        for (Guest guest : guests) {
            if (!date.isBefore(guest.checkIn()) && !date.isAfter(guest.checkOut())) {
                guestForDay.add(guest);
            }
        }
        logger.logInfo("Guests for day " + date + " are: " + guestForDay);
        return guestForDay;
    }

    @Override
    public List<List<Guest>> splitGuestsIntoCycles(int cycles) {
        List<List<Guest>> guestGroups = new ArrayList<>();
        for (int i = 0; i < cycles; i++) {
            guestGroups.add(new ArrayList<>());
            for (int j = 0; j < Math.ceil((double) guests.size() /cycles); j++) {
                guestGroups.get(i).add(guests.get(i + j));
            }
        }
        //logger.logInfo("Groups of guests per cycle are: " + guestGroups);
        return guestGroups;
    }
}
