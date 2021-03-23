package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView.TvDef;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView.TvExmp;
import com.example.worddef_fragment.other.PixelConverter;

public class LlLower extends LinearLayout {
    String def, exmp;


    public LlLower(Context context, String def, String exmp) {
        super(context);
        this.def = def;
        this.exmp = exmp;
        onCreate();
    }

    void onCreate() {
        setView();
    }

    void setView() {
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setPadding(PixelConverter.pix2Dip(getContext(),7),0,0,0);

        addView(new TvDef(getContext(), def));
        if(! exmp.equals("")) addView(new TvExmp(getContext(), exmp));
    }
}
