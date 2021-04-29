package com.example.worddef_fragment.fragments.fragment_test.test_screen.views.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BtnNextScreen extends androidx.appcompat.widget.AppCompatButton {

    public BtnNextScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
