package com.example.worddef_fragment.fragments.fragmentWordDef.editor.ui.operator;

import android.content.Context;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.ui.UiEditorWordDef;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.ui.UiEditorWordDefDetailed;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.ui.UiEditorWordDefSimple;

public class EditorOperator {
    String appearance=SPEditor.APPEARANCE;

    private String getAppearance(Context context) {
        return  new SPEditor().getValue(context,appearance);
    }

    public UiEditorWordDef getUiEditor(Context context, String setName) {
        UiEditorWordDef result=null;
        switch (getAppearance(context)) {
            case "simple":
                result= new UiEditorWordDefSimple(context, setName);
                break;
            case "detailed":
                result= new UiEditorWordDefDetailed(context, setName);
                break;
        }
        return result;
    }
}
