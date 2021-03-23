package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;

public abstract class GDriveTask {
    public Token token;
    public Context context;

    public GDriveTask(Context context, Token token) {
        this.token = token;
        this.context=context;
    }

    public abstract boolean perform();
}
