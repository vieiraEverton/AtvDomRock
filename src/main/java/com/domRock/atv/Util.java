package com.domRock.atv;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static Date convertStringForDate(String dateString){

        Date date = null;
        try {
            date = new SimpleDateFormat("MM/dd/yy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;

    }

    public static Double convertStringForDouble(String value) {
        return Double.parseDouble(value.replace(',', '.'));
    }

    public static String convertDateForString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    public static String decimalFormat(Double num, int casas) {
        String casasString = "";
        for (int i = 0; i < casas; i++){
            casasString = casasString + "#";
        }
        DecimalFormat df = new DecimalFormat("###."+casasString);
        return df.format(num);
    }
}
