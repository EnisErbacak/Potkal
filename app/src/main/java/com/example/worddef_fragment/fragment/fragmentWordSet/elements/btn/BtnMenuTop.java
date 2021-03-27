package com.example.worddef_fragment.fragment.fragmentWordSet.elements.btn;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.worddef_fragment.fragment.fragmentWordSet.elements.popup.PopupTopMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnMenuTop extends FloatingActionButton {
    private ProgressBar pBar;

    public BtnMenuTop(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setView();
        setOnClickListener(new BtnMainOptsLstnr());
    }

    public void setView() {
        setBackgroundColor(Color.parseColor("#2196F3"));
    }

    public void setPBar(ProgressBar pBar) {
        this.pBar = pBar;
    }

    public ProgressBar getPBar() {
        return pBar;
    }

    private class BtnMainOptsLstnr implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            PopupTopMenu opt = new PopupTopMenu(view.getContext(), view);
            opt.show();
        }
    }
}
