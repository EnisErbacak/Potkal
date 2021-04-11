package com.example.worddef_fragment.fragments.fragment_settings.element.containers;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.worddef_fragment.fragments.fragment_settings.element.textview.TvChild;

public class ClContainer extends ConstraintLayout {
    private TvChild tvChild;
    private  Spinner sp;

    private String txt;
    private ConstraintSet constraintSet;

    public ClContainer(Context context, String txt, Spinner sp) {
        super(context);
        this.tvChild =new TvChild(context,txt);
        this.sp=sp;
        this.txt= tvChild.getText().toString();
        onCreate();
    }

    public String getTxt() {
        return txt;
    }


    private void onCreate() {
        this.constraintSet=new ConstraintSet();
        setStyle();
    }

    private void setStyle() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        locateSubPanels();
    }

    private void locateSubPanels() {

        this.setId(ConstraintLayout.generateViewId());
        sp.setId(generateViewId());
        tvChild.setId(generateViewId());

        this.addView(tvChild);
        this.addView(sp);

        constraintSet.clone(this);

        constraintSet.connect(tvChild.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(tvChild.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvChild.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(sp.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END,0);
        constraintSet.connect(sp.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(sp.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);


        constraintSet.connect(tvChild.getId(), ConstraintSet.END,   sp.getId(), ConstraintSet.START);
        constraintSet.connect(sp.getId(), ConstraintSet.START, tvChild.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }
}
