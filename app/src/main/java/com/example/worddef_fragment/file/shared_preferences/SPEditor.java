package com.example.worddef_fragment.file.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.example.worddef_fragment.R;

import java.util.HashMap;

public class SPEditor {

    private HashMap<String,String> hm;
    private final String SP_NAME="SharedPref";
    public static final String TXT_SIZE_SET ="set_txt_size";
    public static final String TXT_SIZE_WORD ="word_txt_size";
    public static final String TXT_SIZE_DEF ="def_txt_size";

    public static final String APP_THEME="app_theme";
    public static final String APP_LANG="app_lang";
    public static final String APPEARANCE="appearance";

    // ALL COLORS ARE IN HEX FORMAT.
    public static final String COL_WORDSET="col_wordset";
    public static final String COL_WORDSET_BG="col_wordset_bg";
    public static final String COL_WORDSET_BTN_BG="col_wordset_btn_bg";
    public static final String COL_WORDSET_TXT="col_wordset_txt";
    public static final String COL_WORDSET_STATUSBAR="col_wordset_statusbar";
    public static final String COL_WORDSET_STATUSBAR_TXT="col_wordset_statusbar_txt";

    public static final String COL_WORDDEF="col_worddef";
    public static final String COL_WORDDEF_BG="col_worddef_bg";
    public static final String COL_WORDDEF_BTN_BG="col_worddef_btn_bg";
    public static final String COL_WORDDEF_TXT="col_worddef_txt";
    public static final String COL_WORDDEF_STATUSBAR="col_worddef_statusbar";
    public static final String COL_WORDDEF_STATUSBAR_TXT="col_worddef_statusbar_txt";


    public static final String COL_SETTINGS_BG="col_settings_bg";
    public static final String COL_SETTINGS_TXT="col_settings_txt";

    public static final String DUPLICATION_CHCK="duplication_check";
    public static final String DUPLICATION_CHCK_CURRENT="current";
    public static final String DUPLICATION_CHCK_ALL="all";

    public static final String COL_TEST_BG="col_test_bg";
    public static final String COL_TEST_SCORE_BG="col_test_score_bg";
    public static final String COL_TEST_QUESTION_BG="col_test_question_bg";
    public static final String COL_TEST_CHOICE_BG="col_test_choice_bg";

    public static final String COL_TEST_SCORE_TXT="col_test_score_txt";
    public static final String COL_TEST_QUESTION_TXT="col_test_question_txt";
    public static final String COL_TEST_CHOICE_TXT="col_test_choice_txt";

    public static final String TXT_SIZE_TEST_QUESTION="txt_size_test_question";
    public static final String TXT_SIZE_TEST_CHOICE="txt_size_test_choice";


    private void initialize(Context context) {
        hm=new HashMap<>();
        hm.put(TXT_SIZE_SET,"35");
        hm.put(TXT_SIZE_WORD,"25");
        hm.put(TXT_SIZE_DEF,"15");
        hm.put(APP_THEME, "eng");
        hm.put(APP_LANG,"eng");
        hm.put(APPEARANCE, "detailed");

        int col=context.getResources().getColor(R.color.col_wordset);

        hm.put(COL_WORDSET,String.valueOf(context.getResources().getColor(R.color.col_wordset)));
        hm.put(COL_WORDSET_BG,String.valueOf(context.getResources().getColor(R.color.col_wordset_bg)));
        hm.put(COL_WORDSET_BTN_BG,String.valueOf(context.getResources().getColor(R.color.col_wordset_btn_bg)));
        hm.put(COL_WORDSET_TXT,String.valueOf(context.getResources().getColor(R.color.col_black)));
        hm.put(COL_WORDSET_STATUSBAR,String.valueOf(context.getResources().getColor(R.color.col_wordset_statusbar)));
        hm.put(COL_WORDSET_STATUSBAR_TXT,String.valueOf(context.getResources().getColor(R.color.white)));

        hm.put(COL_WORDDEF,String.valueOf(context.getResources().getColor(R.color.col_worddef)));
        hm.put(COL_WORDDEF_BG,String.valueOf(context.getResources().getColor(R.color.col_worddef_bg)));
        hm.put(COL_WORDDEF_BTN_BG,String.valueOf(context.getResources().getColor(R.color.col_worddef_btn_bg)));
        hm.put(COL_WORDDEF_TXT,String.valueOf(context.getResources().getColor(R.color.col_black)));
        hm.put(COL_WORDDEF_STATUSBAR,String.valueOf(context.getResources().getColor(R.color.col_worddef_statusbar)));
        hm.put(COL_WORDDEF_STATUSBAR_TXT,String.valueOf(context.getResources().getColor(R.color.colorAccent)));

        hm.put(COL_TEST_BG, String.valueOf(context.getResources().getColor(R.color.col_test_bg)));
        hm.put(COL_TEST_SCORE_BG, String.valueOf(context.getResources().getColor(R.color.col_test_score_bg)));
        hm.put(COL_TEST_QUESTION_BG, String.valueOf(context.getResources().getColor(R.color.col_test_question_bg)));
        hm.put(COL_TEST_CHOICE_BG, String.valueOf(context.getResources().getColor(R.color.col_test_choice_bg)));

        hm.put(COL_TEST_SCORE_TXT, String.valueOf(Color.BLACK));
        hm.put(COL_TEST_QUESTION_TXT, String.valueOf(Color.BLACK));
        hm.put(COL_TEST_CHOICE_TXT, String.valueOf(Color.BLACK));

        hm.put(TXT_SIZE_TEST_QUESTION, String.valueOf(30));
        hm.put(TXT_SIZE_TEST_CHOICE, String.valueOf(14));

        hm.put(COL_SETTINGS_BG,String.valueOf(context.getResources().getColor(R.color.col_settings_bg)));
        hm.put(COL_SETTINGS_TXT,String.valueOf(context.getResources().getColor(R.color.col_black)));
        hm.put(DUPLICATION_CHCK, "current");
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
