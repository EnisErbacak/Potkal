package com.example.worddef_fragment.fragments.fragment_wordset.manager;

import android.content.Context;

import com.example.worddef_fragment.fragments.fragment_wordset.process.WordSetExplorer;
import com.example.worddef_fragment.fragments.fragment_wordset.process.WordSetOperator;

public class WordsetManager {
    public WordSetOperator operate(Context context){
        return new WordSetOperator(context);
    }

    public WordSetExplorer explore(Context context) {
        return new WordSetExplorer();
    }
}
