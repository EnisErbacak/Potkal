package com.example.worddef_fragment.fragment.fragmentWordDef.editor.ui;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;

public interface UiEditorWordDef {
    void buildByCreateDateAsc();
    void buildByCreateDateDsc();
    void buildByAlphabeticalAsc();
    void buildByAlphabeticalDsc();
    void updateScreen();
    void buildScreen(int order);
    ArrayList<String> getKeyList(Iterator<String> iterator, boolean reversed);
    ArrayList<String> sortAlphabetical(ArrayList<String> keys, boolean reversed);
    public View getCleanedPanel(Context context);
}
