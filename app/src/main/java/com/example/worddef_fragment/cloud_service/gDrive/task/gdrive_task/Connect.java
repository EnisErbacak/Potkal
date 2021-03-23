package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;

import com.example.worddef_fragment.misc.editText.Toaster;
import com.example.worddef_fragment.other.ScannerActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.util.Collections;

public class Connect extends GDriveTask {
    public Connect(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
        boolean result=false;
        //Toaster.showBckGrnd("CONNECTING TO GDRIVE...");

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(new ScannerActivity().scanForActivity(context));

        GoogleAccountCredential googleAccountCredential = GoogleAccountCredential
                .usingOAuth2(context, Collections.singleton(DriveScopes.DRIVE_APPDATA));

        boolean loggedIn=false;
        try {
            account.getAccount();
            loggedIn=true;
        }
        catch (NullPointerException nullPointerException) {
            Toaster.show(context, "SIGN IN FIRST PLEASE");
            loggedIn=false;
            result=false;
        }
        catch (Exception exception) {
            Toaster.show(context,"SOMETHING WENT WRONG!");
            loggedIn=false;
            result=false;
        }

        if(loggedIn==true) {
            Drive drive=getDrive(googleAccountCredential,account);
            if(drive !=null) {
                token.setDriveService(drive);
                System.out.println("--------CONNECTED!");
                result=true;
            }
        }

        if(loggedIn==false) {
            //Toaster.show(context, "LOGGED IN FALSE!!!!!");
            result=false;
        }
        return result;
    }

    private Drive getDrive(GoogleAccountCredential googleAccountCredential, GoogleSignInAccount account) {
        googleAccountCredential.setSelectedAccount(account.getAccount());
        return  new Drive.Builder(
                new NetHttpTransport()
                ,new GsonFactory()
                ,googleAccountCredential)
                .setApplicationName("Sign In GDrive")
                .build();
    }
}
