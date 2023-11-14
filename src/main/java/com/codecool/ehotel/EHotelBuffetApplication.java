package com.codecool.ehotel;

import com.codecool.ehotel.service.InputReader;
import com.codecool.ehotel.service.manager.EHotelManager;

public class
EHotelBuffetApplication {

    public static void main(String[] args) {

        InputReader seasonDates = new InputReader();
        seasonDates.obtainDates();

        EHotelManager app = new EHotelManager(seasonDates.getSeasonStart(), seasonDates.getSeasonEnd());
        app.run();
    }
}
