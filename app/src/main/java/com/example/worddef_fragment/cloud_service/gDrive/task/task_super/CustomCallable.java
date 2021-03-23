package com.example.worddef_fragment.cloud_service.gDrive.task.task_super;

import java.util.concurrent.Callable;

public interface CustomCallable<R> extends Callable<R> {
    void setDataAfterLoading(R result);
    boolean afterFinished();
    void setUiForLoading();
}
