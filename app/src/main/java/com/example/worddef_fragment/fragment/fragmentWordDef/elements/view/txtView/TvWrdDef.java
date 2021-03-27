package com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class TvWrdDef extends androidx.appcompat.widget.AppCompatTextView {
    public TvWrdDef(@NonNull Context context) {
        super(context);
        onCreate();
    }

    void onCreate() {
        setView();
    }

    void setView() {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setTextColor(Color.BLACK);
    }

    public String getItalic(String str) {
        return "<i>"+str+"</i>";
    }

    public String getUnderlined(String str) {
        return "<u>"+str+"</u>";
    }


}
