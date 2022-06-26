package xyz.phone.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.phone.manager.R;
import xyz.phone.manager.model.Call;
import xyz.phone.manager.viewholder.CallLogViewHolder;

public class CallLogsAdapter extends RecyclerView.Adapter<CallLogViewHolder> {

    private static final int VEW_ITEM_CALL_LOG = R.layout.item_call_log;

    private final List<Call> calls;
    private final LayoutInflater inflater;

    public CallLogsAdapter(Context context, List<Call> calls) {
        this.calls = calls;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CallLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(VEW_ITEM_CALL_LOG, parent, false);
        return new CallLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogViewHolder holder, int position) {
        //CHECKS
        if (calls == null || calls.get(position) == null) return;

        // DISPLAY NAME
        holder.setDisplayName(calls.get(position));

        // DATE TIME
        holder.setDateTime(calls.get(position));

        // SIM CARD INFO
        holder.setSimCard(calls.get(position));

        // CALL TYPE ICON
        holder.setCallTypeIcon(calls.get(position));

    }

    @Override
    public int getItemCount() {
        return calls == null ? 0 : calls.size();
    }
}
