package com.example.worddef_fragment.fragments.nav_view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_test.FragmentTest;
import com.example.worddef_fragment.fragments.fragment_test.FragmentTestFirst;

import java.util.ArrayList;

public class BtnCreateTest extends androidx.appcompat.widget.AppCompatButton {

    private ArrayList<String> sets;
    public BtnCreateTest(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentTestFirst()).commit();
            }
        });
    }
}
