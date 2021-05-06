package com.example.worddef_fragment.fragments.fragment_worddef.manager;

import android.content.Context;

import com.example.worddef_fragment.fragments.fragment_worddef.process.WordDefExplorer;
import com.example.worddef_fragment.fragments.fragment_worddef.process.WordDefOperator;

public class WorddefManager {
    public WordDefOperator operate(Context context){
        return new WordDefOperator(context);
    }

    public WordDefExplorer explore(Context context) {
        return new WordDefExplorer();
    }
}
