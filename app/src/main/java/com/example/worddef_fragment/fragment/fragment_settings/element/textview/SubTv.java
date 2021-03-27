package com.example.worddef_fragment.fragment.fragment_settings.element.textview;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SubTv extends androidx.appcompat.widget.AppCompatTextView {

    public SubTv(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    private void onCreate() {
        setView();
    }

    private void setView() {
        setLayoutParams(new LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.MATCH_PARENT));
        setTextColor(Color.BLACK);
    }
}
