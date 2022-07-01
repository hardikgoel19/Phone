package xyz.phone.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xyz.phone.commons.model.Message;
import xyz.phone.manager.R;
import xyz.phone.manager.viewholder.MessageViewHolder;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private static final int VEW_ITEM_MESSAGES = R.layout.item_message;

    private final Map<String, Set<Message>> map;
    private final List<String> headings;
    private final LayoutInflater inflater;

    public MessagesAdapter(Context context, Map<String, Set<Message>> map) {
        if (map == null) map = new HashMap<>();
        headings = new ArrayList<>(map.keySet());
        this.map = map;
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
        if (map == null
                || headings == null
                || headings.get(position) == null
                || map.get(headings.get(position)) == null) return;

        //GET MESSAGES GROUPED
        String heading = headings.get(position);
        Set<Message> groupedMessages = map.get(heading);

        //POPULATE DATA
        holder.populateData(heading, groupedMessages);
    }

    @Override
    public int getItemCount() {
        return headings == null ? 0 : headings.size();
    }
}
