package com.example.worddef_fragment.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class TvWrdDef extends TvSuper {
    public TvWrdDef(@NonNull Context context) {
        super(context);
        onCreate();
    }

    void onCreate() {
        setStyle();
    }

    void setStyle() {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setTextColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDDEF_TXT)));
    }

    public String getItalic(String str) {
        return "<i>"+str+"</i>";
    }

    public String getUnderlined(String str) {
        return "<u>"+str+"</u>";
    }


}
