package com.example.worddef_fragment.fragments.fragment_settings.element.containers;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.worddef_fragment.fragments.fragment_settings.sections.color.btn.BtnColor;
import com.example.worddef_fragment.fragments.fragment_settings.element.textview.TvChild;

public class ClColor extends ConstraintLayout {
    private TvChild tvChild;
    private BtnColor btnColor;
    private ConstraintSet constraintSet;

    public ClColor(Context context, String txt,String prefKey) {
        super(context);
        this.tvChild =new TvChild(context,txt);
        this.btnColor=new BtnColor(context, prefKey);
        onCreate();
    }

    private void onCreate() {
        this.constraintSet=new ConstraintSet();
        setView();
    }

    private void setView() {
        setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        locateSubPanels();
    }


    private void locateSubPanels() {

        this.setId(ConstraintLayout.generateViewId());
        tvChild.setId(generateViewId());
        btnColor.setId(generateViewId());

        this.addView(tvChild);
        this.addView(btnColor);

        constraintSet.clone(this);

        constraintSet.connect(tvChild.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(tvChild.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvChild.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(btnColor.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END
                ,(int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics()));
        constraintSet.connect(btnColor.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(btnColor.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);


        constraintSet.connect(tvChild.getId(), ConstraintSet.END,   btnColor.getId(), ConstraintSet.START);
        constraintSet.connect(btnColor.getId(), ConstraintSet.START, tvChild.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }

}
