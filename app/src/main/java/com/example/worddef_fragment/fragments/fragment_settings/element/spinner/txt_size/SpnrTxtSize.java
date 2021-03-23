package com.example.worddef_fragment.fragments.fragment_settings.element.spinner.txt_size;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.element.spinner.CustomSpinner;

public class SpnrTxtSize extends CustomSpinner {
    private Context context;
    private SPEditor spEditor;
    private String sharedPrefKey;

    public SpnrTxtSize(@NonNull Context context,String sharedPrefKey) {
        super(context);
        this.context=context;
        spEditor=new SPEditor();
        this.sharedPrefKey=sharedPrefKey;
        onCreate();
    }


    private void onCreate() {
        initialize();
        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spEditor.setValues(getContext(), sharedPrefKey,String.valueOf(parent.getItemAtPosition(pos)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void initialize() {
        ArrayAdapter<Object> arrayAdapter=super.getNumericArrayadapter(context,15,45,5);
        setAdapter(arrayAdapter);
        setLastSelecedItem(arrayAdapter);
    }

     public int getLastValue() {
        return Integer.valueOf(spEditor.getValue(context, sharedPrefKey));
    }


    @Override
    public void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter) {
        setSelection(arrayAdapter.getPosition(getLastValue()));
    }

}
