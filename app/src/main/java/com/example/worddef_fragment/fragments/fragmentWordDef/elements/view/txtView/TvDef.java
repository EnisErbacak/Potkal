package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class TvDef extends TvWrdDef {
    public TvDef(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    @Override
    void setView() {
        super.setView();
        setTextSize(getTxtSize());
    }

    private int getTxtSize() {
        return Integer.valueOf(new SPEditor().getValue(getContext(), SPEditor.DEF_TXT_SIZE));
    }
}
