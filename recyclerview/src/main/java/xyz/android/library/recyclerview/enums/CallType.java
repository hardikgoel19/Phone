package xyz.android.library.recyclerview.enums;

import android.provider.CallLog;

import xyz.android.library.recyclerview.R;

public enum CallType {

    INCOMING(CallLog.Calls.INCOMING_TYPE, R.drawable.call_received),
    OUTGOING(CallLog.Calls.OUTGOING_TYPE, R.drawable.call_made),
    MISSED(CallLog.Calls.MISSED_TYPE, R.drawable.call_missed),
    VOICEMAIL(CallLog.Calls.VOICEMAIL_TYPE, R.drawable.call_blocked_rejected),
    REJECTED(CallLog.Calls.REJECTED_TYPE, R.drawable.call_blocked_rejected),
    BLOCKED(CallLog.Calls.BLOCKED_TYPE, R.drawable.call_blocked_rejected),
    UNKNOWN(-1, R.drawable.call_blocked_rejected);

    private final int typeValue;
    private final int drawableResource;

    CallType(int typeValue, int drawableResource) {
        this.typeValue = typeValue;
        this.drawableResource = drawableResource;
    }

    public static CallType getByTypeValue(int typeValue) {
        for (CallType call : values()) {
            if (call.getTypeValue() == typeValue) return call;
        }
        return UNKNOWN;
    }

    public int getTypeValue() {
        return typeValue;
    }

    public int getDrawableResource() {
        return drawableResource;
    }
}