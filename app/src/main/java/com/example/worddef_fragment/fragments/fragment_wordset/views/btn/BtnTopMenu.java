package com.example.worddef_fragment.fragments.fragment_wordset.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.popup.PopupTopMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnTopMenu extends FloatingActionButton {
    private ProgressBar pBar;

    public BtnTopMenu(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setStyle();
        setOnClickListener(new BtnMainOptsLstnr());
    }

    public void setStyle() {
        setSupportBackgroundTintList(ColorStateList.valueOf(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDSET_STATUSBAR))));
    }



    private class BtnMainOptsLstnr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            PopupTopMenu opt = new PopupTopMenu(view.getContext(), view);
            opt.show();
        }
    }
}
