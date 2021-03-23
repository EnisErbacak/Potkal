package com.example.worddef_fragment.fragments.fragmentWordDef.dialog.dialog_fragments;

import android.content.Context;
import android.widget.EditText;

import com.example.worddef_fragment.fragments.fragmentWordDef.editor.data.Word;
import com.example.worddef_fragment.tdk.TdkObject;

import java.util.ArrayList;

public interface CustomDialogFragment {
    EditText getEtWrd();
    EditText getEtDef();
    EditText getEtLang();
    EditText getEtKind();
    EditText getEtExmp();

    Word getWord();
    void setWord(Word word);

    ArrayList<String> getDefList();
    void setDefList(ArrayList<String> defList);

    TdkObject getTdkObject();
    void setTdkObject(TdkObject tdkObject);
}
