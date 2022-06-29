package xyz.android.library.recyclerview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xyz.android.library.recyclerview.viewholders.HeaderViewHolder;
import xyz.android.library.recyclerview.R;
import xyz.phone.commons.model.Call;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderViewHolder> {

    private static final int VIEW_ITEM_HEAD = R.layout.item_head;

    private final List<String> category;
    private final Map<String, List<Call>> map;
    private final LayoutInflater inflater;

    public HeaderAdapter(Context context, Map<String, List<Call>> map) {
        this.map = map;
        this.inflater = LayoutInflater.from(context);

        //TAKE CATEGORY AS SEPARATE LIST
        category = new ArrayList<>(map.keySet());
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(VIEW_ITEM_HEAD, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
        //CHECKS
        if (map == null || category == null || map.get(category.get(position)) == null) return;

        //DISPLAY DATA
        holder.populateData(
                //CATEGORY
                category.get(position),
                //OBJECT
                map.get(category.get(position))
        );
    }

    @Override
    public int getItemCount() {
        return category == null ? 0 : category.size();
    }
}
