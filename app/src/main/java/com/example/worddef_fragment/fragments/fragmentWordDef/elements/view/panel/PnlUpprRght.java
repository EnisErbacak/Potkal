package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.Arrays;
import java.util.Collections;

public class PnlUpprRght extends LinearLayout {
    private InnerRelativeLayout[] subLayouts;

    private final int WDTH= ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
    private final int HGHT= LayoutParams.WRAP_CONTENT;


    public PnlUpprRght(Context context,InnerRelativeLayout[] subLayouts) {
        super(context);
        this.subLayouts=subLayouts;
        onCreate();
    }

    private void onCreate() {
        this.setId(generateViewId());
        setView();
    }

    private void setView() {
        LayoutParams lp=new LayoutParams(WDTH, HGHT);
        setLayoutParams(lp);
        addViews(subLayouts);
    }

    private void addViews(RelativeLayout[] subLayouts) {
        for(int i=0;i<subLayouts.length;i++) {
            addView(subLayouts[i]);
        }
    }
}

class InnerRelativeLayout extends RelativeLayout { // Used relative layout for alignment of the elements right.

    private View[] subViews;
    public InnerRelativeLayout(Context context, View[] subViews) {
        super(context);
        this.subViews=subViews;
        onCreate();
    }

    private void onCreate() {
        setView();
        addView(new InnerLinearLayout(getContext(),subViews));
    }

    private void setView() {
        setGravity(Gravity.END);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}


class InnerLinearLayout extends LinearLayout {

    private View[] subViews;
    public InnerLinearLayout(Context context, View[] subViews) {
        super(context);
        this.subViews=subViews;
        onCreate();
    }

    private  void onCreate() {
        setView();
    }

    private void setView() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setOrientation(HORIZONTAL);
        addViews();
    }

    private void addViews() {
        View[] reversed=subViews.clone();
        Collections.reverse(Arrays.asList(reversed)); // Reversed for right to left element addition.

        for(int i=0;i<reversed.length;i++) {
            //addView(reversed[i]);
        }
    }
}

class InnerConstraintsLayout extends ConstraintLayout {

    private View[] subViews;
    public InnerConstraintsLayout(Context context, View[] subViews) {
        super(context);
        this.subViews=subViews;
        onCreate();
    }

    private void onCreate() {
        setId(ConstraintLayout.generateViewId());
        setView();
    }
    private void setView() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addViews();
        locateViews(subViews);
    }

    private void addViews() {
        for(int i=0;i<subViews.length;i++) {
            addView(subViews[i]);
        }
    }

    private void locateViews(View[] subViews) {

        ConstraintSet constraintSet=new ConstraintSet();
        constraintSet.clone(this);

        if(subViews.length>1)
            Collections.reverse(Arrays.asList(subViews));

        for(int i=0;i<subViews.length;i++) {
            // Common steps for all elements.
            constraintSet.connect(subViews[i].getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
            constraintSet.connect(subViews[i].getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


            if(i==0) // If first element, Connect to parent start.
                constraintSet.connect(subViews[i].getId(), ConstraintSet.START,  this.getId(),ConstraintSet.END);
            else// If not the first element, Connect to start to the previous element.
                constraintSet.connect(subViews[i].getId(), ConstraintSet.START,  subViews[i-1].getId(),ConstraintSet.END);

            if(i+1>=subViews.length) // If it's the last element, Connect the element's end to the parent's end.
                constraintSet.connect(subViews[i].getId(), ConstraintSet.END,  this.getId(),ConstraintSet.START);
            else // If not the last element, Connect element's end to the next element's start.
                constraintSet.connect(subViews[i].getId(), ConstraintSet.END,  subViews[i+1].getId(),ConstraintSet.START);

        }
        constraintSet.applyTo(this);
        this.invalidate();
    }
}
