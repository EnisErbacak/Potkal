package com.example.worddef_fragment.fragments.fragment_settings.element.textview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class TvChild extends androidx.appcompat.widget.AppCompatTextView {

    public TvChild(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setLayoutParams(new LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.MATCH_PARENT));
        setTextColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_SETTINGS_TXT)));
    }
}