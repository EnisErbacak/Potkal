package com.example.worddef_fragment.cloud_service.gDrive;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive.TaskBackUp;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive.TaskRestore;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.TaskRunner;
import com.example.worddef_fragment.misc.editText.Toaster;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GClass {
    private GoogleSignInOptions gso,gdo;//SET THOSE TWO EVEN WITHOUT SIGN-IN
    private GoogleSignInClient mGoogleSignInClient;
    private String accountName;
    private final Scope SCOPE_GDRIVE_APPDATA_READ=new Scope("https://www.googleapis.com/auth/drive.appdata");
    Context context;

    private Drive mDriveService;

    public GClass(Context context) {
        this.context = context;
    }

    public void silentLogin() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        try {
            mGoogleSignInClient = GoogleSignIn.getClient((Activity)context, gso);
        } catch (NullPointerException nullPointerException) {
            login();
        }

        mGoogleSignInClient.silentSignIn().addOnSuccessListener(
                new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(GoogleSignInAccount googleSignInAccount)
                    {
                        accountName=googleSignInAccount.getDisplayName();
                        Toaster.show(context,"WELCOME\n"+googleSignInAccount.getDisplayName());
                    }
                }).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toaster.show(context,"FAILED TO SILENT LOGIN!");
                        login();
                    }
                }
        );
    }

    public void login() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(scanForActivity(context), gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        scanForActivity(context).startActivityForResult(signInIntent,1);
    }

    private GoogleSignInClient buildGoogleSignInClient() {
        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestScopes(SCOPE_GDRIVE_APPDATA_READ)
                        .build();

        return GoogleSignIn.getClient(scanForActivity(context), signInOptions);
    }

    private void download() {
        new TaskRunner().executeAsync(new TaskRestore(context, "TASK RESTORE",mDriveService
                ,scanForActivity(context).findViewById(R.id.pBarWordSet)));
    }

    void upload() {
        new TaskRunner().executeAsync(new TaskBackUp(context, "BACKUP", scanForActivity(context), mDriveService,scanForActivity(context).findViewById(R.id.pBarWordSet)));
    }

    public void restore() {
        if(connect2Drive()) download();
    }
    public void backup() {if(connect2Drive()) upload(); }

    private boolean connect2Drive() {
        boolean result=false;
        //Toaster.showBckGrnd("CONNECTING TO GDRIVE...");

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(scanForActivity(context));

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
            if(getDrive(googleAccountCredential,account)!=null) result=true;
        }

        if(loggedIn==false) {
            //Toaster.show(context, "LOGGED IN FALSE!!!!!");
            result=false;
        }
        return result;
    }

    public Drive getDrive(GoogleAccountCredential googleAccountCredential, GoogleSignInAccount account) {
        googleAccountCredential.setSelectedAccount(account.getAccount());
        return mDriveService=new Drive.Builder(
                new NetHttpTransport()
                ,new GsonFactory()
                ,googleAccountCredential)
                .setApplicationName("Sign In GDrive")
                .build();
    }

    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }
}
