package com.sape.feecalculator.utility;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtility {

   public static Date getDate(String dateStr) throws ParseException {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return  simpleDateFormat.parse(dateStr);

    }
    public static String getStringDate(Date date) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);

    }
}
