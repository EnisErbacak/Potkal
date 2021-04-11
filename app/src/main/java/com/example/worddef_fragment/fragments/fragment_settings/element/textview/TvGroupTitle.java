package com.example.worddef_fragment.fragments.fragment_settings.element.textview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.TypedValue;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class TvGroupTitle extends androidx.appcompat.widget.AppCompatTextView {
    private final int TXT_SIZE=15;

    public TvGroupTitle(Context context) {
        super(context);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP,TXT_SIZE);
        setPaintFlags(getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        setTextColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_SETTINGS_TXT)));
    }
}
