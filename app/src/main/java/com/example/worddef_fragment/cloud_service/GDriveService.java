package com.example.worddef_fragment.cloud_service;


import android.content.Context;

import com.example.worddef_fragment.cloud_service.gDrive.GClass;

public class GDriveService implements CloudService {


    @Override
    public boolean upload(Context context, String path) {
        new GClass(context).backup();
        return false;
    }

    @Override
    public boolean download(Context context, String path) {
        new GClass(context).restore();
        return false;
    }
}
