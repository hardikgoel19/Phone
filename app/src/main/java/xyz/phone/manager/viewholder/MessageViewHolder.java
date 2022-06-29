package xyz.phone.manager.viewholder;

import android.content.Intent;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.phone.commons.model.Message;
import xyz.phone.commons.utils.Converter;
import xyz.phone.commons.utils.DateTimeUtil;
import xyz.phone.manager.ConversationActivity;
import xyz.phone.manager.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private static final String DEFAULT_DISPLAY_NAME = "Unknown";
    private static final String DEFAULT_LAST_DATE_TIME = "";
    private static final String DEFAULT_LAST_MESSAGE = "";

    private final RelativeLayout mainLayout;
    private final TextView displayName;
    private final TextView lastDateTime;
    private final TextView lastMessage;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mainLayout = itemView.findViewById(R.id.mainLayout);
        this.displayName = itemView.findViewById(R.id.displayName);
        this.lastDateTime = itemView.findViewById(R.id.lastDateTime);
        this.lastMessage = itemView.findViewById(R.id.lastMessage);
    }

    public void setDisplayName(List<Message> messages) {
        if (this.displayName == null) return;
        if (messages == null || messages.isEmpty()) return;

        Message message = messages.get(messages.size() - 1);
        String address = DEFAULT_DISPLAY_NAME;
        if (!(message == null
                || message.getAddress() == null
                || message.getAddress().isEmpty()
        ))
            address = message.getAddress();

        this.displayName.setText(address);
    }

    public void setLastDateTime(List<Message> messages) {
        if (this.lastDateTime == null) return;
        if (messages == null || messages.isEmpty()) return;

        Message message = messages.get(messages.size() - 1);
        long timeInMillis = 0L;
        if (!(message == null
                || message.getMessageDate() == null
                || message.getMessageDate().isEmpty()
        ))
            timeInMillis = Converter.toLong(message.getMessageDate());

        if (timeInMillis == 0L) {
            this.lastDateTime.setText(DEFAULT_LAST_DATE_TIME);
        } else {
            String formattedDateTime = DateTimeUtil.getFormattedTimestamp(timeInMillis);
            this.lastDateTime.setText(formattedDateTime);
        }
    }

    public void setLastMessage(List<Message> messages) {
        if (this.lastMessage == null) return;
        if (messages == null || messages.isEmpty()) return;

        Message message = messages.get(messages.size() - 1);
        String lastMessageText = DEFAULT_LAST_MESSAGE;
        if (!(message == null
                || message.getMessageDate() == null
                || message.getMessageDate().isEmpty()
        ))
            lastMessageText = message.getContent();

        this.lastMessage.setText(lastMessageText);
    }

    public void handleOnClick(List<Message> groupedMessages) {
        if (this.mainLayout == null || this.mainLayout.getContext() == null) return;

        ArrayList<Message> list = new ArrayList<>(groupedMessages);

        mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mainLayout.getContext(), ConversationActivity.class);
            intent.putExtra(ConversationActivity.MESSAGES, list);
            mainLayout.getContext().startActivity(intent);
        });
    }
}
