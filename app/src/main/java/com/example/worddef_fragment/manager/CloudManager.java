package com.example.worddef_fragment.manager;

import android.content.Context;

public interface CloudManager {
    public boolean backup(Context context, String srcPath);
    public boolean restore(Context context, String trgtDir);
}
