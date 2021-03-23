package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;

public class GDriveTaskFactory {

    public GDriveTask create(String task, Context context, Token token) {
        GDriveTask returnTask;
        switch (task.toLowerCase()) {
            case "login":
                returnTask= new Login(context, token);
                break;
            case "connect":
                returnTask= new Connect(context, token);
            break;
            case "download":
                returnTask= new Download(context, token);
            break;
            case "upload":
                returnTask= new Upload(context, token);
                break;
            case  "clean":
                returnTask= new Clean(context,token);
            break;
            default:
                returnTask= null;
        }
        return  returnTask;
    }
}
