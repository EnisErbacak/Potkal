package com.example.worddef_fragment.fragment.fragmentWordDef.elements.btn;

import android.content.Context;
import android.util.AttributeSet;

import com.example.worddef_fragment.fragment.fragmentWordDef.elements.popupMenu.PopupSort;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnSrt extends FloatingActionButton {

    public BtnSrt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean performClick() {
        new PopupSort(getContext(), this).show();
        return super.performClick();
    }
}
