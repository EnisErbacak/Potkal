package com.example.worddef_fragment.fragments.fragment_worddef.views.txtView;

import android.content.Context;

import androidx.annotation.NonNull;

public class TvPts extends TvSuper {
    public TvPts(@NonNull Context context, int pts) {
        super(context);
        setText("Point: "+String.valueOf(pts));
    }
}
