package com.example.worddef_fragment.tdk.fragment.views.textview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
public class TvTdkTitle extends androidx.appcompat.widget.AppCompatTextView {

    public TvTdkTitle(@NonNull Context context, String strWrd) {
        super(context);
        setText(strWrd);
        setStyle();
    }

    private void setStyle() {
        setTypeface(Typeface.DEFAULT_BOLD);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            setLayoutParams(lp);
            setTextColor(Color.BLACK);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setTextSize(25);
    }
}
