package xyz.phone.providers;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.CallLog;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import xyz.phone.commons.model.Call;

public class CallLogsProvider {

    private static final String DEFAULT_KEY = "Unknown";

    public static Map<String, Set<Call>> getAllGrouped(
            Context context,
            Function<Call, String> groupingFunction,
            Comparator<Call> comparator
    ) {
        Map<String, Set<Call>> map = new HashMap<>();

        //MAKE CURSOR
        Cursor cursor = context.getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                CallLog.Calls.DATE + " DESC"
        );

        //COLUMN INDEX
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int id = cursor.getColumnIndex(BaseColumns._ID);
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int phoneId = cursor.getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_ID);

        while (cursor.moveToNext()) {

            //GET FIELDS
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            String callDuration = cursor.getString(duration);
            String callId = cursor.getString(id);
            String callName = cursor.getString(name);
            String phoneAccountId = cursor.getString(phoneId);

            //SET TO CALL OBJECT
            Call call = new Call();
            call.setNumber(phNumber);
            call.setType(callType);
            call.setCallDate(callDate);
            call.setDuration(callDuration);
            call.setId(callId);
            call.setName(callName);
            call.setPhoneAccountId(phoneAccountId);

            putInMap(groupingFunction.apply(call), call, map, comparator);
        }
        cursor.close();
        return map;
    }

    private static void putInMap(
            String key,
            Call call,
            Map<String, Set<Call>> map,
            Comparator<Call> comparator
    ) {
        if (key == null || key.isEmpty()) key = DEFAULT_KEY;

        Set<Call> temp;
        if (map.containsKey(key)) {
            temp = map.get(key);
            if (temp == null) temp = new TreeSet<>(comparator);
        } else {
            temp = new TreeSet<>(comparator);
        }
        temp.add(call);
        map.put(key, temp);
    }

}
