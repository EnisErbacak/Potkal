package com.example.worddef_fragment.cloud_service.manager;

import android.content.Context;

public interface CloudManager {
    boolean backup(Context context, String srcPath);
    boolean restore(Context context, String trgtDir);
}
