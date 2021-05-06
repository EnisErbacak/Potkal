package com.example.worddef_fragment.other;

import android.content.Context;
import android.util.TypedValue;

public class PixelConverter {
    private Context context;

    public PixelConverter(Context context) {
        this.context = context;
    }

    public  int dp2Px(int dp) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

    public  int sp2Px(int sp) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics()));
    }
}
