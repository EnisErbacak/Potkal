package com.example.worddef_fragment.fragments.fragment_wordset.editor;

import android.content.Context;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.element.containers.ClColorContainer;

import java.util.ArrayList;

public class WordSetSettings {
    private Context context;
    private ArrayList<ClColorContainer> settings;
    public WordSetSettings(Context context) {
        this.context = context;
        settings=new ArrayList<>();
        createContent();
    }

    private void createContent() {

        String str_set=context.getResources().getString(R.string.set);
        String str_def=context.getResources().getString(R.string.definition);
        String str_bg=context.getResources().getString(R.string.background);
        String str_btn=context.getResources().getString(R.string.button);
        String str_txt=context.getResources().getString(R.string.text);
        String str_settings=context.getResources().getString(R.string.settings);
        String str_topbar=context.getResources().getString(R.string.status_bar);
        String str_dialog=context.getResources().getString(R.string.dialog);

        settings.add(new ClColorContainer(context,str_set +" "+str_topbar, SPEditor.COL_WORDSET_STATUSBAR)); // WORDSET TOP BAR
        settings.add(new ClColorContainer(context,str_set +" "+str_topbar+" "+str_txt, SPEditor.COL_WORDSET_STATUSBAR_TXT)); // WORDSET TOP BAR TXT

        settings.add(new ClColorContainer(context,str_set, SPEditor.COL_WORDSET)); // WORDSET
        settings.add(new ClColorContainer(context,str_set +" "+str_bg, SPEditor.COL_WORDSET_BG)); // WORDSET BG
        settings.add(new ClColorContainer(context,str_set +" "+str_btn+" "+str_bg, SPEditor.COL_WORDSET_BTN_BG)); // WORDSET BTN BG
        settings.add(new ClColorContainer(context,str_set +" "+str_txt, SPEditor.COL_WORDSET_TXT)); // WORDSET TXT
    }

    public ArrayList<ClColorContainer> getColorSettings() {
        return settings;
    }
}
