package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive;

import android.content.Context;
import android.content.Intent;

import com.example.worddef_fragment.misc.editText.Toaster;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.CustomTask;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TaskDownloadZip extends CustomTask {
    com.google.api.services.drive.Drive.Files.Get request;
    Context context;
    public TaskDownloadZip(Context context, String taskName, com.google.api.services.drive.Drive.Files.Get request) {
        super(context, taskName);
        this.request=request;
        this.context=context;
    }

    public void downloadZip(com.google.api.services.drive.Drive.Files.Get request) {
        String pathDwnld=context.getFilesDir().getPath()+ File.separator+"download";
        OutputStream out = null;
        checkDir(pathDwnld);
        try {

            out = new FileOutputStream(new java.io.File(pathDwnld, "Potkal.zip"));
            System.out.println("---------"+new File(pathDwnld).listFiles()[0].length());
            request.executeMediaAndDownloadTo(out);
            Thread.sleep(10000);
            System.out.println("---------"+new File(pathDwnld).listFiles()[0].length());
            System.out.println("---GDRIVE: DOWNLOADED!");
            //new FileOperator().importFile(context);
        }
        catch (UserRecoverableAuthIOException userRecoverableAuthIOException) {
            Intent requestAgainGDrive = userRecoverableAuthIOException.getIntent();
            context.startActivity(requestAgainGDrive);
        }
        catch (Exception e) {
            Toaster.show(context, e.getMessage());
        }
        finally{
            try{
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public Object call() throws Exception {
        downloadZip(request);
        return super.call();
    }

    public void checkDir(String path) {
        String pathDwnld=context.getFilesDir().getPath()+File.separator+"download";
        File dir = new File (pathDwnld);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
