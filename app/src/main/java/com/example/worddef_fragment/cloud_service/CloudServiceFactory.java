package com.example.worddef_fragment.cloud_service;

public class CloudServiceFactory {

    public CloudService create(String serviceName) {
        CloudService cloudService;
        switch (serviceName.toLowerCase()) {
            case "gdrive":
                cloudService=new GDriveService();
                break;
            default:
                cloudService=null;
        }

        return cloudService;
    }
}
