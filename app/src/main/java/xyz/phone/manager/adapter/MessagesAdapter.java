package xyz.phone.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.phone.manager.R;
import xyz.phone.manager.model.Message;
import xyz.phone.manager.viewholder.MessageViewHolder;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private static final int VEW_ITEM_MESSAGES = R.layout.item_message;

    private final List<List<Message>> messages;
    private final LayoutInflater inflater;

    public MessagesAdapter(Context context, List<List<Message>> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(VEW_ITEM_MESSAGES, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        //CHECKS
        if (messages == null || messages.get(position) == null) return;

        //GET MESSAGES GROUPED
        List<Message> groupedMessages = messages.get(position);

        //SET DISPLAY NAME
        holder.setDisplayName(groupedMessages);

        //SET DATE TIME
        holder.setLastDateTime(groupedMessages);

        //SET LAST MESSAGE
        holder.setLastMessage(groupedMessages);

    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }
}
