package com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.panel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.worddef_fragment.file.editor.WordSetEditor;
import com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.txtView.SuperTxtViewRght;
import com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.txtView.TxtViewWordCountRght;
import com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.txtView.TxtViewWrdSetLft;
import com.example.worddef_fragment.other.PixelConverter;

//********REFACTORED
public class PnlContainerWrdSet extends ConstraintLayout
{
    private final int MTCH_PRNT= LayoutParams.MATCH_PARENT;

    // Margin values
    private final int VAL_MRGN_LFT= PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_TOP=PixelConverter.pix2Dip(getContext(),7);
    private final int VAL_MRGN_RGHT=PixelConverter.pix2Dip(getContext(),10);
    private final int VAL_MRGN_BTTM=PixelConverter.pix2Dip(getContext(),7);

    // Padding values
    private final int VAL_PAD_LFT=PixelConverter.pix2Dip(getContext(),5);
    private final int VAL_PAD_TOP=PixelConverter.pix2Dip(getContext(),7);
    private final int VAL_PAD_RGHT=PixelConverter.pix2Dip(getContext(),0);
    private final int VAL_PAD_BTTM=PixelConverter.pix2Dip(getContext(),7);

    private final int COL_BG=Color.parseColor("#C7E6FB");

    private final int VAL_CRNR_RDS=PixelConverter.pix2Dip(getContext(),15);

    private ConstraintSet constraintSet;
    private LayoutParams lp;

    private TxtViewWrdSetLft txtViewWrdSet;
    private PnlInnerRght pnlInnerRght;
    private PnlInnerLft pnlInnerLft;
    private String setName;
    private int countWord;

    public PnlContainerWrdSet(final Context context, final String setName) {
        super(context);
        this.setName=setName;
        countWord=new WordSetEditor(getContext()).getWordCount(setName);
        onCreate();
    }

    private void onCreate() {
        this.setId(ConstraintLayout.generateViewId());

        this.constraintSet=new ConstraintSet();
        txtViewWrdSet=new TxtViewWrdSetLft(getContext(),setName);

        pnlInnerLft=new PnlInnerLft(getContext(),txtViewWrdSet);
        pnlInnerRght=new PnlInnerRght(getContext(),new SuperTxtViewRght[]{new TxtViewWordCountRght(getContext(),countWord)});
        setView();
    }

    public void setView() {
        lp=new LayoutParams(MTCH_PRNT,MTCH_PRNT);
        //lp=new LayoutParams(MTCH_PRNT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLytPrms(lp);
        setMargin(lp);
        setPad();

        setBgShape(getGradientDrawable(COL_BG,VAL_CRNR_RDS));
        locateSubPanels();
    }

    private void locateSubPanels() {
        this.addView(pnlInnerLft);
        this.addView(pnlInnerRght);
        constraintSet.clone(this);

        constraintSet.connect(pnlInnerLft.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(pnlInnerLft.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(pnlInnerLft.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(pnlInnerRght.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END,20);
        constraintSet.connect(pnlInnerRght.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(pnlInnerRght.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);


        constraintSet.connect(pnlInnerLft.getId(), ConstraintSet.END,   pnlInnerRght.getId(), ConstraintSet.START);
        constraintSet.connect(pnlInnerRght.getId(), ConstraintSet.START, pnlInnerLft.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }


    private Drawable getGradientDrawable(int color,int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    private void setBgShape(Drawable shape){
        setBackground(shape);
    }
    private void setLytPrms(LayoutParams lp) {
        setLayoutParams(lp);
    }
    private void setMargin(LayoutParams lp) {
        lp.setMargins(VAL_MRGN_LFT,VAL_MRGN_TOP,VAL_MRGN_RGHT,VAL_MRGN_BTTM);
    }

    private void setPad() {
        setPadding(VAL_PAD_LFT,VAL_PAD_TOP,VAL_PAD_RGHT,VAL_PAD_BTTM);
    }




    public PnlInnerRght getPnlInnerRght() {
        return pnlInnerRght;
    }

    public PnlInnerLft getPnlInnerLft() {
        return pnlInnerLft;
    }

    public String getSetName() {
        return setName;
    }
}
