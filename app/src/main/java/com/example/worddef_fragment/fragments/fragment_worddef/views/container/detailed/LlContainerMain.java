package com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.other.PixelConverter;

public class LlContainerMain extends LinearLayout {
    private LlContainerUpper llContainerUpper;
    private LlContainerLower containerLower;
    private PixelConverter pixelConverter;

    private final int WDTH= ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGHT=ViewGroup.LayoutParams.WRAP_CONTENT;

    // Values in Dp
    private final int VAL_MRGN_LFT= 10;
    private final int VAL_MRGN_TOP=7;
    private final int VAL_MRGN_RGHT=10;
    private final int VAL_MRGN_BTTM=7;

    private final int VAL_PADDING=7;

    private final int VAL_CRNR_RDS=15;
    //private final int COL_BG= Color.parseColor("#C7E6FB");
    private  int colBg;

    public LlContainerMain(Context context, LlContainerUpper llContainerUpper, LlContainerLower containerLower) {
        super(context);
        this.llContainerUpper = llContainerUpper;
        this.containerLower=containerLower;
        pixelConverter=new PixelConverter(context);
        onCreate();
    }

    public void onCreate() {
        setStyle();
        addChilren();
    }

    public void setStyle() {
        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WDTH, HGHT);
        lp.setMargins(pixelConverter.dp2Px(VAL_MRGN_LFT), pixelConverter.dp2Px(VAL_MRGN_TOP), pixelConverter.dp2Px(VAL_MRGN_RGHT), pixelConverter.dp2Px(VAL_MRGN_BTTM));
        setPadding(pixelConverter.dp2Px(VAL_PADDING), pixelConverter.dp2Px(VAL_PADDING), pixelConverter.dp2Px(VAL_PADDING), pixelConverter.dp2Px(VAL_PADDING));
        setLayoutParams(lp);
        colBg= Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDDEF));
        setBackground(getGradientDrawable(colBg, pixelConverter.dp2Px(15)));
    }

    void addChilren() {
        addView(llContainerUpper);
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
