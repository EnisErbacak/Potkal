package com.example.worddef_fragment.fragments.fragment_test.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.worddef_fragment.fragments.fragment_test.Choice;

public class ButtonChoice extends androidx.appcompat.widget.AppCompatButton {
    private Choice choice;

    public ButtonChoice(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                choice.select();
            }
        });
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}