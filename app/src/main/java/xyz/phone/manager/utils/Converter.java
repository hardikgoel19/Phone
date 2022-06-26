package xyz.phone.manager.utils;

import android.util.Log;

public class Converter {

    private static final String TAG = "Converter";
    private static final Long FAILED_LONG = -555L;
    private static final Integer FAILED_INTEGER = -555;

    public static Long toLong(String stringData) {
        long longData = FAILED_LONG;

        try {
            longData = Long.parseLong(stringData);
        } catch (NumberFormatException ex) {
            Log.d(TAG, "toLong: failed to convert from String to Long: " + stringData);
        }

        return longData;
    }

    public static Integer toInteger(String stringData) {
        int intData = FAILED_INTEGER;

        try {
            intData = Integer.parseInt(stringData);
        } catch (NumberFormatException ex) {
            Log.d(TAG, "toLong: failed to convert from String to Integer: " + stringData);
        }

        return intData;
    }
}
