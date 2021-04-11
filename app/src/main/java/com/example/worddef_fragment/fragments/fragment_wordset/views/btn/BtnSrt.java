package com.example.worddef_fragment.fragments.fragment_wordset.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.popup.PopupSrtWrdSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnSrt extends FloatingActionButton
{

    public BtnSrt(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setSupportBackgroundTintList(ColorStateList.valueOf(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDSET_BTN_BG))));
    }
    @Override
    public boolean performClick() {
        new PopupSrtWrdSet(getContext(), this).show();
        return super.performClick();
    }
}
