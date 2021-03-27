package com.example.worddef_fragment.fragment.fragmentWordSet.elements.view.panel;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.fragment.fragmentWordSet.elements.view.txtView.SuperTxtViewRght;
import com.example.worddef_fragment.fragment.fragmentWordSet.elements.view.txtView.TxtViewWordCountRght;

public class PnlInnerRght extends LinearLayout {

    private LinearLayout.LayoutParams lp;
    private SuperTxtViewRght[] subViews;

    private final int MTCH_CNSTRNT= ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;

    public PnlInnerRght(Context context, SuperTxtViewRght[] subViews) {
        super(context);
        this.subViews=subViews;
        onCreate();
    }

    void onCreate() {
        this.setId(generateViewId());
        addViews(subViews);
        setView();
    }
    void setView(){
        setOrientation(LinearLayout.VERTICAL);
        lp=new LayoutParams(MTCH_CNSTRNT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
    }

    private void addViews(SuperTxtViewRght[] subViews) {
        for(int i=0;i<subViews.length;i++) {
            addView(subViews[i]);
        }
    }

    public SuperTxtViewRght[] getSubViews() {
        return subViews;
    }


}