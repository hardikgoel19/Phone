package xyz.phone.manager.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyz.phone.manager.R;
import xyz.phone.manager.enums.MessageType;
import xyz.phone.manager.model.Message;
import xyz.phone.manager.utils.Converter;
import xyz.phone.manager.utils.DateTimeUtil;

public class ConversationViewHolder extends RecyclerView.ViewHolder {

    private static final String DISPLAY_SIM_FORMAT = "Sim %s";

    private final LinearLayout mainLayout;
    private final TextView content;
    private final TextView dateTime;
    private final TextView simCard;
    private final TextView type;

    public ConversationViewHolder(@NonNull View itemView) {
        super(itemView);
        mainLayout = itemView.findViewById(R.id.mainLayout);
        content = itemView.findViewById(R.id.content);
        dateTime = itemView.findViewById(R.id.dateTime);
        simCard = itemView.findViewById(R.id.simCard);
        type = itemView.findViewById(R.id.type);
    }

    public void setContent(Message message) {
        if (this.content == null || message == null) return;

        String content = message.getContent();
        if (content == null || content.isEmpty()) return;

        this.content.setText(content);
    }

    public void setDateTime(Message message) {
        if (this.dateTime == null || message == null) return;

        String dateTime = message.getMessageDate();
        if (dateTime == null || dateTime.isEmpty()) return;

        long dateTimeMillis = Converter.toLong(dateTime);
        if (dateTimeMillis == 0L) return;

        String dateTimeFormatted = DateTimeUtil.getFormattedDate(dateTimeMillis);
        if (dateTimeFormatted.isEmpty()) return;

        this.dateTime.setText(dateTimeFormatted);
    }

    public void setSimCard(Message message) {
        if (this.simCard == null || message == null) return;

        String simCard = message.getPhoneAccountId();
        if (simCard == null || simCard.isEmpty()) return;

        this.simCard.setText(String.format(DISPLAY_SIM_FORMAT, simCard));
    }

    public void setStatus(Message message) {
        if (this.type == null || message == null) return;

        String type = message.getType();
        if (type == null || type.isEmpty()) return;

        Integer typeInt = Converter.toInteger(type);
        MessageType messageType = MessageType.getByTypeValue(typeInt);
        if (messageType == null || messageType.getDisplayTypeString().isEmpty()) return;

        this.type.setText(messageType.getDisplayTypeString());
        this.type.setVisibility(View.VISIBLE);
    }
}
