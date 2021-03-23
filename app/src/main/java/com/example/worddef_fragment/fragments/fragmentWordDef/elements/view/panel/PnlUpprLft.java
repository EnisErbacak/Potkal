package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PnlUpprLft extends LinearLayout {

    private View[] subViews;

    private final int WDTH= ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
    private final int HGHT=LayoutParams.WRAP_CONTENT;

    public PnlUpprLft(Context context, View[] subViews) {
        super(context);
        this.subViews=subViews;
        onCreate();
    }

    private void onCreate() {
        this.setId(generateViewId());
        setView();
    }

    private void setView() {
        LayoutParams lp=new LayoutParams(WDTH, HGHT);
        setLayoutParams(lp);
        setOrientation(VERTICAL);
        addViews(subViews);
    }

    private void addViews(View[] subViews) {
        for(int i=0;i<subViews.length;i++) {
            addView(subViews[i]);
        }
    }

    public void deleteBox() {
        ((PnlUpper)getParent()).deleteBox();
    }
}
