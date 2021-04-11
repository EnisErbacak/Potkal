package com.example.worddef_fragment.fragments.fragment_worddef.views.txtView;

import android.content.Context;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class TvSuper extends androidx.appcompat.widget.AppCompatTextView {
    public TvSuper(Context context) {
        super(context);
        setStyle();
    }

    private void setStyle() {
        setTextColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDDEF_TXT)));
    }
}
