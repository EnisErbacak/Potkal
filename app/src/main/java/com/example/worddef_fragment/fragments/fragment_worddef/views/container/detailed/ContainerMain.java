package com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.worddef_fragment.other.PixelConverter;

public class ContainerMain extends LinearLayout {
    private ContainerUpper containerUpper;
    private ContainerLower containerLower;

    private final int WDTH= ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGHT=ViewGroup.LayoutParams.WRAP_CONTENT;

    private final int VAL_MRGN_LFT= PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_TOP=PixelConverter.pix2Dip(getContext(),7);
    private final int VAL_MRGN_RGHT=PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_BTTM=PixelConverter.pix2Dip(getContext(),7);

    private final int VAL_CRNR_RDS=PixelConverter.pix2Dip(getContext(),15);
    private final int COL_BG= Color.parseColor("#C7E6FB");

    public ContainerMain(Context context, ContainerUpper containerUpper, ContainerLower containerLower) {
        super(context);
        this.containerUpper = containerUpper;
        this.containerLower=containerLower;
        onCreate();
    }

    public void onCreate() {
        setStyle();
        addChilren();
    }

    public void setStyle() {
        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WDTH, HGHT);
        lp.setMargins(VAL_MRGN_LFT,VAL_MRGN_TOP,VAL_MRGN_RGHT,VAL_MRGN_BTTM);
        setPadding(PixelConverter.pix2Dip(getContext(),7), PixelConverter.pix2Dip(getContext(),7)
                ,PixelConverter.pix2Dip(getContext(),7),PixelConverter.pix2Dip(getContext(),7));
        setLayoutParams(lp);
        setBackground(getGradientDrawable(COL_BG, VAL_CRNR_RDS));
    }

    void addChilren() {
        addView(containerUpper);
        addView(containerLower);
    }

    public TextView getTvDef() {
        return containerLower.getTvDef();
    }

    private Drawable getGradientDrawable(int color, int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }
}
