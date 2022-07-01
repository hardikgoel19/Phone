package xyz.phone.providers;

import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import xyz.phone.commons.model.Message;


public class MessagesProvider {

    private static final String DEFAULT_KEY = "Unknown";

    public static Map<String, Set<Message>> getAllGrouped(
            Context context,
            Function<Message, String> groupingFunction,
            Comparator<Message> comparator
    ) {
        Map<String, Set<Message>> map = new HashMap<>();

        //MAKE CURSOR
        Cursor cursor = context.getContentResolver().query(
                Telephony.Sms.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        int idIndex = cursor.getColumnIndexOrThrow(Telephony.Sms._ID);
        int dateIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.DATE);
        int addressIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS);
        int contentIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.BODY);
        int typeIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE);
        int subjectIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.SUBJECT);
        int statusIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.STATUS);
        int readIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.READ);
        int subscriptionIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.SUBSCRIPTION_ID);

        while (cursor.moveToNext()) {

            //GET FIELDS
            String id = cursor.getString(idIndex);
            String date = cursor.getString(dateIndex);
            String address = cursor.getString(addressIndex);
            String content = cursor.getString(contentIndex);
            String type = cursor.getString(typeIndex);
            String subject = cursor.getString(subjectIndex);
            String status = cursor.getString(statusIndex);
            String read = cursor.getString(readIndex);
            String subscriptionId = cursor.getString(subscriptionIndex);

            //SET TO MESSAGE OBJECT
            Message message = new Message();
            message.setId(id);
            message.setMessageDate(date);
            message.setAddress(address);
            message.setContent(content);
            message.setType(type);
            message.setSubject(subject);
            message.setStatus(status);
            message.setRead(read);
            message.setPhoneAccountId(subscriptionId);

            putInMap(groupingFunction.apply(message), message, map, comparator);
        }
        cursor.close();

        return map;
    }

    private static void putInMap(
            String key,
            Message message,
            Map<String, Set<Message>> map,
            Comparator<Message> comparator
    ) {
        if (key == null || key.isEmpty()) key = DEFAULT_KEY;

        Set<Message> temp;
        if (map.containsKey(key)) {
            temp = map.get(key);
            if (temp == null) temp = new TreeSet<>(comparator);
        } else {
            temp = new TreeSet<>(comparator);
        }
        temp.add(message);
        map.put(key, temp);
    }
}

