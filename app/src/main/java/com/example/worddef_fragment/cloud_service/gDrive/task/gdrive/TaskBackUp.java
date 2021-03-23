package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive;

import android.app.Activity;
import android.content.Context;
import android.widget.ProgressBar;

import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.TaskRunner;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.CustomTask;
import com.google.api.services.drive.Drive;

/*
        THIS CLASS IS NOT USED DELETE IT...
 */
public class TaskBackUp extends CustomTask
{
    Context context;
    Activity activity;
    Drive mDrive;
    ProgressBar pBar;

    public TaskBackUp(Context context, String taskName,Activity activity, Drive mDrive, ProgressBar pBar) {
        super(context, taskName);
        this.context=context;
        this.activity=activity;
        this.mDrive=mDrive;
        this.pBar=pBar;
    }

    @Override
    public Object call() throws Exception {
        new TaskRunner().executeAsync(new TaskCleanGDrive(context, "CLEAN GDRIVE", activity, mDrive, pBar));
        return null;
    }
}
