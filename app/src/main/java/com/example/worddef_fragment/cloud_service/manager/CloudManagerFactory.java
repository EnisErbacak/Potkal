package com.example.worddef_fragment.cloud_service.manager;

import android.widget.ProgressBar;

public class CloudManagerFactory {

    public static final String GDRIVE="gdrive";
    public CloudManager create(String managerName, ProgressBar pb) {
        CloudManager cloudManager;
        switch (managerName.toLowerCase()) {
            case  GDRIVE:
                cloudManager=new GdriveManager(pb);
                break;
            default:
                cloudManager=null;
        }
        return cloudManager;
    }
}
