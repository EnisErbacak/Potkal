package com.example.worddef_fragment.fragments.fragment_test.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_test.FragmentTest;
import com.example.worddef_fragment.fragments.fragment_test.FragmentTestFirst;

import java.util.ArrayList;

public class BtnStartTest extends androidx.appcompat.widget.AppCompatButton {

    public BtnStartTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    private void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout ll=view.getRootView().findViewById(R.id.llTestFirst);
                ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentTest(getSetNames(ll))).commit();
            }
        });
    }

    private ArrayList<String> getSetNames(LinearLayout ll) {
        ArrayList<String> names=new ArrayList<>();
        for(int i=0;i<ll.getChildCount();i++) {
            ContainerTestWordset containerTestWordset= (ContainerTestWordset) ll.getChildAt(i);
            if(containerTestWordset.getCheckBox().isChecked()) names.add(containerTestWordset.getSetName());
        }
        return names;
    }
}
