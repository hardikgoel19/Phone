package xyz.android.library.recyclerview.viewholders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Set;

import xyz.android.library.recyclerview.R;
import xyz.android.library.recyclerview.adapters.CallLogsAdapter;
import xyz.phone.commons.model.Call;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private boolean isCollapsed;

    private final RelativeLayout mainLayout;
    private final TextView headText;
    private final ImageView collapseIcon;

    //COLLAPSABLE VIEW
    private final RecyclerView recyclerView;

    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        mainLayout = itemView.findViewById(R.id.mainLayout);
        headText = itemView.findViewById(R.id.headText);
        collapseIcon = itemView.findViewById(R.id.collapseIcon);
        recyclerView = itemView.findViewById(R.id.recyclerView);
    }

    public void populateData(String head, Set<Call> calls) {
        //HANDLE COLLAPSE ACTION
        handleCollapseAction();

        //DISPLAY HEAD TEXT
        displayHeadText(head);

        //HANDLE MAIN LAYOUT ON CLICK
        handleMainLayoutOnClick(head);

        //POPULATE RECYCLER VIEW
        populateRecyclerView(calls);
    }

    private void populateRecyclerView(Set<Call> calls) {
        if (this.recyclerView == null) return;

        //CONTEXT
        Context context = this.recyclerView.getContext();

        //ADAPTER
        CallLogsAdapter adapter = new CallLogsAdapter(context, calls);

        //LAYOUT MANAGER
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

        //PREPARE RECYCLER VIEW
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void handleCollapseAction() {
        if (isCollapsed) {
            //SET COLLAPSE ICON
            if (this.collapseIcon == null) return;
            Drawable imageDrawable = ContextCompat.getDrawable(
                    this.collapseIcon.getContext(),
                    R.drawable.drop_up
            );
            if (imageDrawable != null) {
                this.collapseIcon.setImageDrawable(imageDrawable);
            }

            //HIDE RECYCLER VIEW
            if (this.recyclerView == null) return;

            this.recyclerView.setVisibility(View.GONE);
        } else {

            //SET COLLAPSE ICON
            if (this.collapseIcon == null) return;
            Drawable imageDrawable = ContextCompat.getDrawable(
                    this.collapseIcon.getContext(),
                    R.drawable.drop_down
            );
            if (imageDrawable != null) {
                this.collapseIcon.setImageDrawable(imageDrawable);
            }

            //SHOW RECYCLER VIEW
            if (this.recyclerView == null) return;

            this.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void handleMainLayoutOnClick(String head) {
        if (this.mainLayout == null) return;

        this.mainLayout.setOnClickListener(view -> {
            isCollapsed = !isCollapsed;
            handleCollapseAction();
        });
    }

    private void displayHeadText(String headText) {
        if (this.headText == null) return;

        if (headText == null || headText.isEmpty()) return;

        this.headText.setText(headText);
    }

}
