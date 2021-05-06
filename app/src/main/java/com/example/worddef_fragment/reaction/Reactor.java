package com.example.worddef_fragment.reaction;

import android.content.Context;
import android.widget.Toast;

import com.example.worddef_fragment.other.ScannerActivity;

public class Reactor {
    private Context context;

    public Reactor(Context context) {
        this.context = context;
    }

    public void showShort(String msg) {
        new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}