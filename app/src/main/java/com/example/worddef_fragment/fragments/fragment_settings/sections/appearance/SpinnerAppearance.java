package com.example.worddef_fragment.fragments.fragment_settings.sections.appearance;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.element.spinners.CustomSpinner;
import com.example.worddef_fragment.fragments.fragment_settings.element.spinners.Listener;

import io.opencensus.internal.StringUtils;

public class SpinnerAppearance extends CustomSpinner {
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
        String str=getLastValue();
        setSelection(arrayAdapter.getPosition(str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase()));
    }

    void onCreate() {
        initialize();
        setOnItemSelectedListener(new Listener(sharedPrefKey,null));
    }

    public void initialize() {
        ArrayAdapter<Object> arrayAdapter=new ArrayAdapter<Object>(context, android.R.layout.simple_spinner_item, new String[] {"Simple", "Detailed"});
        setAdapter(arrayAdapter);
        setLastSelecedItem(arrayAdapter);
    }
}
