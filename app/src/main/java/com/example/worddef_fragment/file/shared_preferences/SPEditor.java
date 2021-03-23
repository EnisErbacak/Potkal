package com.example.worddef_fragment.file.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

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




    private void initialize() {
        hm=new HashMap<>();
        hm.put(SET_TXT_SIZE,"35");
        hm.put(WORD_TXT_SIZE,"25");
        hm.put(DEF_TXT_SIZE,"15");
        hm.put(APP_THEME, "eng");
        hm.put(APP_LANG,"eng");
        hm.put(APPEARANCE, "detailed");
    }

    public void setValues(Context context, String key, String value) {
        SharedPreferences sp= context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public void start(Context context) {
        initialize();
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
