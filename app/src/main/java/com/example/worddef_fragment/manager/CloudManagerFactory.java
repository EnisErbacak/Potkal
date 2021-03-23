package com.example.worddef_fragment.manager;

public class CloudManagerFactory {

    public CloudManager create(String managerName) {
        CloudManager cloudManager;
        switch (managerName.toLowerCase()) {
            case  "gdrive":
                cloudManager=new GdriveManager();
                break;
            default:
                cloudManager=null;
        }
        return cloudManager;
    }
}
