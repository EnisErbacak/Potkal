package com.example.worddef_fragment.fragments.fragmentWordDef.elements.btn;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.popupMenu.PopupWrdDefOpt;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.PnlContainerWrdDef;
import com.example.worddef_fragment.other.ScannerActivity;
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
