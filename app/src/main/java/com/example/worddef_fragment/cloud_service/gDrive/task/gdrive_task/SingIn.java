package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;
import android.content.Intent;

import com.example.worddef_fragment.other.ScannerActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SingIn extends GDriveTask{

    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;

    public SingIn(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(new ScannerActivity().scanForActivity(context), googleSignInOptions);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        new ScannerActivity().scanForActivity(context).startActivityForResult(signInIntent,1);

        token.setGoogleSignInClient(googleSignInClient);
        token.setGoogleSignInOptions(googleSignInOptions);

        System.out.println("--------LOGGED IN");

        return  true;
    }
}
