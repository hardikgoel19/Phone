package xyz.phone.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import xyz.phone.commons.model.Message;
import xyz.phone.commons.utils.Converter;
import xyz.phone.manager.enums.MessageType;
import xyz.phone.manager.viewholder.ConversationViewHolder;

public class ConversationsAdapter extends RecyclerView.Adapter<ConversationViewHolder> {

    private final ArrayList<Message> messages;
    private final LayoutInflater inflater;

    public ConversationsAdapter(Context context, ArrayList<Message> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (this.messages != null) {
            Message message = messages.get(position);
            if (message != null) {
                Integer type = Converter.toInteger(message.getType());
                MessageType messageType = MessageType.getByTypeValue(type);
                return messageType.getLayoutId();
            }
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        if (messages == null || messages.get(position) == null) return;

        //SET CONTENT
        holder.setContent(messages.get(position));

        //SET DATE TIME
        holder.setDateTime(messages.get(position));

        //SET SIM CARD
        holder.setSimCard(messages.get(position));

        //SET STATUS
        holder.setStatus(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
