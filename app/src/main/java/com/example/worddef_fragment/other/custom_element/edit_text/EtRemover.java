package com.example.worddef_fragment.other.custom_element.edit_text;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EtRemover extends androidx.appcompat.widget.AppCompatEditText {
    private boolean clicked=false;
    public EtRemover(@NonNull Context context, @Nullable AttributeSet attrs, String txt) {
        super(context, attrs);
        setText(txt);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(!clicked) {
                    setText("");
                    clicked=true;
                }
                return false;
            }
        });
    }
}
