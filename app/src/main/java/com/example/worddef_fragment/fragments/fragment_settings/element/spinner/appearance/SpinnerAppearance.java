package com.example.worddef_fragment.fragments.fragment_settings.element.spinner.appearance;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class SpinnerAppearance extends androidx.appcompat.widget.AppCompatSpinner {
    private Context context;
    private SPEditor spEditor;
    private String sharedPrefKey;

    public SpinnerAppearance(Context context, String sharedPrefKey) {
        super(context);
        this.context=context;
        spEditor=new SPEditor();
        this.sharedPrefKey=sharedPrefKey;
        onCreate();
    }

    public String getLastValue() {
        return spEditor.getValue(context, sharedPrefKey);
    }

    public void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter) {
        setSelection(arrayAdapter.getPosition(getLastValue()));
    }

    void onCreate() {
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
        ArrayAdapter<Object> arrayAdapter=new ArrayAdapter<Object>(context, android.R.layout.simple_spinner_item, new String[] {"simple", "detailed"});
        setAdapter(arrayAdapter);
        setLastSelecedItem(arrayAdapter);
    }
}
