package xyz.phone.manager.enums;

import android.provider.Telephony;

import xyz.phone.manager.R;

public enum MessageType {
    RECEIVED(Telephony.Sms.MESSAGE_TYPE_INBOX, R.layout.item_conversation_left, ""),
    DRAFT(Telephony.Sms.MESSAGE_TYPE_DRAFT, R.layout.item_conversation_right, "Draft"),
    SENT(Telephony.Sms.MESSAGE_TYPE_SENT, R.layout.item_conversation_right, ""),
    OUTBOX(Telephony.Sms.MESSAGE_TYPE_OUTBOX, R.layout.item_conversation_right, "Outbox"),
    QUEUED(Telephony.Sms.MESSAGE_TYPE_QUEUED, R.layout.item_conversation_right, "Queued"),
    FAILED(Telephony.Sms.MESSAGE_TYPE_FAILED, R.layout.item_conversation_right, "Failed"),
    ALL(Telephony.Sms.MESSAGE_TYPE_FAILED, R.layout.item_conversation_left, "");

    private final int code;
    private final int layoutId;
    private final String displayTypeString;

    MessageType(int code, int layoutId, String displayTypeString) {
        this.code = code;
        this.layoutId = layoutId;
        this.displayTypeString = displayTypeString;
    }

    public int getCode() {
        return code;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public String getDisplayTypeString() {
        return displayTypeString;
    }

    public static MessageType getByTypeValue(int typeValue) {
        for (MessageType message : values()) {
            if (message.getCode() == typeValue) return message;
        }
        return ALL;
    }
}
