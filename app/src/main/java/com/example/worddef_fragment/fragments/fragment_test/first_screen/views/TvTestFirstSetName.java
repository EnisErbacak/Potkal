package com.example.worddef_fragment.fragments.fragment_test.first_screen.views;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

public class TvTestFirstSetName extends androidx.appcompat.widget.AppCompatTextView {
    public TvTestFirstSetName(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setTextColor(Color.WHITE);
        setTextSize(20);
    }
}
