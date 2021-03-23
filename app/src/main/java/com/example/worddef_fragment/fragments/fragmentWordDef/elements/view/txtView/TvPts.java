package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class TvPts extends androidx.appcompat.widget.AppCompatTextView {
    public TvPts(@NonNull Context context, int pts) {
        super(context);
        setText("Point: "+String.valueOf(pts));
    }
}
