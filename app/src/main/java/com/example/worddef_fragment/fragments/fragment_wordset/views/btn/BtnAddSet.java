package com.example.worddef_fragment.fragments.fragment_wordset.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.dialog.FragmentAddSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnAddSet extends FloatingActionButton
{

    public BtnAddSet(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public void onCreate() {
        setCondition();
        setStyle();
    }
    private void setStyle() {
        setSupportBackgroundTintList(ColorStateList.valueOf(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDSET_BTN_BG))));
    }

    private void setCondition() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new FragmentAddSet(view.getRootView().findViewById(R.id.pnlWrdSetMain)).show(
                        ((FragmentActivity)getContext()).getSupportFragmentManager(),"ADD NEW SET"
                );
            }
        });
    }
}