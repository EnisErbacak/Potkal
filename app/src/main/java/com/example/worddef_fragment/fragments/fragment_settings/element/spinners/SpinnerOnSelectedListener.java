package com.example.worddef_fragment.fragments.fragment_settings.element.spinners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;

public class SpinnerOnSelectedListener implements AdapterView.OnItemSelectedListener{
    private String prefKey;
    private String[] customArr;

    public SpinnerOnSelectedListener(String prefKey, @Nullable String[] customArr) {
        this.prefKey = prefKey;
        this.customArr=customArr;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        SPEditor spEditor=new SPEditor();

        // Child view text color
        ((TextView)parent.getChildAt(0)).setTextColor( Integer.parseInt(spEditor.getValue(view.getContext(), SPEditor.COL_SETTINGS_TXT)));
        String selected;
        if(customArr!=null)  selected=customArr[pos];
        else selected=String.valueOf(parent.getItemAtPosition(pos));

        spEditor.setValues(view.getContext(), prefKey, String.valueOf(selected).toLowerCase());
    }





    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
