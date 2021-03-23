package com.example.worddef_fragment;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragmentWordSet.FragmentWordSet;
import com.example.worddef_fragment.cloud_service.gDrive.GClass;
import com.example.worddef_fragment.misc.editText.Toaster;

public class MainActivity extends FragmentActivity {
    private FrameLayout container;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context=getApplicationContext();
        startFragment(savedInstanceState);

        setCondition();
    }

    private void setCondition() {
        new GClass(MainActivity.this).silentLogin();
        new SPEditor().start(getApplicationContext());
    }

    private void startFragment(Bundle savedInstanceState) {
        if(savedInstanceState==null) {
            container=findViewById(R.id.containerActivityMain);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerActivityMain, FragmentWordSet.getInstance()).commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            endApp();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    private void endApp() {
        Toaster.show(context, "GOODBYE!");
        super.onBackPressed();
        finish();
    }
}