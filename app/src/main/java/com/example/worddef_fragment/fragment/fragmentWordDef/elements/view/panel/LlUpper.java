package com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView.TvLang;

public class LlUpper extends LinearLayout {

    private LlUpperSubLeft llUpperSubLeft;
    private String lang;

    public LlUpper(Context context, LlUpperSubLeft llUpperSubLeft, String lang) {
        super(context);
        this.llUpperSubLeft=llUpperSubLeft;
        this.lang=lang;
        onCreate();
    }

    void onCreate() {
        setView();
    }

    void setView() {
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(llUpperSubLeft);
        if(! lang.equals("")) addView(new TvLang(getContext(), lang));

    }
}
