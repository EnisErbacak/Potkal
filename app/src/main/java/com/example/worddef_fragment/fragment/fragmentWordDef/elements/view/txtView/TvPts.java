package com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView;

import android.content.Context;

import androidx.annotation.NonNull;

public class TvPts extends androidx.appcompat.widget.AppCompatTextView {
    public TvPts(@NonNull Context context, int pts) {
        super(context);
        setText("Point: "+String.valueOf(pts));
    }
}
