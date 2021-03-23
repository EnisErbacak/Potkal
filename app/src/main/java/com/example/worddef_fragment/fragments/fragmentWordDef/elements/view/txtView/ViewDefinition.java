package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.other.PixelConverter;

public class ViewDefinition extends androidx.appcompat.widget.AppCompatTextView
{
    private final int WDTH=ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGT=ViewGroup.LayoutParams.WRAP_CONTENT;

    private final int VAL_MRGN_LFT= PixelConverter.pix2Dip(getContext(),15);
    private final int VAL_MRGN_TOP=PixelConverter.pix2Dip(getContext(),0);
    private final int VAL_MRGN_RGHT=PixelConverter.pix2Dip(getContext(),15);
    private final int VAL_MRGN_BTTM=PixelConverter.pix2Dip(getContext(),0);

    private final int COL_TXT=Color.BLACK;
    private final int SIZE_TXT=PixelConverter.pix2Sp(getContext(), 6);

    private Context context;
    public ViewDefinition(@NonNull Context context,String def) {
        super(context);
        this.context=context;
        onCreate();
        setText(def);
    }

    void onCreate()
    {
        setView();
    }

    void setView() {
        LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(WDTH,HGT);

        lp.setMargins(VAL_MRGN_LFT, VAL_MRGN_TOP, VAL_MRGN_RGHT, VAL_MRGN_BTTM);
        setLayoutParams(lp);

        setTextSize(getTxtSize());
        setTextColor(COL_TXT);
    }

    private int getTxtSize() {
        return Integer.valueOf(new SPEditor().getValue(context, SPEditor.DEF_TXT_SIZE));
    }
}
