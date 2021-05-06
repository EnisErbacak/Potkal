package com.example.worddef_fragment.fragments.fragment_settings.element.containers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.worddef_fragment.fragments.fragment_settings.element.textview.TvChild;

public class ClSubTitleContainer extends ConstraintLayout {
    private TvSub tvSub;

    private String txt;
    private ConstraintSet constraintSet;

    public ClSubTitleContainer(Context context, String txt) {
        super(context);
        this.tvSub = new TvSub(context, txt);
        onCreate();
    }

    public String getTxt() {
        return txt;
    }


    private void onCreate() {
        this.constraintSet = new ConstraintSet();
        setStyle();
    }

    private void setStyle() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        locateSubPanels();
    }

    private void locateSubPanels() {
        this.setId(ConstraintLayout.generateViewId());
        tvSub.setId(generateViewId());

        this.addView(tvSub);

        constraintSet.clone(this);

        constraintSet.connect(tvSub.getId(), ConstraintSet.START, this.getId(), ConstraintSet.START);
        constraintSet.connect(tvSub.getId(), ConstraintSet.BOTTOM, this.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(tvSub.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP);

        constraintSet.applyTo(this);
    }

    private class TvSub extends androidx.appcompat.widget.AppCompatTextView {

        public TvSub(Context context, String txt) {
            super(context);
            setText(txt);
            setTypeface(Typeface.DEFAULT_BOLD);
            setTextColor(Color.BLACK);
            setTextSize(20);
        }
    }
}

