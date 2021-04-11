package com.example.worddef_fragment.fragments.fragment_worddef.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.popup.PopupSort;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnSrt extends FloatingActionButton {

    public BtnSrt(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setSupportBackgroundTintList(ColorStateList.valueOf(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDDEF_BTN_BG))));
    }

    public boolean performClick() {
        new PopupSort(getContext(), this).show();
        return super.performClick();
    }
}
