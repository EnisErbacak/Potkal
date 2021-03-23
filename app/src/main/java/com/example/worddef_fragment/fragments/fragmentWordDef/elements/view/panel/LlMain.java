package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.other.PixelConverter;

public class LlMain extends LinearLayout {

    private final int WDTH=ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGHT=ViewGroup.LayoutParams.WRAP_CONTENT;

    private final int VAL_MRGN_LFT= PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_TOP=PixelConverter.pix2Dip(getContext(),7);
    private final int VAL_MRGN_RGHT=PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_BTTM=PixelConverter.pix2Dip(getContext(),7);

    private final int VAL_CRNR_RDS=PixelConverter.pix2Dip(getContext(),15);
    private final int COL_BG= Color.parseColor("#C7E6FB");

    private LlUpper llUpper;
    private LlLower llLower;


    public LlMain(Context context, LlUpper llUpper, LlLower llLower) {
        super(context);
        this.llLower=llLower;
        this.llUpper=llUpper;
        onCreate();
    }

    void onCreate() {
        setView();
    }

    void setView() {
        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WDTH, HGHT);
        lp.setMargins(VAL_MRGN_LFT,VAL_MRGN_TOP,VAL_MRGN_RGHT,VAL_MRGN_BTTM);
        setLayoutParams(lp);
        setBackground(getGradientDrawable(COL_BG, VAL_CRNR_RDS));

        setPadding(VAL_MRGN_LFT,VAL_MRGN_TOP,VAL_MRGN_RGHT,VAL_MRGN_BTTM);
        addView(llUpper);
        addView(llLower);
    }

    private Drawable getGradientDrawable(int color, int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    public void deleteBox()
    {
        ((LinearLayout) getParent()).removeView(this);
    }
}
