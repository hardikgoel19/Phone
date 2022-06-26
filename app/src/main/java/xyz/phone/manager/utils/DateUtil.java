package xyz.phone.manager.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    //YYYY-MMM-DD HH:mm AM/PM
    private static final String DATE_FORMAT = "%04d-%s-%02d %02d:%02d %s";

    private static final String[] MONTHS = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    private static final String AM = "AM";
    private static final String PM = "PM";

    public static String getFormattedDate(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timeInMillis));
        return String.format(Locale.US,
                DATE_FORMAT,
                calendar.get(Calendar.YEAR),
                getDisplayMonth(calendar.get(Calendar.MONTH)),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                getDisplayAMorPM(calendar.get(Calendar.AM_PM))
        );
    }

    private static String getDisplayAMorPM(int index) {
        if (Calendar.AM == index) return AM;
        else if (Calendar.PM == index) return PM;
        else return "";
    }

    private static String getDisplayMonth(int monthIndex) {
        if (monthIndex >= 0 && monthIndex < MONTHS.length)
            return MONTHS[monthIndex];
        else
            return MONTHS[0];
    }
}
