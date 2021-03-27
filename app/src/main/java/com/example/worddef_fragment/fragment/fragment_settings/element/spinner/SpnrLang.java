package com.example.worddef_fragment.fragment.fragment_settings.element.spinner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SpnrLang extends androidx.appcompat.widget.AppCompatSpinner {
    ArrayList<String> content;
    Context context;
    public SpnrLang(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        onCreate();
    }

    private void onCreate() {
        createContent();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, content);
        setAdapter(dataAdapter);
    }

    private void createContent() {
        content=new ArrayList<>();
        content.add("tr");
        content.add("eng");
    }
}
