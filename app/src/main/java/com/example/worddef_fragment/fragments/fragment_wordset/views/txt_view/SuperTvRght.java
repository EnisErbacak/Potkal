package com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.other.PixelConverter;

public class SuperTvRght extends androidx.appcompat.widget.AppCompatTextView {

    private LinearLayout.LayoutParams lp;

    private final int MTCH_PRNT= LinearLayout.LayoutParams.MATCH_PARENT;
    private final int WRP_CNTNT= LinearLayout.LayoutParams.WRAP_CONTENT;

    private final int COL_TXT= Color.BLACK;
    private final int SIZE_TXT= PixelConverter.pix2Sp(getContext(),5);

    private String txt;

    public SuperTvRght(@NonNull Context context, String txt) {
        super(context);
        onCreate();
        setText(txt);
        this.txt=txt;
    }

    private void onCreate() {
        setView();
    }

    private void setView() {
        lp=new LinearLayout.LayoutParams(MTCH_PRNT,WRP_CNTNT);
        setTextColor(COL_TXT);
        setTextSize(SIZE_TXT);
        setTypeface(getTypeface(), Typeface.BOLD);
        setLayoutParams(lp);
        setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
    }

    public String getText() {
        return txt;
    }
}
