package com.example.worddef_fragment.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Typeface;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class TvDef extends TvWrdDef {
    public TvDef(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    @Override
    void setStyle() {
        super.setStyle();
        setTextSize(getTxtSize());
        setTypeface(Typeface.DEFAULT_BOLD);
    }

    private int getTxtSize() {
        return Integer.valueOf(new SPEditor().getValue(getContext(), SPEditor.TXT_SIZE_DEF));
    }
}
