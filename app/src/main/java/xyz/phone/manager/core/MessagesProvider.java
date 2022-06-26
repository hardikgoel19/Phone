package xyz.phone.manager.core;

import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;

import java.util.ArrayList;
import java.util.List;

import xyz.phone.manager.model.Message;

public class MessagesProvider {

    public static List<Message> getAll(Context context) {

        List<Message> messages = new ArrayList<>();

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

            //ACCUMULATE MESSAGE
            messages.add(message);
        }
        cursor.close();

        return messages;
    }
}

