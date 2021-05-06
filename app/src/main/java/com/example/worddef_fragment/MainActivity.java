package com.example.worddef_fragment;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.widget.Toast;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;


import java.util.Locale;

public class MainActivity extends FragmentActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCondition();
        startFragment(savedInstanceState);
    }

    private void setCondition() {
        new FileManager().operate().createDir(new PathPicker(getApplicationContext()).get(PathPicker.WORDSET));
    }

    private void startFragment(Bundle savedInstanceState) {
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerActivityMain, FragmentWordSet.getInstance()).commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) endApp();
        else getSupportFragmentManager().popBackStack();
    }

    private void endApp() {
        Toast.makeText(getBaseContext(), getBaseContext().getResources().getString(R.string.goodbye), Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        finish();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        new SPEditor().firstStart(newBase);

        String lang=newBase.getSharedPreferences("SharedPref", newBase.MODE_PRIVATE).getString(SPEditor.APP_LANG,"");
        Locale myLocale = new Locale(lang);
        Resources res = newBase.getResources();
        Configuration configuration = res.getConfiguration();
        configuration.setLocale(myLocale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(myLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            newBase = newBase.createConfigurationContext(configuration);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(myLocale);
            newBase = newBase.createConfigurationContext(configuration);
        } else {
            configuration.locale = myLocale;
            res.updateConfiguration(configuration, res.getDisplayMetrics());
        }
        super.attachBaseContext(newBase);
    }
}