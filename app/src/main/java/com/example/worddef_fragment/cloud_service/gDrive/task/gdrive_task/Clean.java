package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;
import android.content.Intent;

import com.example.worddef_fragment.other.ScannerActivity;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;

public class Clean extends GDriveTask {
    public Clean(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
        return clean();
    }

    private Boolean clean() {
        Boolean result=false;

        try {
            FileList fileList=token.getDriveService().files().list().setSpaces("appDataFolder").execute();

            for(File file: fileList.getFiles()) {
                if(file.getName().equals("Potkal.zip")) {
                    token.getDriveService().files().delete(file.getId()).execute();
                    token.getDriveService().files().list().setSpaces("appDataFolder").execute().clear();
                    System.out.println("CLEAANEDDD");
                }
            }
            result= true;
        } catch (UserRecoverableAuthIOException userRecoverableAuthIOException) {
            Intent requestAgainGDrive = userRecoverableAuthIOException.getIntent();
            new ScannerActivity().scanForActivity(context).startActivity(requestAgainGDrive);
            return false;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            result = false;
        }
        return result;
    }
}
