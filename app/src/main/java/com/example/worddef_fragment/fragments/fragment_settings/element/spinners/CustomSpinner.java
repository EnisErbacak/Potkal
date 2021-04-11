package com.example.worddef_fragment.fragments.fragment_settings.element.spinners;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

public abstract class CustomSpinner extends androidx.appcompat.widget.AppCompatSpinner {

    public CustomSpinner(Context context) {
        super(context);
        onCreate();
    }

    private void onCreate() {
        setLayoutParams(new LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, LayoutParams.WRAP_CONTENT));
    }

    public abstract void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter);
    public abstract void initialize();



    public ArrayAdapter getNumericArrayadapter(Context context, int start, int end, int by) {
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=start;i<end;i+=by)
            list.add(i);
        return new ArrayAdapter(context, android.R.layout.simple_spinner_item,list);
    }

    public ArrayAdapter<Object> getStringArrayAdapter(Context context, String[] arrayStr) {
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, arrayStr);
    }
}
