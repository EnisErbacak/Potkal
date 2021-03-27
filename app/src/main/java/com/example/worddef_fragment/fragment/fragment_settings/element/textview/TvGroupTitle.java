package com.example.worddef_fragment.fragment.fragment_settings.element.textview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;

public class TvGroupTitle extends androidx.appcompat.widget.AppCompatTextView {

    private final int TXT_COL= Color.BLACK;
    private final int TXT_SIZE=15;

    public TvGroupTitle(Context context) {
        super(context);
        onCreate();
    }

    private void onCreate() {
        setView();
    }

    private void setView() {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP,TXT_SIZE);
        setTextColor(TXT_COL);
    }
}
