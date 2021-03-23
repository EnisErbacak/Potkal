package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.CustomTask;
import com.example.worddef_fragment.misc.editText.Toaster;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskUploadZip extends CustomTask {

    private final Executor mExecutor= Executors.newSingleThreadExecutor();
    private Drive mDriveService;
    private Context context;
    private Exception exception=null;
    private ProgressBar pBar;

    static final int REQUEST_AUTHORIZATION = 2;

    public TaskUploadZip(Context context, String taskName, Drive mDriveService, ProgressBar pBar) {
        super(context, taskName);
        this.mDriveService=mDriveService;
        this.context=context;
        this.pBar=pBar;
    }

    private String getZipPath(Context context) {
        return  context.getFilesDir().getPath() + java.io.File.separator + "Zip Files"+ java.io.File.separator+"Potkal.zip";
    }

    public Task<String> createZipInGdrive(final String fileName, final String filePath) {
        return Tasks.call(mExecutor, new Callable<String>() {
            @Override
            public String call() throws Exception {
                File fileMetaData = new File();
                fileMetaData.setName(fileName);
                fileMetaData.setParents(Collections.singletonList("appDataFolder"));

                java.io.File file = new java.io.File(filePath);

                FileContent mediaContent = new FileContent("application/zip", file);

                File myFile = null;

                try {
                    myFile = mDriveService.files().create(fileMetaData, mediaContent).execute();

                    Thread.sleep(3000);
                    new Handler(Looper.getMainLooper()).post(new Runnable(){
                        @Override
                        public void run() {
                            pBar.setVisibility(View.INVISIBLE);
                            //new FileOperator().cleanAfterBckup(context );
                        }
                    });

                    System.out.println("---GDRIVE: UPLOADED!");
                    Toaster.show(context,"BACKED UP!");

                } catch (UserRecoverableAuthIOException e) {
                    //Toaster.show(context, "RECONNECTING TO GDRIVE...");
                    Intent intent=e.getIntent();
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toaster.show(context, e.getMessage());
                }
                if (myFile == null) {
                    Toaster.show(context, "NULL GDRIVE FILE!");
                    throw new IOException("null result when requesting file creation");
                }
                return myFile.getId();
            }
        });
    }

    public void backupZip() {
        createZipInGdrive("Potkal.zip", getZipPath(context));
    }

    @Override
    public Object call() throws Exception {
        try {
            backupZip();
        }
        catch (Exception e){exception=e;}
        return super.call();
    }

    @Override
    public void setDataAfterLoading(Object result) {
        if(exception!=null)
            Toaster.show(context,exception.getCause().toString());
        super.setDataAfterLoading(result);
    }
}
