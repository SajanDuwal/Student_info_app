package com.sajiman.jasonapp.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateConverterHelper {

    private static final String CLASS_TAG = DateConverterHelper.class.getSimpleName();
    private Map<Integer, int[]> bs = new HashMap<>();
    private int nepaliYear, nepaliMonth, nepaliDay,
            englishYear, englishMonth, englishDay;
    private int convertedNubDay, convertedYear, convertedMonth, convertedDate;
    private String convertedDay, convertedNepMonth;

    public DateConverterHelper() {
        bs.put(0, new int[]{2000, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(1, new int[]{2001, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(2, new int[]{2002, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(3, new int[]{2003, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(4, new int[]{2004, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(5, new int[]{2005, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(6, new int[]{2006, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(7, new int[]{2007, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(8, new int[]{2008, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        bs.put(9, new int[]{2009, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(10, new int[]{2010, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(11, new int[]{2011, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(12, new int[]{2012, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        bs.put(13, new int[]{2013, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(14, new int[]{2014, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(15, new int[]{2015, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(16, new int[]{2016, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        bs.put(17, new int[]{2017, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(18, new int[]{2018, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(19, new int[]{2019, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(20, new int[]{2020, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(21, new int[]{2021, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(22, new int[]{2022, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        bs.put(23, new int[]{2023, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(24, new int[]{2024, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(25, new int[]{2025, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(26, new int[]{2026, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(27, new int[]{2027, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(28, new int[]{2028, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(29, new int[]{2029, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        bs.put(30, new int[]{2030, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(31, new int[]{2031, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(32, new int[]{2032, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(33, new int[]{2033, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(34, new int[]{2034, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(35, new int[]{2035, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        bs.put(36, new int[]{2036, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(37, new int[]{2037, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(38, new int[]{2038, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(39, new int[]{2039, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        bs.put(40, new int[]{2040, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(41, new int[]{2041, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(42, new int[]{2042, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(43, new int[]{2043, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        bs.put(44, new int[]{2044, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(45, new int[]{2045, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(46, new int[]{2046, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(47, new int[]{2047, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(48, new int[]{2048, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(49, new int[]{2049, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        bs.put(50, new int[]{2050, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(51, new int[]{2051, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(52, new int[]{2052, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(53, new int[]{2053, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        bs.put(54, new int[]{2054, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(55, new int[]{2055, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(56, new int[]{2056, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        bs.put(57, new int[]{2057, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(58, new int[]{2058, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(59, new int[]{2059, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(60, new int[]{2060, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(61, new int[]{2061, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(62, new int[]{2062, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
        bs.put(63, new int[]{2063, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(64, new int[]{2064, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(65, new int[]{2065, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(66, new int[]{2066, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        bs.put(67, new int[]{2067, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(68, new int[]{2068, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(69, new int[]{2069, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(70, new int[]{2070, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        bs.put(71, new int[]{2071, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(72, new int[]{2072, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        bs.put(73, new int[]{2073, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        bs.put(74, new int[]{2074, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(75, new int[]{2075, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(76, new int[]{2076, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        bs.put(77, new int[]{2077, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bs.put(78, new int[]{2078, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(79, new int[]{2079, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        bs.put(80, new int[]{2080, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        bs.put(81, new int[]{2081, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        bs.put(82, new int[]{2082, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        bs.put(83, new int[]{2083, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        bs.put(84, new int[]{2084, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        bs.put(85, new int[]{2085, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        bs.put(86, new int[]{2086, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        bs.put(87, new int[]{2087, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
        bs.put(88, new int[]{2088, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        bs.put(89, new int[]{2089, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        bs.put(90, new int[]{2090, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});

    }

    public boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (year % 4 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String getNepaliMonth(int month) {
        String nepaliMonth = "";
        switch (month) {
            case 1:
                nepaliMonth = "Baishak";
                break;

            case 2:
                nepaliMonth = "Jestha";
                break;

            case 3:
                nepaliMonth = "Ashad";
                break;

            case 4:
                nepaliMonth = "Shrawn";
                break;

            case 5:
                nepaliMonth = "Bhadra";
                break;

            case 6:
                nepaliMonth = "Ashwin";
                break;

            case 7:
                nepaliMonth = "kartik";
                break;

            case 8:
                nepaliMonth = "Mangshir";
                break;

            case 9:
                nepaliMonth = "Poush";
                break;

            case 10:
                nepaliMonth = "Magh";
                break;

            case 11:
                nepaliMonth = "Falgun";
                break;

            case 12:
                nepaliMonth = "Chaitra";
                break;
        }
        return nepaliMonth;
    }

    public String getEnglishMonth(int month) {
        String englishMonth = "";
        switch (month) {
            case 1:
                englishMonth = "January";
                break;
            case 2:
                englishMonth = "February";
                break;
            case 3:
                englishMonth = "March";
                break;
            case 4:
                englishMonth = "April";
                break;
            case 5:
                englishMonth = "May";
                break;
            case 6:
                englishMonth = "June";
                break;
            case 7:
                englishMonth = "July";
                break;
            case 8:
                englishMonth = "August";
                break;
            case 9:
                englishMonth = "September";
                break;
            case 10:
                englishMonth = "October";
                break;
            case 11:
                englishMonth = "November";
                break;
            case 12:
                englishMonth = "December";
        }
        return englishMonth;
    }

    public String getDayOfWeek(int day) {
        String dayOfWeek = "";
        switch (day) {
            case 1:
                dayOfWeek = "Sunday";
                break;

            case 2:
                dayOfWeek = "Monday";
                break;

            case 3:
                dayOfWeek = "Tuesday";
                break;

            case 4:
                dayOfWeek = "Wednesday";
                break;

            case 5:
                dayOfWeek = "Thursday";
                break;

            case 6:
                dayOfWeek = "Friday";
                break;

            case 7:
                dayOfWeek = "Saturday";
                break;
        }
        return dayOfWeek;
    }

    public boolean isEngDateInRange(int yy, int mm, int dd) {
        return !(yy < 1944 || yy > 2033) && !(mm < 1 || mm > 12) && !(dd < 1 || dd > 31);
    }

    public boolean isNepDateInRange(int year, int month, int day) {
        return !(year < 2000 || year > 2089) && !(month < 1 || month > 12) && !(day < 1 || day > 32);
    }

    public void engToNep(int yy, int mm, int dd) {
        if (!isEngDateInRange(yy, mm, dd)) {
            return;
        }

        int[] month = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] lmonth = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int def_eyy = 1944;                                    //spear head english date...
        int def_nyy = 2000;
        int def_nmm = 9;
        int def_ndd = 17 - 1;        //spear head nepali date...
        int total_eDays = 0;
        int total_nDays = 0;
        int a = 0;
        int day = 7 - 1;        //all the initializations...
        int m = 0;
        int y = 0;
        int numDay = 0;

        // count total no. of days in-terms of year
        for (int k = 0; k < (yy - def_eyy); k++) {    //total days for month calculation...(english)
            if (isLeapYear(def_eyy + k)) {
                for (int l = 0; l < 12; l++) {
                    total_eDays += lmonth[l];
                }
            } else {
                for (int l = 0; l < 12; l++) {
                    total_eDays += month[l];
                }
            }
        }

        // count total no. of days in-terms of month
        for (int i = 0; i < (mm - 1); i++) {
            if (isLeapYear(yy))
                total_eDays += lmonth[i];
            else
                total_eDays += month[i];
        }

        //count total no. of days in terms of date
        total_eDays += dd;

        total_nDays = def_ndd;
        m = def_nmm;
        y = def_nyy;
        int j = def_nmm;
        int i = 0;
        //count nepali date from array

        while (total_eDays != 0) {
            a = bs.get(i)[j];
            total_nDays++;
            day++;
            if (total_nDays > a) {
                m++;
                total_nDays = 1;
                j++;
            }

            if (day > 7) {
                day = 1;
            }

            if (m > 12) {
                y++;
                m = 1;
            }

            if (j > 12) {
                j = 1;
                i++;
            }
            total_eDays--;
        }

        numDay = day;
        convertedYear = y;
        convertedMonth = m;
        convertedDate = total_nDays;
        convertedDay = getDayOfWeek(day);
        convertedNepMonth = getNepaliMonth(m);
        convertedNubDay = numDay;
    }


    public void nepToEng(int yy, int mm, int dd) {


        int def_eyy = 1943;
        int def_emm = 4;
        int def_edd = 14 - 1;        // init english date.
        int def_nyy = 2000;
        int def_nmm = 1;
        int def_ndd = 1;        // equivalent nepali date.
        int total_eDays = 0;
        int total_nDays = 0;
        int a = 0;
        int day = 4 - 1;        // initializations...
        int m = 0;
        int y = 0;
        int i = 0;
        int k = 0;
        int numDay = 0;

        int[] month = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] lmonth = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isNepDateInRange(yy, mm, dd)) {
            // count total days in-terms of year
            for (i = 0; i < (yy - def_nyy); i++) {
                for (int j = 1; j <= 12; j++) {
                    total_nDays += bs.get(k)[j];
                }
                k++;
            }

            // count total days in-terms of month
            for (int j = 1; j < mm; j++) {
                total_nDays += bs.get(k)[j];
            }

            // count total days in-terms of dat
            total_nDays += dd;

            //calculation of equivalent english date...
            total_eDays = def_edd;
            m = def_emm;
            y = def_eyy;
            while (total_nDays != 0) {
                if (isLeapYear(y)) {
                    a = lmonth[m];
                } else {
                    a = month[m];
                }
                total_eDays++;
                day++;
                if (total_eDays > a) {
                    m++;
                    total_eDays = 1;
                    if (m > 12) {
                        y++;
                        m = 1;
                    }
                }
                if (day > 7)
                    day = 1;
                total_nDays--;
            }
            numDay = day;

            convertedYear = y;
            convertedMonth = m;
            convertedDate = total_eDays;
            convertedDay = getDayOfWeek(day);
            convertedNepMonth = getEnglishMonth(m);
            convertedNubDay = numDay;
        }
    }

    public int numberOfDaysInMonth(int year, int month) {
        for (int i = 0; i < bs.size(); i++) {
            int[] value = bs.get(i);
            for (int y : value) {
                if (y == year) {
                    return value[month];
                }
            }
        }
        return 0;
    }

    public int getConvertedNubDay() {
        return this.convertedNubDay;
    }

    public int getConvertedYear() {
        return convertedYear;
    }

    public int getConvertedMonth() {
        return convertedMonth;
    }

    public int getConvertedDate() {
        return convertedDate;
    }

    public String getDifferenceInString(Date currentDate, Date birthDate) {
//        long diff = Math.abs(currentDate.getTime() - birthDate.getTime());
        long diff = currentDate.getTime() - birthDate.getTime();

        long milliSecond = diff % 1000;
        long seconds = diff / 1000;

        long minutes = seconds / 60;
        seconds = seconds % 60;

        long hour = minutes / 60;
        minutes = minutes % 60;

        long day = hour / 24;
        hour = hour % 24;

        long month = day / 30;
        day = day % 30;

        return month + " &#2350;. " + day + " &#2342;&#2367;.";
    }

    public long[] getAge(Date currentDate, Date birthDate) {
        long diff = currentDate.getTime() - birthDate.getTime();

        long milliSecond = diff % 1000;
        long seconds = diff / 1000;

        long minutes = seconds / 60;
        seconds = seconds % 60;

        long hour = minutes / 60;
        minutes = minutes % 60;

        long day = hour / 24;
        hour = hour % 24;

        long month = day / 30;
        day = day % 30;
        return new long[]{month, day};
    }
}