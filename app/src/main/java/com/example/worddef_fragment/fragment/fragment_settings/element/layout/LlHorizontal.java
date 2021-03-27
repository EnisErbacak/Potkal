package com.example.worddef_fragment.fragment.fragment_settings.element.layout;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.worddef_fragment.fragment.fragment_settings.element.textview.SubTv;

public class LlHorizontal extends ConstraintLayout {
    private SubTv subTv;
    private  Spinner sp;

    private String txt;
    private ConstraintSet constraintSet;

    public LlHorizontal(Context context, String txt, Spinner sp) {
        super(context);
        this.subTv=new SubTv(context,txt);
        this.sp=sp;
        this.txt=subTv.getText().toString();
        onCreate();
    }

    public String getTxt() {
        return txt;
    }


    private void onCreate() {
        this.constraintSet=new ConstraintSet();
        setView();
    }

    private void setView() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        locateSubPanels();
    }

    private void locateSubPanels() {

        this.setId(ConstraintLayout.generateViewId());
        sp.setId(generateViewId());
        subTv.setId(generateViewId());

        this.addView(subTv);
        this.addView(sp);

        constraintSet.clone(this);

        constraintSet.connect(subTv.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(subTv.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(subTv.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(sp.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END,0);
        constraintSet.connect(sp.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(sp.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);


        constraintSet.connect(subTv.getId(), ConstraintSet.END,   sp.getId(), ConstraintSet.START);
        constraintSet.connect(sp.getId(), ConstraintSet.START, subTv.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }
}
