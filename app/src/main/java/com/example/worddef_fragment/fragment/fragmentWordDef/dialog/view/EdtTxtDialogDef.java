package com.example.worddef_fragment.fragment.fragmentWordDef.dialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EdtTxtDialogDef  extends androidx.appcompat.widget.AppCompatEditText {
    private LinearLayout.LayoutParams lp;

    private final int MTCH_PRNT= LinearLayout.LayoutParams.MATCH_PARENT;
    private final int WRP_CNTNT= LinearLayout.LayoutParams.WRAP_CONTENT;

    private final int VAL_WDTH=MTCH_PRNT;
    private final int VAL_HGT=WRP_CNTNT;

    // Margin values
    private final int VAL_MRGN_LFT=pix2Dip(15);
    private final int VAL_MRGN_TOP=pix2Dip(0);
    private final int VAL_MRGN_RGHT=pix2Dip(15);
    private final int VAL_MRGN_BTTM=pix2Dip(0);

    public EdtTxtDialogDef(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //setText("DEFINITION");
        onCreate();
    }

    public void onCreate(){

        lp= new LinearLayout.LayoutParams(VAL_WDTH, VAL_HGT);
        lp.setMargins(VAL_MRGN_LFT, VAL_MRGN_TOP, VAL_MRGN_RGHT,VAL_MRGN_BTTM);
        setLayoutParams(lp);
        //setTextSize(SIZE_TXT);
        //setTextColor(COL_TXT);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        //setFocusable(true);
        //setFocusableInTouchMode(true);
        //setClickable(true);
    }
    private int pix2Dip(int value){return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics()));}

}