package com.example.worddef_fragment.fragments.fragment_settings.sections.lang;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.element.spinners.SpinnerOnSelectedListener;

public class SpinnerLang  extends androidx.appcompat.widget.AppCompatSpinner{
    private Context context;
    private SPEditor spEditor;
    private String sharedPrefKey;
    private final String[] codes={"eng", "tr"};
    private final String[] langs={"English", "Türkçe"};

    public SpinnerLang(@NonNull Context context,String sharedPrefKey) {
        super(context);
        this.context=context;
        spEditor=new SPEditor();
        this.sharedPrefKey=sharedPrefKey;
        onCreate();
    }

    public String getLastValue() {
        String value="";
        String last=spEditor.getValue(context, sharedPrefKey);

        for(int i=0;i<codes.length;i++) {

            if(codes[i].equals(last)){
                value=langs[i];
                break;
            }
        }
        return  value;
    }

    public void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter) {
        setSelection(arrayAdapter.getPosition(getLastValue()));
    }

    void onCreate() {
        initialize();
        setOnItemSelectedListener(new SpinnerOnSelectedListener(sharedPrefKey,codes));
    }

    public void initialize() {
        ArrayAdapter<Object> arrayAdapter=new ArrayAdapter<Object>(context, android.R.layout.simple_spinner_item, langs);
        setAdapter(arrayAdapter);
        setLastSelecedItem(arrayAdapter);
    }
}
