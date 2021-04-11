package com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.Word;
import com.example.worddef_fragment.tdk.process.TdkWord;

import java.util.ArrayList;

public interface CustomDialogFragment {
    Context getContext();
    EditText getEtWrd();
    EditText getEtDef();
    EditText getEtLang();
    EditText getEtKind();
    EditText getEtExmp();
    Button getBtnDsplyTdk();
    void setTdkWordList(ArrayList<TdkWord> tdkWordList);
    ArrayList<TdkWord> getTdkWordList();
    ProgressBar getPbTdk();

    Word getWord();
    void setWord(Word word);
}
