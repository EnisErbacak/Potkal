package com.example.worddef_fragment.file.operator2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.worddef_fragment.file.editor.WordSetEditor;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.file.transporter.FileUnzipper;
import com.example.worddef_fragment.file.transporter.FileZipper;
import com.example.worddef_fragment.fragments.fragmentWordSet.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragmentWordSet.editor.UiEdtrWrdSet;
import com.example.worddef_fragment.cloud_service.gDrive.activity.ActivityGDrive;
import com.example.worddef_fragment.misc.editText.Toaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileOperator {

    FileExplorer fileExplorer;
    FileZipper fileZipper;
    FileUnzipper fileUnzipper;

    PathPickerFactory pathPickerFactory=new PathPickerFactory();

    public FileOperator() {
        fileExplorer = new FileExplorer();
        fileZipper=new FileZipper();
        fileUnzipper=new FileUnzipper();
    }

    private String getSetPath(Context context) { return context.getFilesDir().getPath() + File.separator + "WordSet Files"; }
    private String getZipPath(Context context) { return context.getFilesDir().getPath() + File.separator + "Zip Files";}
    private String getUnzipPath(Context context) { return context.getFilesDir().getPath()+File.separator+"Unzip Files";}
    private String getCompressPath(Context context) {return context.getFilesDir().getPath()+File.separator+"Compressed Files";}
    private String getDecompressPath(Context context) { return context.getFilesDir().getPath()+File.separator+"Decompressed Files";}
    private String getDownloadPath(Context context) { return context.getFilesDir().getPath()+File.separator+"download";}


    /*
    BACKUP----
    1.COMPRESS
    2.ZİP
    3.UPLOAD TO GDRIVE

    IMPORT----
    1.DOWNLOAD
    2.UNZİP
    3.DECOMPRESS
    4.MOVE TO SET FOLDER
     */

    private File[] getFiles(String path) {
        return new File(path).listFiles();
    }

    private boolean zip(Context context) {
        checkDir(getZipPath(context));
        if(!(fileZipper.zipFiles(getSetPath(context),getZipPath(context)+File.separator+"Potkal.zip"))) {
            return false;
        }
    return true;
    }

    private boolean unzip(Context context) {
        checkDir(getUnzipPath(context));
        if(fileUnzipper.unzip(getDownloadPath(context)+File.separator+"Potkal.zip",getUnzipPath(context)))
            return true;
        else
            return  false;
    }

    private void moveUnzipFiles(Context context) {
        String name;
        File[] files=getFiles(getUnzipPath(context));
        String inputPath=getUnzipPath(context);
        String outputPath=getSetPath(context);

        for(int i=0;i<files.length;i++) {
            name=files[i].getName();
            copyFile(inputPath, files[i].getName(),outputPath);
        }
    }

    private void copyFile(String inputPath, String inputFile, String outputPath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            //create output directory if it doesn't exist
            File dir = new File (outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            in = new FileInputStream(inputPath +File.separator+ inputFile);
            out = new FileOutputStream(outputPath +File.separator+ inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }


        }  catch (FileNotFoundException fnfe1) {
            fnfe1.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                in.close();
                in = null;

                // write the output file (You have now copied the file)
                out.flush();
                out.close();
                out = null;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void preventDuplication(Context context) {
        FileExplorer explorer=new FileExplorer();
        ArrayList<String> listCreatedSets=new WordSetEditor(context).getAllSetNames();

        File[] setCreated=new File(getSetPath(context)).listFiles();
        File[] fileDownloaded=new File(getDecompressPath(context)).listFiles();

        for(int i=0;i<fileDownloaded.length;i++) {
            for(int j=0;j<listCreatedSets.size();j++) {

                if(explorer.chcFileDuplication(context, listCreatedSets.get(j), fileDownloaded)) {
                    System.out.println("DUPLICATION ON _____"+listCreatedSets.get(j));

                    System.out.println("");
                    int no=1;
                    while(true) {

                        String fileName=listCreatedSets.get(j);
                        String newFileName="("+no+")"+fileName;
                        if(!explorer.chcFileDuplication(context, newFileName,setCreated)) {
                            if(!explorer.chcFileDuplication(context, newFileName,fileDownloaded)) {
                                explorer.renameFile(context, getDecompressPath(context), fileName, newFileName);
                                break;
                            }
                            else
                                no++;
                        }
                        else
                            no++;
                    }
                    listCreatedSets.remove(j);
                }
            }
        }
    }

    private void checkDir(String path) {
        File dir=new File(path);
        if(!dir.exists() && !dir.isDirectory())
            dir.mkdir();
    }

    public boolean export(Context context) {
            if (zip(context)) {
                //fileExplorer.removeDir(getCompressPath(context));
                System.out.println("---Compressed!");
                System.out.println("---Zipped!");
                ActivityGDrive.getpBar().setVisibility(View.VISIBLE);
                return true;
            } else return false;
    }

    public boolean importFile(Context context) {
        if(unzip(context)) {
                //preventDuplication(context);
                moveUnzipFiles(context);
                //cleanAfterRestore(context);
                new File(getDecompressPath(context)).deleteOnExit();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ActivityGDrive.getpBar().setVisibility(View.INVISIBLE);
                        new UiEdtrWrdSet(context).updateScrn(FragmentWordSet.ORDER_BY);
                    }
                });
                Toaster.show(context,"RESTORED!");
                return true;
        }
            else return false;
    }

    public void cleanAfterBckup(Context context) {
        fileExplorer.removeDir(getCompressPath(context));
        fileExplorer.removeDir(getZipPath(context));
    }


    public void cleanAfterRestore(Context context) {
        fileExplorer.removeDir(getDecompressPath(context));
        fileExplorer.removeDir(getDecompressPath(context));
        fileExplorer.removeDir(getDownloadPath(context));
        fileExplorer.removeDir(getUnzipPath(context));
    }
}
