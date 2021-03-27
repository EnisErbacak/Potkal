package com.example.worddef_fragment.fragment.fragmentWordDef.elements.btn;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.worddef_fragment.fragment.fragmentWordDef.elements.popupMenu.PopupWrdDefOpt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnOpt extends FloatingActionButton {
    public BtnOpt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean performClick() {
        //new PopupWrdDefOpt(getContext(), this).show();
        new PopupWrdDefOpt(getContext(), this).show();
        return super.performClick();
    }
}
