package com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.operator;

import android.content.Context;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.UiBuilder;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.UiBuilderDetailed;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.UiBuilderSimple;

public class BuilderEditor {
    String appearance=SPEditor.APPEARANCE;

    private String getAppearance(Context context) {
        return  new SPEditor().getValue(context,appearance);
    }

    public UiBuilder getUiEditor(Context context, String setName) {
        UiBuilder result=null;
        switch (getAppearance(context)) {
            case "simple":
                result= new UiBuilderSimple(context, setName);
                break;
            case "detailed":
                result= new UiBuilderDetailed(context, setName);
                break;
        }
        return result;
    }
}
