package com.example.worddef_fragment.cloud_service;

import android.content.Context;

/*
    Facade class template for file upload and download to/from cloud service
 */
public interface CloudService {
    boolean upload(Context context, String path);
    boolean download(Context context, String path);
}
