package com.example.worddef_fragment.other;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

public class ScannerActivity {
    public Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }
}
