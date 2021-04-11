package com.example.worddef_fragment.fragments.fragment_wordset.views.container;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view.SuperTvRght;

public class ContainerInnerRght extends LinearLayout {
    private SuperTvRght[] subViews;

    public ContainerInnerRght(Context context, SuperTvRght[] subViews) {
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
        setLayoutParams(new LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    private void addViews(SuperTvRght[] subViews) {
        for(int i=0;i<subViews.length;i++) {
            addView(subViews[i]);
        }
    }

    public SuperTvRght[] getSubViews() {
        return subViews;
    }
}