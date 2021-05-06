package com.example.worddef_fragment.fragments.fragment_worddef.builder.settings;

import android.content.Context;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.element.containers.ClColorContainer;

import java.util.ArrayList;

public class WordDefSettings {
    private Context context;
    private ArrayList<ClColorContainer> settings;

    public WordDefSettings(Context context) {
        this.context = context;
        settings = new ArrayList<>();
        createContent();
    }

    private void createContent() {
        String str_set = context.getResources().getString(R.string.set);
        String str_def = context.getResources().getString(R.string.definition);
        String str_bg = context.getResources().getString(R.string.background);
        String str_btn = context.getResources().getString(R.string.button);
        String str_txt = context.getResources().getString(R.string.text);
        String str_settings = context.getResources().getString(R.string.settings);
        String str_topbar = context.getResources().getString(R.string.status_bar);
        String str_dialog = context.getResources().getString(R.string.dialog);


        settings.add(new ClColorContainer(context, str_def + " " + str_topbar + " " + str_txt, SPEditor.COL_WORDDEF_STATUSBAR_TXT)); // WORDDEF TOP BAR TXT
        settings.add(new ClColorContainer(context, str_def, SPEditor.COL_WORDDEF));// WORDDEF
        settings.add(new ClColorContainer(context, str_def + " " + str_bg, SPEditor.COL_WORDDEF_BG));//WORDDEF BG
        settings.add(new ClColorContainer(context, str_def + " " + str_btn + " " + str_bg, SPEditor.COL_WORDDEF_BTN_BG)); // WORDSET BTN BG
        settings.add(new ClColorContainer(context, str_def + " " + str_txt, SPEditor.COL_WORDDEF_TXT)); // WORDSET TXT
        settings.add(new ClColorContainer(context, str_def + " " + str_topbar, SPEditor.COL_WORDDEF_STATUSBAR)); // WORDSET STATUS BAR

        settings.add(new ClColorContainer(context, str_settings + " " + str_bg, SPEditor.COL_SETTINGS_BG)); // SETTINGS BG
        settings.add(new ClColorContainer(context, str_settings + " " + str_txt, SPEditor.COL_SETTINGS_TXT)); // SETTINGS BG
    }

    public ArrayList<ClColorContainer> getColorSettings() {
        return settings;
    }
}