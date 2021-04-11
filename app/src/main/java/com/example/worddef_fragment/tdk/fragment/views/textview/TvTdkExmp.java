package com.example.worddef_fragment.tdk.fragment.views.textview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class TvTdkExmp extends androidx.appcompat.widget.AppCompatTextView{

    private String example;
    public TvTdkExmp(@NonNull Context context, String example) {
        super(context);
        this.example=example;
        onCreate();
    }

    void onCreate() {
        setStyle();
        setText(example);
    }

    void setStyle() {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        lp.setMargins(30,0,0,0);

        this.setTextColor(Color.BLACK);
        setTextSize(15);
        setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
    }
}