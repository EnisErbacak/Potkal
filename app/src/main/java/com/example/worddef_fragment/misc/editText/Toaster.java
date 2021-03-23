package com.example.worddef_fragment.misc.editText;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class Toaster extends Toast
{

    public Toaster(Context context) {
        super(context);
    }

    public static void show(Context context, final String msg) {
        if (context != null && msg != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
