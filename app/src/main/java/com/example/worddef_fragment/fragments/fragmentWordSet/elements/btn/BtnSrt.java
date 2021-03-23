package com.example.worddef_fragment.fragments.fragmentWordSet.elements.btn;

import android.content.Context;
import android.util.AttributeSet;

import com.example.worddef_fragment.fragments.fragmentWordSet.elements.popup.PopupSrtWrdSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnSrt extends FloatingActionButton
{

    public BtnSrt(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    private void onCreate() {
        setCondition();
    }

    private void setCondition() {

    }

    public void setView()
    {
    }

    @Override
    public boolean performClick()
    {
        //optionsMenu.setGravity(Gravity.LEFT);
        //optionsMenu.show();
        new PopupSrtWrdSet(getContext(), this).show();
        return super.performClick();
    }
}
