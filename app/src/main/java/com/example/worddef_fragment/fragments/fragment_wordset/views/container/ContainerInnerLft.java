package com.example.worddef_fragment.fragments.fragment_wordset.views.container;

import android.content.Context;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view.TvWordsetLeft;

public class ContainerInnerLft extends LinearLayout {
    private TvWordsetLeft txtViewWrdSet;

    public ContainerInnerLft(Context context, TvWordsetLeft txtViewWrdSet) {
        super(context);
        this.txtViewWrdSet=txtViewWrdSet;
        onCreate();
    }

    public void onCreate() {
        this.setId(generateViewId());
        setStyle();
        addView(txtViewWrdSet);
    }

    public void setStyle() {
        setLayoutParams(new LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);
    }
    public TvWordsetLeft getTxtViewWrdSet() {
        return txtViewWrdSet;
    }
}
