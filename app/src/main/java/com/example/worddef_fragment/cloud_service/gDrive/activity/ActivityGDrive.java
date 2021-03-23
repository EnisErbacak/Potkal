package com.example.worddef_fragment.cloud_service.gDrive.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive.TaskBackUp;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive.TaskRestore;
import com.example.worddef_fragment.misc.editText.Toaster;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.TaskRunner;
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

public class ActivityGDrive extends AppCompatActivity {

    public static final int ORDER_START=0;
    public static final int ORDER_LOGIN=1;

    public static final int ORDER_DOWNLOAD=2;
    public static final int ORDER_UPLOAD=3;
    public static final int ORDER_REQ=4;



    public static ProgressBar pBar;

    private GoogleSignInOptions gso,gdo;//SET THOSE TWO EVEN WITHOUT SIGN-IN
    private GoogleSignInClient mGoogleSignInClient;
    private final Scope SCOPE_GDRIVE_APPDATA_READ=new Scope("https://www.googleapis.com/auth/drive.appdata");
    private String accountName;
    private Context context;

    private Drive mDriveService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyOrder();
        setContentView( R.layout.activity_g_drive2);
        this.context=getApplicationContext();
        System.out.println("GDRIVE STARTRED");
    }


    private void applyOrder() {
        int orderNo= getIntent().getIntExtra("order",0);

        Context context=getApplicationContext();
        Context context1=getBaseContext();



//        ProgressBar progressBar=scanForActivity(context).findViewById(R.id.pBarWordSet);
//        ProgressBar progressBar2=scanForActivity(context1).findViewById(R.id.pBarWordSet);

        switch (orderNo) {
            case ORDER_START:
                silentLogin();
                break;
            case ORDER_LOGIN:
                login();
                break;
            case ORDER_DOWNLOAD:
                startDownload();
                break;
            case ORDER_UPLOAD:
                startUpload();
                break;
            case ORDER_REQ:
                requestGDrive();
                break;
            default :
                onBackPressed();
        }
    }
    public static ProgressBar getpBar() {
        return pBar;
    }

    public static void setpBar(ProgressBar pBar) {
        ActivityGDrive.pBar = pBar;
    }

    void login() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(ActivityGDrive.this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, ORDER_LOGIN);
    }

    void silentLogin() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        try {
            mGoogleSignInClient = GoogleSignIn.getClient(ActivityGDrive.this, gso);
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
        onBackPressed();
    }

    private GoogleSignInClient buildGoogleSignInClient() {
        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestScopes(SCOPE_GDRIVE_APPDATA_READ)
                        .build();

        return GoogleSignIn.getClient(this, signInOptions);
    }



    void requestGDrive() {
        startActivity(buildGoogleSignInClient().getSignInIntent());
        onBackPressed();
    }

    private boolean connect2Drive() {
        boolean result=false;
        //Toaster.showBckGrnd("CONNECTING TO GDRIVE...");

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(ActivityGDrive.this);

        GoogleAccountCredential googleAccountCredential = GoogleAccountCredential
                .usingOAuth2(this, Collections.singleton(DriveScopes.DRIVE_APPDATA));

        boolean loggedIn=false;
        try {
            account.getAccount();
            loggedIn=true;
        }
        catch (NullPointerException nullPointerException) {
            Toaster.show(context, "LOG IN FIRST PLEASE");
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
            Toaster.show(context, "LOGGED IN FALSE!!!!!");
            result=false;
        }
        onBackPressed();
        return result;
    }

    public Drive getDrive(GoogleAccountCredential googleAccountCredential,GoogleSignInAccount account) {
        googleAccountCredential.setSelectedAccount(account.getAccount());
         return mDriveService=new Drive.Builder(
                new NetHttpTransport()
                ,new GsonFactory()
                ,googleAccountCredential)
                .setApplicationName("Sign In GDrive")
                .build();
    }


    void startUpload() {
        if(connect2Drive()) upload();
    }
    void startDownload() {
        if(connect2Drive()) download();
    }

    void upload() {
        //new TaskRunner().executeAsync(new TaskCleanGDrive(getApplicationContext(), "TASK BACKUP", ActivityGDrive.this, mDriveService,pBar));
        new TaskRunner().executeAsync(new TaskBackUp(getApplicationContext(), "TASK BACKUP", ActivityGDrive.this, mDriveService,pBar));
    }


    void download() {
        new TaskRunner().executeAsync(new TaskRestore(getApplicationContext(), "TASK RESTORE",mDriveService,getpBar()));
    }

    public void setLoading(ProgressBar pBar) {
        pBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("GDRIVE DEAD NOW");
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