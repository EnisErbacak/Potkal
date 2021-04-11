package com.example.worddef_fragment.fragments.fragment_worddef.builder.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public interface UiBuilder {
    void buildByCreateDateAsc();
    void buildByCreateDateDsc();
    void buildByAlphabeticalAsc();
    void buildByAlphabeticalDsc();
    void updateScreen();
    void buildScreen(int order);
    void createView(LinearLayout ll, ArrayList<String> keys, JSONObject jObj);
    ArrayList<String> getKeyList(Iterator<String> iterator, boolean reversed);
    ArrayList<String> sortAlphabetical(ArrayList<String> keys, boolean reversed);
    View getCleanedPanel(Context context);
}
