package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;
import android.content.Intent;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.reaction.Reactor;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.Collections;

public class Upload extends GDriveTask {
    public Upload(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
        return upload();
    }

    private Boolean upload() {
        boolean result=false;

        Reactor reactor =new Reactor(context);
        File fileMetaData = new File();
        fileMetaData.setName("Potkal.zip");
        fileMetaData.setParents(Collections.singletonList("appDataFolder"));

        String zipPath=new PathPicker(context).get(PathPicker.ZIP);
        java.io.File file = new java.io.File(zipPath + java.io.File.separator+ "Potkal.zip");

        FileContent mediaContent = new FileContent("application/zip", file);

        File myFile = null;

        try {
            myFile = token.getDriveService().files().create(fileMetaData, mediaContent).execute();

            //Thread.sleep(3000);

            System.out.println("---GDRIVE: UPLOADED!");
            result=true;

        } catch (UserRecoverableAuthIOException e) {
            //Toaster.show(context, "RECONNECTING TO GDRIVE...");
            Intent intent=e.getIntent();
            context.startActivity(intent);
            result=false;
        } catch (Exception e) {
            reactor.showShort(e.getMessage());
            result=false;
        }
        if (myFile == null) {
            reactor.showShort( "NULL GDRIVE FILE!");
            result=false;
            try {
                throw new IOException("null result when requesting file creation");
            } catch (IOException ioException) {
                ioException.printStackTrace();
                result=false;
            }
        }
        return result;
    }
}