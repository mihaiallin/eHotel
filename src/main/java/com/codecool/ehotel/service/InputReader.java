package com.codecool.ehotel.service;

import com.codecool.ehotel.service.logger.ConsoleLogger;
import com.codecool.ehotel.service.logger.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class InputReader {

    LocalDate seasonStart;
    LocalDate seasonEnd;

    public InputReader() {
        this.seasonStart = null;
        this.seasonEnd = null;
    }

    public void obtainDates() {
        Logger logger = new ConsoleLogger();
        logger.logInfo("\n\n------WELCOME------\n");

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean isValidInput = false;
//
//        try {
//            logger.logInfo("Enter season starting date (DD-MM-YYYY): ");
//            String seasonStartInput = scanner.nextLine();
//            seasonStart = LocalDate.parse(seasonStartInput, formatter);
//        } catch (Exception e) {
//            logger.logError("Invalid input: " + e.getMessage());
//        }
//
//        do {
//            try {
//                logger.logInfo("Enter season ending date (DD-MM-YYYY): ");
//                String seasonEndInput = scanner.nextLine();
//                seasonEnd = LocalDate.parse(seasonEndInput, formatter);
//
//                long daysBetween = ChronoUnit.DAYS.between(seasonStart, seasonEnd);
//                if (daysBetween > 7) {
//                    throw new IllegalArgumentException("The difference between the start and end dates cannot be more than 7 days.");
//                }
//
//                isValidInput = true;
//            } catch (Exception e) {
//                logger.logError("Invalid input: " + e.getMessage());
//            }
//        } while (!isValidInput);

        seasonStart = LocalDate.of(2023, 7, 29);
        seasonEnd = LocalDate.of(2023, 8, 3);

        logger.logInfo("Season starts on " + seasonStart + " and ends on " + seasonEnd + ".");
    }

    public LocalDate getSeasonStart() {
        return seasonStart;
    }

    public LocalDate getSeasonEnd() {
        return seasonEnd;
    }
}
