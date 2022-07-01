package xyz.phone.manager.viewholder;

import android.content.Intent;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import xyz.phone.commons.model.Call;
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

    public void populateData(String heading, Set<Message> groupedMessages) {
        List<Message> messages = new ArrayList<>(groupedMessages);

        // DISPLAY NAME
        setDisplayName(heading);

        // LAST DATE TIME
        setLastDateTime(messages);

        // LAST MESSAGE
        setLastMessage(messages);

        // HANDLE ON CLICK
        handleOnClick(messages);
    }

    private void setDisplayName(String displayName) {
        if (this.displayName == null) return;

        if (displayName == null || displayName.isEmpty())
            displayName = DEFAULT_DISPLAY_NAME;

        this.displayName.setText(displayName);
    }

    private void setLastDateTime(List<Message> groupedMessages) {
        if (this.lastDateTime == null) return;

        if (groupedMessages == null || groupedMessages.isEmpty()) return;

        //ASSUMING MESSAGES ARE SORTED IN DESC ORDER
        Message message = groupedMessages.get(0);
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

    private void setLastMessage(List<Message> groupedMessages) {
        if (this.lastMessage == null) return;
        if (groupedMessages == null || groupedMessages.isEmpty()) return;

        //ASSUMING MESSAGES ARE SORTED IN DESC ORDER
        Message message = groupedMessages.get(0);
        String lastMessageText = DEFAULT_LAST_MESSAGE;
        if (!(message == null
                || message.getMessageDate() == null
                || message.getMessageDate().isEmpty()
        ))
            lastMessageText = message.getContent();

        this.lastMessage.setText(lastMessageText);
    }

    private void handleOnClick(List<Message> groupedMessages) {
        if (this.mainLayout == null || this.mainLayout.getContext() == null) return;

        ArrayList<Message> list = new ArrayList<>(groupedMessages);

        mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mainLayout.getContext(), ConversationActivity.class);
            intent.putExtra(ConversationActivity.MESSAGES, list);
            mainLayout.getContext().startActivity(intent);
        });
    }
}
