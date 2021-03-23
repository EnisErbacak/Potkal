package com.example.worddef_fragment.manager;

import android.content.Context;
import android.provider.DocumentsContract;

import com.example.worddef_fragment.cloud_service.CloudService;
import com.example.worddef_fragment.cloud_service.CloudServiceFactory;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task.GDriveTask;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task.GDriveTaskFactory;
import com.example.worddef_fragment.cloud_service.gDrive.task.gdrive_task.Token;
import com.example.worddef_fragment.file.controller.FileController;
import com.example.worddef_fragment.file.operator.BasicFileOperator;
import com.example.worddef_fragment.file.operator.FileOperator;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.file.transporter.FileTransferFactory;
import com.example.worddef_fragment.file.transporter.FileTransporter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class GdriveManager implements CloudManager{

    private CloudService cloudService;
    private PathPicker pathPicker;
    private FileTransporter fileTransporter;

    public GdriveManager() {
        cloudService=new CloudServiceFactory().create("gdrive");
    }

    @Override
    public boolean backup(Context context, String srcPath) {
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



                    GDriveTask login = taskFactory.create("login", context, token);
                    GDriveTask connect = taskFactory.create("connect", context, token);
                    GDriveTask clean = taskFactory.create("clean", context, token);
                    GDriveTask upload = taskFactory.create("upload", context, token);

                    if(login.perform())
                        if(connect.perform())
                            if(clean.perform())
                                if(upload.perform())
                                    System.out.println("FINISHHHHHHHH");

                    /*
                    Future<Boolean> futureLogin = executor2.submit(login);

                    try {
                        if (futureLogin.get() == true) {
                            Future<Boolean> futureConnectGDrive = executor2.submit(connect);

                            if (futureConnectGDrive.get() == true) {
                                Future<Boolean> futureClean = executor2.submit(clean);

                                if (futureClean.get() == true) {
                                    Future<Boolean> futureUpload = executor2.submit(upload);
                                    if (futureUpload.get() == true) {
                                        System.out.println("FINISHED PROPERLY");
                                    }
                                }
                            }
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                     */


                    executor2.shutdown();
                }
            }

        });
        executor.shutdown();


        //cloudService.upload(context, zipPath); // --Upload File name must set in GDrive as "Potkal.zip"

        new BasicFileOperator().deleteDir(new PathPickerFactory().create("download").get(context)); // --removeZip

        return false;
    }


    @Override
    public boolean restore(Context context, String trgtDir) {

        Token token=new Token();

        ExecutorService executor= Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {

                ExecutorService executor2= Executors.newCachedThreadPool();
                GDriveTaskFactory taskFactory=new GDriveTaskFactory();
                GDriveTask login=taskFactory.create("login", context,token);
                GDriveTask connect=taskFactory.create("connect", context,token);
                GDriveTask download=taskFactory.create("download", context,token);

                if(login.perform())
                    if(connect.perform())
                        if(download.perform())
                            System.out.println("FINISHHHHHHHH");


                /*
                Future<Boolean> futureLogin=executor2.submit(login);
                try {
                    if(futureLogin.get()==true) {
                        Future<Boolean> futureConnectGDrive= executor2.submit(connect);
                            if(futureConnectGDrive.get()==true) {
                                Future<Boolean> futureDownlaodZip=executor2.submit(download);
                                    if(futureDownlaodZip.get()==true) {
                                        System.out.println("FINISHED PROPERLY");

                                        FileOperator fileOperator=new BasicFileOperator();
                                        fileTransporter=new FileTransferFactory().create("unzip");
                                        FileController fileController=new FileController();

                                        String downloadPath=new PathPickerFactory().create("download").get(context);
                                        String wordSetPath=new PathPickerFactory().create("wordset").get(context);
                                        fileController.controlDir(wordSetPath);

                                        fileTransporter.transfer(downloadPath, wordSetPath);

                                        fileOperator.deleteDir(downloadPath);
                                    }
                            }
                        }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 */

                System.out.println("FINISHHHHHHHH");
                executor2.shutdown();





            }
        });
        executor.shutdown();
        return false;
    }
}
