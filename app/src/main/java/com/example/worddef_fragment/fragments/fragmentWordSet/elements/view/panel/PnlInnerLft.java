package com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.panel;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.txtView.TxtViewWrdSetLft;

public class PnlInnerLft extends LinearLayout {

    private final int MTCH_CNSTRNT= ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;

    private LayoutParams lp;
    private TxtViewWrdSetLft txtViewWrdSet;



    public PnlInnerLft(Context context, TxtViewWrdSetLft txtViewWrdSet) {
        super(context);
        this.txtViewWrdSet=txtViewWrdSet;
        onCreate();
    }

    public void onCreate() {
        this.setId(generateViewId());
        setView();
        addView(txtViewWrdSet);
    }

    public void setView() {
        lp=new LayoutParams(MTCH_CNSTRNT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        setLytPrms(lp);
        setOrientation(LinearLayout.VERTICAL);
    }

    private void setLytPrms(LayoutParams lp) {
        setLayoutParams(lp);
    }

    public TxtViewWrdSetLft getTxtViewWrdSet() {
        return txtViewWrdSet;
    }
}
