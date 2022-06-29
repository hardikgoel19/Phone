package xyz.android.library.recyclerview.viewholders;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import xyz.android.library.recyclerview.R;
import xyz.android.library.recyclerview.enums.CallType;
import xyz.phone.commons.model.Call;
import xyz.phone.commons.utils.Converter;
import xyz.phone.commons.utils.DateTimeUtil;

public class CallLogViewHolder extends RecyclerView.ViewHolder {

    private final RelativeLayout mainLayout;
    private final TextView displayName;
    private final TextView dateTime;
    private final TextView simCard;
    private final ImageView callTypeIcon;

    public CallLogViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mainLayout = itemView.findViewById(R.id.mainLayout);
        this.displayName = itemView.findViewById(R.id.displayName);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.simCard = itemView.findViewById(R.id.simCard);
        this.callTypeIcon = itemView.findViewById(R.id.callTypeIcon);
    }

    private void setDisplayName(@NonNull Call call) {
        if (this.displayName == null) return;

        //SET DISPLAY NAME (NAME OR NUMBER)
        String name = call.getName();
        if (name != null && !name.isEmpty()) {
            this.displayName.setText(name);
        } else {
            this.displayName.setText(call.getNumber());
        }
    }

    private void setDateTime(@NonNull Call call) {
        if (this.dateTime == null) return;

        //SET DATE-TIME
        long timeInMillis = Converter.toLong(call.getCallDate());
        if (timeInMillis == 0) {
            dateTime.setText("");
        } else {
            String formattedDateTime = DateTimeUtil.getFormattedTimestamp(timeInMillis);
            dateTime.setText(formattedDateTime);
        }
    }

    private void setSimCard(@NonNull Call call) {
        if (this.simCard == null) return;

        //SET SIM CARD INFO
        this.simCard.setText(call.getPhoneAccountId());
    }

    private void setCallTypeIcon(@NonNull Call call) {
        if (this.callTypeIcon == null) return;

        //SET CALL TYPE ICON
        int intTypeValue = Converter.toInteger(call.getType());
        CallType callType = CallType.getByTypeValue(intTypeValue);
        Drawable imageDrawable = ContextCompat.getDrawable(
                this.callTypeIcon.getContext(),
                callType.getDrawableResource()
        );
        if (imageDrawable != null) {
            this.callTypeIcon.setImageDrawable(imageDrawable);
        }
    }

    public void populateData(Call call) {
        // DISPLAY NAME
        setDisplayName(call);

        // DATE TIME
        setDateTime(call);

        // SIM CARD INFO
        setSimCard(call);

        // CALL TYPE ICON
        setCallTypeIcon(call);
    }
}