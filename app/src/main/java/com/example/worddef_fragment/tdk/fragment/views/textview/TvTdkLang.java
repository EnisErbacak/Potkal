package com.example.worddef_fragment.tdk.fragment.views.textview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class TvTdkLang extends androidx.appcompat.widget.AppCompatTextView {
    public TvTdkLang(@NonNull Context context, String lang) {
        super(context);
        setText(lang);
        onCreate();
    }

    void onCreate() {
        setStyle();
    }

    void setStyle() {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        setTextColor(Color.BLACK);
        setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        setTextSize(15);
    }
}
