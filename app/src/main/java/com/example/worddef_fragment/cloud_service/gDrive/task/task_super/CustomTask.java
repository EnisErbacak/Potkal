package com.example.worddef_fragment.cloud_service.gDrive.task.task_super;

import android.content.Context;
import android.widget.ProgressBar;

public class CustomTask<R> implements  CustomCallable {
    private Context context;
    private String taskName;
    private ProgressBar pBar;
    public CustomTask(Context context,String taskName) {
        this.context=context;
        this.taskName=taskName;
    }

    @Override
    public void setDataAfterLoading(Object result) {

    }

    @Override
    public boolean afterFinished() {
        return false;
    }

    @Override
    public void setUiForLoading() {
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}


