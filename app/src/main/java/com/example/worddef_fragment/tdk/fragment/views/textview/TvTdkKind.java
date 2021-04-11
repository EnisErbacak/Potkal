package com.example.worddef_fragment.tdk.fragment.views.textview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TvTdkKind extends androidx.appcompat.widget.AppCompatTextView {
    public TvTdkKind(Context context, String kind) {
        super(context);
        setText(kind);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        lp.setMargins(30,0,0,0);
        this.setTextColor(Color.BLACK);
        setTextSize(15);
        setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
    }


}
