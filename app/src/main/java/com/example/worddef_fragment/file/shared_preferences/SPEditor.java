package com.example.worddef_fragment.file.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.worddef_fragment.R;

import java.util.HashMap;

public class SPEditor {

    private HashMap<String,String> hm;
    private final String SP_NAME="SharedPref";
    public static final String SET_TXT_SIZE="set_txt_size";
    public static final String WORD_TXT_SIZE="word_txt_size";
    public static final String DEF_TXT_SIZE="def_txt_size";
    public static final String APP_THEME="app_theme";
    public static final String APP_LANG="app_lang";
    public static final String APPEARANCE="appearance";

    // ALL COLORS ARE IN HEX FORMAT.
    public static final String COL_WORDSET="col_wordset";
    public static final String COL_WORDSET_BG="col_wordset_bg";
    public static final String COL_WORDSET_BTN_BG="col_wordset_btn_bg";
    public static final String COL_WORDSET_TXT="col_wordset_txt";
    public static final String COL_WORDSET_STATUSBAR="col_wordset_statusbar";

    public static final String COL_WORDDEF="col_worddef";
    public static final String COL_WORDDEF_BG="col_worddef_bg";
    public static final String COL_WORDDEF_BTN_BG="col_worddef_btn_bg";
    public static final String COL_WORDDEF_TXT="col_worddef_txt";
    public static final String COL_WORDDEF_STATUSBAR="col_worddef_statusbar";


    public static final String COL_SETTINGS_BG="col_settings_bg";
    public static final String COL_SETTINGS_TXT="col_settings_txt";

    private void initialize(Context context) {
        hm=new HashMap<>();
        hm.put(SET_TXT_SIZE,"35");
        hm.put(WORD_TXT_SIZE,"25");
        hm.put(DEF_TXT_SIZE,"15");
        hm.put(APP_THEME, "eng");
        hm.put(APP_LANG,"eng");
        hm.put(APPEARANCE, "detailed");

        int col=context.getResources().getColor(R.color.col_wordset);

        hm.put(COL_WORDSET,String.valueOf(context.getResources().getColor(R.color.col_wordset)));
        hm.put(COL_WORDSET_BG,String.valueOf(context.getResources().getColor(R.color.col_wordset_bg)));
        hm.put(COL_WORDSET_BTN_BG,String.valueOf(context.getResources().getColor(R.color.col_wordset_btn_bg)));
        hm.put(COL_WORDSET_TXT,String.valueOf(context.getResources().getColor(R.color.col_black)));
        hm.put(COL_WORDSET_STATUSBAR,String.valueOf(context.getResources().getColor(R.color.col_wordset_statusbar)));

        hm.put(COL_WORDDEF,String.valueOf(context.getResources().getColor(R.color.col_worddef)));
        hm.put(COL_WORDDEF_BG,String.valueOf(context.getResources().getColor(R.color.col_worddef_bg)));
        hm.put(COL_WORDDEF_BTN_BG,String.valueOf(context.getResources().getColor(R.color.col_worddef_btn_bg)));
        hm.put(COL_WORDDEF_TXT,String.valueOf(context.getResources().getColor(R.color.col_black)));
        hm.put(COL_WORDDEF_STATUSBAR,String.valueOf(context.getResources().getColor(R.color.col_worddef_statusbar)));


        hm.put(COL_SETTINGS_BG,String.valueOf(context.getResources().getColor(R.color.col_settings_bg)));
        hm.put(COL_SETTINGS_TXT,String.valueOf(context.getResources().getColor(R.color.col_black)));
    }

    public void setValues(Context context, String key, String value) {
        SharedPreferences sp= context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public void firstStart(Context context) {
        initialize(context);
        checkValues(context);
    }

    private void checkValues(Context context) {
        SharedPreferences sp= context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        for (String key : hm.keySet()) {
            if(!sp.contains(key)) setValues(context,key,hm.get(key));
        }
    }

    public String getValue(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key,"");
    }
}
