package com.example.worddef_fragment.cloud_service.gDrive.task.gdrive;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.CustomTask;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.TaskRunner;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskRestore extends CustomTask
{
    private final Executor mExecutor= Executors.newSingleThreadExecutor();
    private Drive driveService;
    private Context context;
    private com.google.api.services.drive.Drive.Files.Get request;
    private ProgressBar pBar;

    public TaskRestore(Context context, String taskName, Drive driveService, ProgressBar pBar) {
        super(context, taskName);
        this.driveService= driveService;
        this.context=context;
        this.pBar=pBar;
    }

    @Override
    public Object call() throws Exception {
        new Handler(Looper.getMainLooper()).post(new Runnable(){
            @Override
            public void run() {
                pBar.setVisibility(View.VISIBLE);
            }
        });
       getRequest();
        return super.call();
    }

    private com.google.api.services.drive.Drive.Files.Get getRequest() {
        queryFiles().addOnSuccessListener(new OnSuccessListener<FileList>() {
            OutputStream out;
            @Override
            public void onSuccess(FileList fileList) {
                for (int i = 0; i < fileList.getFiles().size(); i++) {
                    int finalI = i;
                    getFile(fileList.getFiles().get(i).getId(), i).addOnSuccessListener(new OnSuccessListener<Pair<String, String>>() {
                        @Override
                        public void onSuccess(Pair<String, String> stringStringPair) {
                            com.google.api.services.drive.model.File file = fileList.getFiles().get(finalI);
                            if (fileList.getFiles().get(finalI).getName().equals("Potkal.zip")) {
                                com.google.api.services.drive.model.File zipFile = (com.google.api.services.drive.model.File) fileList.getFiles().get(finalI);
                                //downloadFile(zipFile);

                                try {
                                    request = driveService.files().get(zipFile.getId());
                                   new TaskRunner().executeAsync(new TaskDownloadZip(context,"Download Zip",request));

                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }});
        return request;
    }

    public Task<FileList> queryFiles() {
        return Tasks.call(mExecutor, new Callable<FileList>() {
            @Override
            public FileList call() throws Exception {
                return driveService.files().list().setSpaces("appDataFolder").execute();
            }
        });
    }

    public Task<Pair<String, String>> getFile(final String fileId, final int i)
    {
        return Tasks.call(mExecutor, new Callable<Pair<String, String>>() {
            //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public Pair<String, String> call() throws Exception {
                // Retrieve the metadata as a File object.
                //File metadata = mDriveService.files().get(fileId).execute();

                FileList files = driveService.files().list()
                        .setSpaces("appDataFolder")
                        .setFields("nextPageToken, files(id, name)")
                        //.setPageSize(10)
                        .execute();

                com.google.api.services.drive.model.File metaData = files.getFiles().get(i);

                String name = metaData.getName();

                // Stream the file contents to a String.
                try (InputStream is = driveService.files().get(fileId).executeMediaAsInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    String contents = stringBuilder.toString();

                    System.out.println("");
                    return Pair.create(name, contents);
                }
            }
        });
    }
}
