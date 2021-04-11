package com.example.worddef_fragment.cloud_service.manager;

import android.app.Activity;
import android.content.Context;
import android.widget.ProgressBar;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task.GDriveTask;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task.GDriveTaskFactory;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task.Token;
import com.example.worddef_fragment.file.controller.FileController;
import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.operator.FileOperator;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.file.transporter.FileTransferFactory;
import com.example.worddef_fragment.file.transporter.FileTransporter;
import com.example.worddef_fragment.other.ScannerActivity;
import com.example.worddef_fragment.reaction.Reaction;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GdriveManager implements CloudManager{

    private PathPicker pathPicker;
    private FileTransporter fileTransporter;
    private ProgressBar pb;

    public GdriveManager(ProgressBar pb) {
        this.pb=pb;
    }

    @Override
    public boolean backup(Context context, String srcPath) {

        pb.setAlpha(1);
        FileController fileController=new FileController();
        fileTransporter=new FileTransferFactory().create("zip");
        String zipPath=new PathPickerFactory().create("zip").get(context);

        Token token=new Token();
        ExecutorService executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                fileController.controlDir(zipPath);

                if(fileTransporter.transfer(srcPath, zipPath)) { // --Zipping file

                    ExecutorService executor2 = Executors.newCachedThreadPool();
                    GDriveTaskFactory taskFactory = new GDriveTaskFactory();

                    GDriveTask login = taskFactory.create("signin", context, token);
                    GDriveTask connect = taskFactory.create("connect", context, token);
                    GDriveTask clean = taskFactory.create("clean", context, token);
                    GDriveTask upload = taskFactory.create("upload", context, token);

                    if(login.perform())
                        if(connect.perform())
                            if(clean.perform())
                                if(upload.perform()) {
                                    System.out.println("FINISHHHHHHHH");
                                    new FileManager().operate().deleteDir(new PathPickerFactory().create("zip").get(context)); // --removeZip
                                    new Reaction(context).showShort(context.getResources().getString(R.string.backed_up));
                                }
                    executor2.shutdown();
                    new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pb.setAlpha(0);
                        }
                    });
                }
            }});
        executor.shutdown();
        return false;
    }


    @Override
    public boolean restore(Context context, String trgtDir) {
        pb.setAlpha(1);
        Token token=new Token();
        ExecutorService executor= Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {

                ExecutorService executor2= Executors.newCachedThreadPool();
                GDriveTaskFactory taskFactory=new GDriveTaskFactory();
                GDriveTask login=taskFactory.create("signin", context,token);
                GDriveTask connect=taskFactory.create("connect", context,token);
                GDriveTask download=taskFactory.create("download", context,token);

                if(login.perform())
                    if(connect.perform())
                        if(download.perform()) {

                            FileOperator fileOperator=new FileManager().operate();
                            fileTransporter=new FileTransferFactory().create("unzip");
                            FileController fileController=new FileController();

                            String downloadPath=new PathPickerFactory().create("download").get(context);
                            String wordSetPath=new PathPickerFactory().create("wordset").get(context);
                            fileController.controlDir(wordSetPath);

                            fileTransporter.transfer(downloadPath, wordSetPath);
                            fileOperator.deleteDir(downloadPath);

                            System.out.println("FINISHHHHHHHH");
                            new Reaction(context).showShort(context.getResources().getString(R.string.restored));
                        }
                executor2.shutdown();
                new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb.setAlpha(0);
                    }
                });
            }});
        executor.shutdown();

        return false;
    }

    private boolean silentSignIn(Token token,Context context) {
        boolean result=false;

        token.setGoogleSignInOptions( new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build());
        try {
            token.setGoogleSignInClient(GoogleSignIn.getClient((Activity)context, token.getGoogleSignInOptions()));
            result=true;
        }catch (NullPointerException nex){
            result=false;
        }
        return result;
    }
}
