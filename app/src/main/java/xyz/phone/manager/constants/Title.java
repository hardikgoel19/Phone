package xyz.phone.manager.constants;

import android.text.Html;
import android.text.Spanned;

public interface Title {

    String WHITE_TEMPLATE = "<font color='#ffffff'> %s </font>";

    //ACTION BAR TITLES
    Spanned CALL_LOGS_TITLE = Html.fromHtml(
            String.format(WHITE_TEMPLATE, "Call Logs"),
            0);

    Spanned MESSAGES_TITLE = Html.fromHtml(
            String.format(WHITE_TEMPLATE, "Messages"),
            0);
}
