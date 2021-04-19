package com.example.worddef_fragment.fragments.fragment_wordset.views.container;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view.SuperTvRght;

public class ContainerInnerRght extends LinearLayout {
    private View[] subViews;

    public ContainerInnerRght(Context context, View[] subViews) {
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

    private void addViews(View[] subViews) {
        for(int i=0;i<subViews.length;i++) {
            addView(subViews[i]);
        }
    }

    public View[] getSubViews() {
        return subViews;
    }
}