package com.example.worddef_fragment.other;

import android.content.Context;
import android.util.TypedValue;

public class PixelConverter {

    public static int pix2Dip(Context context, int pix) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pix, context.getResources().getDisplayMetrics()));
    }

    public static int pix2Sp(Context context, int pix) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pix, context.getResources().getDisplayMetrics()));
    }
}
