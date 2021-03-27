package com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView.ViewDefinition;
import com.example.worddef_fragment.other.PixelConverter;

public class PnlContainerWrdDef extends LinearLayout
{
    private ViewDefinition viewDef;
    private PnlUpper upperPanel;

    private final int WDTH=ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGHT=ViewGroup.LayoutParams.WRAP_CONTENT;

    private final int VAL_MRGN_LFT= PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_TOP=PixelConverter.pix2Dip(getContext(),7);
    private final int VAL_MRGN_RGHT=PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_BTTM=PixelConverter.pix2Dip(getContext(),7);

    private final int VAL_CRNR_RDS=PixelConverter.pix2Dip(getContext(),15);
    private final int COL_BG=Color.parseColor("#C7E6FB");

    public PnlContainerWrdDef(Context context, PnlUpper upperPanel, ViewDefinition viewDef) {
        super(context);
        this.viewDef=viewDef;
        this.upperPanel=upperPanel;
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

        addView(upperPanel);
        addView(viewDef);
    }

    public PnlUpper getUpperPanel() {
        return upperPanel;
    }

    public ViewDefinition getViewDef() {
        return viewDef;
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
