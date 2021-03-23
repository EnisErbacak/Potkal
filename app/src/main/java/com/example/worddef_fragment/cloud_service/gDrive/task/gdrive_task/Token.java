package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.api.services.drive.Drive;

public class Token {
    private final Scope SCOPE_GDRIVE_APPDATA_READ=new Scope("https://www.googleapis.com/auth/drive.appdata");

    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;
    private Drive driveService;

    public Scope getSCOPE_GDRIVE_APPDATA_READ() {
        return SCOPE_GDRIVE_APPDATA_READ;
    }

    public GoogleSignInOptions getGoogleSignInOptions() {
        return googleSignInOptions;
    }

    public void setGoogleSignInOptions(GoogleSignInOptions googleSignInOptions) {
        this.googleSignInOptions = googleSignInOptions;
    }

    public GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    public void setGoogleSignInClient(GoogleSignInClient googleSignInClient) {
        this.googleSignInClient = googleSignInClient;
    }

    public Drive getDriveService() {
        return driveService;
    }

    public void setDriveService(Drive driveService) {
        this.driveService = driveService;
    }
}
