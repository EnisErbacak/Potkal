package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;
import android.content.Intent;

import com.example.worddef_fragment.other.ScannerActivity;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Download extends GDriveTask {
    public Download(Context context, Token token) {
        super(context, token);
    }
    Boolean result=false;
    @Override
    public boolean perform() {
        return downloadZip();
    }

    private Boolean downloadZip() {
        boolean result=false;

        String pathDwnld=context.getFilesDir().getPath()+ java.io.File.separator+"download";
        OutputStream out = null;
        checkDir(pathDwnld);

        try {
            out = new FileOutputStream(new java.io.File(pathDwnld, "Potkal.zip"));
            FileList fileList=token.getDriveService().files().list().setSpaces("appDataFolder").execute();
            fileList.getFiles();

            for(File file: fileList.getFiles()) {
                if(file.getName().equals("Potkal.zip")) {
                    System.out.println("POTKAL.ZIP FOUND!");

                    token.getDriveService().files().get(file.getId()).executeMediaAndDownloadTo(out);
                    result= true;
                    break;
                }
            }
        } catch (UserRecoverableAuthIOException userRecoverableAuthIOException) {
            Intent requestAgainGDrive = userRecoverableAuthIOException.getIntent();
            new ScannerActivity().scanForActivity(context).startActivity(requestAgainGDrive);
            return false;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            result = false;
        }
        finally {
            try {
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return result;
    }

    public void checkDir(String path) {
        String pathDwnld=context.getFilesDir().getPath()+ java.io.File.separator+"download";
        java.io.File dir = new java.io.File(pathDwnld);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}