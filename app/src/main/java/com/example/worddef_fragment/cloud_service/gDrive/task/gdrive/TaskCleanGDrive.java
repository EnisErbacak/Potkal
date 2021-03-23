package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import com.example.worddef_fragment.misc.editText.Toaster;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.CustomTask;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.TaskRunner;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskCleanGDrive extends CustomTask {
    Context context;
    Activity activity;
    Drive mDriveService;
    private  ProgressBar pBar;
    public TaskCleanGDrive(Context context, String taskName, Activity activity, Drive mDriveService, ProgressBar pBar) {
        super(context, taskName);
        this.context=context;
        this.activity=activity;
        this.mDriveService=mDriveService;
        this.pBar=pBar;
    }

    private void callBackup() {
        new TaskRunner().executeAsync(new TaskUploadZip(context, "Backup Zip", mDriveService,pBar));
    }

    @Override
    public Object call() throws Exception {
        new Handler(Looper.getMainLooper()).post(new Runnable(){
            @Override
            public void run() {
                pBar.setVisibility(View.VISIBLE);
            }
        });
        clean();
        return super.call();
    }

    private void clean() {

        Executor mExecutor= Executors.newSingleThreadExecutor();

        Tasks.call(mExecutor, new Callable<FileList>() {
            @Override
            public FileList call() {
                try {
                    FileList fileList = mDriveService.files().list().setSpaces("appDataFolder").execute();
                    if (fileList.getFiles().size() == 0) {
                        callBackup();
                    }
                    else {
                        for (int i = 0; i < fileList.getFiles().size(); i++) {
                            System.out.println(fileList.getFiles().get(i));
                            //Toaster.showBckGrnd(fileList.getFiles().get(i).getName());
                            if(fileList.getFiles().get(i).getName().equals("WordDefZip.zip")) {
                                mDriveService.files().delete(fileList.getFiles().get(i).getId()).execute();
                                mDriveService.files().list().setSpaces("appDataFolder").execute().clear();
                                System.out.println("---GDRIVE: CLEANED!");
                            }
                        }
                        callBackup();
                    }
                    return fileList;
                } catch (UserRecoverableAuthIOException userRecoverableAuthIOException) {
                    //Toaster.showBckGrnd("RECONNECTING TO GDRIVE...");
                    userRecoverableAuthIOException.printStackTrace();
                    Intent requestAgainGDrive = userRecoverableAuthIOException.getIntent();
                    activity.startActivity(requestAgainGDrive);
                    return null;

                } catch (Exception e) {
                    Toaster.show(context, e.getMessage());
                    e.printStackTrace();
                    return null;
                }
                finally {
                  //  progressBar.setAlpha(0);
                }
            }
        });
    }

}
