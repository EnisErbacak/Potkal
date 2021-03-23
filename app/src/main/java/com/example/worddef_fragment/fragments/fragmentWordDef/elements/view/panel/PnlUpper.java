package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.Lamp;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView.TvPts;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView.ViewWord;
import com.example.worddef_fragment.other.PixelConverter;

public class PnlUpper extends ConstraintLayout
{
    private ViewWord viewWord;
    private Lamp trueFalse;
    private TvPts tvPts;
    private ConstraintLayout.LayoutParams lp;
    private ConstraintSet constraintSet;


    private PnlUpprLft pnlInnerLft;
    private PnlUpprRght pnlInnerRght;

    private  final int WDTH= ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGT= ViewGroup.LayoutParams.MATCH_PARENT;

    // Margin values
    private final int VAL_MRGN_LFT= PixelConverter.pix2Dip(getContext(),15);
    private final int VAL_MRGN_TOP=PixelConverter.pix2Dip(getContext(),0);
    private final int VAL_MRGN_RGHT=PixelConverter.pix2Dip(getContext(),15);
    private final int VAL_MRGN_BTTM=PixelConverter.pix2Dip(getContext(),0);


    public PnlUpper(Context context, ViewWord viewWord) {
        super(context);
        this.viewWord=viewWord;
        this.trueFalse=trueFalse;
        this.tvPts=tvPts;
        onCreate();
    }

    public void onCreate() {
        this.setId(ConstraintLayout.generateViewId());
        this.constraintSet=new ConstraintSet();
        this.pnlInnerLft=new PnlUpprLft(getContext(),new View[]{viewWord});

        InnerRelativeLayout innerRelativeLayout=new InnerRelativeLayout(getContext(),new View[]{trueFalse,tvPts});
        this.pnlInnerRght=new PnlUpprRght(getContext(),new InnerRelativeLayout[]{innerRelativeLayout});

        setView();
    }

    public void setView() {
        lp=new LayoutParams(WDTH, HGT);
        lp.setMargins(VAL_MRGN_LFT,VAL_MRGN_TOP,VAL_MRGN_RGHT,VAL_MRGN_BTTM);
        setLayoutParams(lp);
        locateSubPanels();
    }

    private void locateSubPanels() {
        this.addView(pnlInnerLft);
        this.addView(pnlInnerRght);
        constraintSet.clone(PnlUpper.this);

        constraintSet.connect(pnlInnerLft.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(pnlInnerLft.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(pnlInnerLft.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(pnlInnerRght.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(pnlInnerRght.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);
        constraintSet.connect(pnlInnerRght.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END,20);

        constraintSet.connect(pnlInnerLft.getId(), ConstraintSet.END,   pnlInnerRght.getId(), ConstraintSet.START);
        constraintSet.connect(pnlInnerRght.getId(), ConstraintSet.START, pnlInnerLft.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }

    public ViewWord getWord(){return viewWord;}
    public Lamp getLamp() { return trueFalse;}

    public void deleteBox() {
        ((PnlContainerWrdDef)getParent()).deleteBox();
    }
}
