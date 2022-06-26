package xyz.phone.manager.core;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.CallLog;

import java.util.ArrayList;
import java.util.List;

import xyz.phone.manager.model.Call;

public class CallLogsProvider {

    public static List<Call> getAll(Context context) {
        List<Call> calls = new ArrayList<>();

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

            //ACCUMULATE CALL
            calls.add(call);
        }
        cursor.close();
        return calls;
    }

}
