package com.sajiman.jasonapp.Utils;

import java.util.Calendar;

public class DateUtils {

    public static String getCurrentNepaliYear() {
        Calendar calendar = Calendar.getInstance();
        int engYear = calendar.get(Calendar.YEAR);

        int engMonth = calendar.get(Calendar.MONTH);

        int engDay = calendar.get(Calendar.DAY_OF_MONTH);

        DateConverterHelper helper = new DateConverterHelper();
        helper.engToNep(engYear, engMonth + 1, engDay);
        return helper.getConvertedYear() + "";
    }
}
