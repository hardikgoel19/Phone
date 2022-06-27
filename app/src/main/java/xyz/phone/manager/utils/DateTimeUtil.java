package xyz.phone.manager.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

    private static class DateTimeDifference {
        public long inYears, inDays;
        public long inHours, inMinutes, inSeconds, inMillis;

        public DateTimeDifference(long inYears, long inDays, long inHours, long inMinutes, long inSeconds, long inMillis) {
            this.inYears = inYears;
            this.inDays = inDays;
            this.inHours = inHours;
            this.inMinutes = inMinutes;
            this.inSeconds = inSeconds;
            this.inMillis = inMillis;
        }
    }

    //YYYY-MMM-DD HH:mm AM/PM
    private static final String DATE_FORMAT = "%04d-%s-%02d %02d:%02d %s";

    private static final String[] MONTHS = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    private static final String AM = "AM";
    private static final String PM = "PM";

    public static String getFormattedDate(long timeInMillis) {
        //ORIGINAL TIMESTAMP
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timeInMillis));

        //DIFFERENCE
        DateTimeDifference difference = getDifference(
                (new Date()).getTime(),
                calendar.getTimeInMillis()
        );

        //IN MILLIS
        if(difference.inSeconds <= 30) {
            return "Just Now";
        } else if (difference.inSeconds <= 60) {
            return difference.inSeconds + " Seconds";
        }

        //IN MINUTES
        if(difference.inMinutes <= 60) {
            return difference.inMinutes + " Minutes";
        }

        //IN HOURS
        if(difference.inHours <= 24) {
            return difference.inHours + " Hours";
        }

        //IN DAYS
        if(difference.inDays <= 30) {
            return difference.inDays + " Days";
        }

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

    private static DateTimeDifference getDifference(long millisFrom, long millisTo) {
        long millis = (long) (millisFrom - millisTo);
        long seconds = (millis / 1000L);
        long minutes = (millis / 60000L);
        long hours = (millis / 3600000L);
        long days = (millis / (1000 * 60 * 60 * 24));
        long years = (millis / (1000L * 60 * 60 * 24 * 365));
        return new DateTimeDifference(years, days, hours, minutes, seconds, millis);
    }
}
