package com.domRock.atv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static Date convertStringForDate(String dateString){

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;

    }

    public static Double convertStringForDouble(String value) {
        return Double.parseDouble(value.replace(',', '.'));
    }
}
