package com.example.worddef_fragment.file.operator2;

import android.content.Context;
import android.widget.Toast;

import com.example.worddef_fragment.misc.editText.Toaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
******* CLASS WHICH HANDLES FILE PROCESS SUCH AS: GET, CREATE, READ, DELETE, RENAME, ETC..
*/
public class FileExplorer {

    // Returns: "/data/user/0/com.example.worddef_fragment/files"
    private String getRootPath(Context context) { return context.getFilesDir().getPath();}

    // Returns: "/data/user/0/com.example.worddef_fragment/files/sets"
    private String getSetPath(Context context) { return context.getFilesDir().getPath() + File.separator + "wordset_files"; }

    // Checks file exists or not
    private boolean checkSetFile(Context context, String fileName){
        if(getSetFile(context,fileName).exists())
            return true;
        else {
            shwMessage(context,"FILE NOT EXIST!");
            return false;
        }
    }

    // CHECKS DUPLICATION, IF NOT CREATES FILE
    public boolean createSetFile(Context context, String fileName) {
        checkDir(getSetPath(context));
        FileWriter writer=null;
        boolean result;
        if(!chckSetDuplication(context,fileName)) {
            try {
                result= getSetFile(context,fileName).createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                result=false;
            }
            return true;
        }
        else
            return false;
    }

    // Returns file as given name in "WordSet Files" directory
    public File getSetFile(Context context, String wordSetName) {
        return new File(getSetPath(context)+ File.separator + wordSetName);
    }

    // Returns all files in wordSet directory.
    public File[] getSetFiles(Context context){
        File directory = new File(getSetPath(context));
        return directory.listFiles();
    }

    public ArrayList<String> getFileNames(Context context, String dir) {
        ArrayList<String> listFileName=new ArrayList<>();
        File[] files=new File(dir).listFiles();
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                listFileName.add(files[i].getName());
            }
        }
        return  listFileName;
    }

    // Returns file content.
    public String readSetFile(Context context, String wordSetName) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;
        String fileContent="";

        if(checkSetFile(context,wordSetName)) {
            try {
                fis = new FileInputStream(getSetFile(context, wordSetName));
                isr = new InputStreamReader(fis);
                bufferedReader = new BufferedReader(isr);

                fileContent = bufferedReader.readLine();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    isr.close();
                    bufferedReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return fileContent;
    }

    // Checks file exists or not, then write into it.
    public void write2SetFile(Context context, String setName, String content) {
            checkDir(getSetPath(context));// Check directory
            FileWriter fileWriter=null;
            File file = getSetFile(context,setName);

            if(checkSetFile(context,setName)) { // Checks file exists or not
                try {
                    fileWriter = new FileWriter(file);
                    fileWriter.write(content.toString());
                    Toaster.show(context,"WRITTEN INTO FILE!");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    try {
                        fileWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            else
                shwMessage(context,"FILE NOT EXIST!");
    }

    // First checks file if it exists or not, then delete it.
    public void deleteSet(Context context, String wordSetName) {
        File file= getSetFile(context,wordSetName);
        if(checkSetFile(context,wordSetName)) {
            file.delete();
            shwMessage(context, "DELETED!");
        }
    }

    // Checks duplication then renames if there is no duplication.
    public void renameSet(Context context, String oldName, String newName) {
        if(!chckSetDuplication(context,newName)) {
            File file = getSetFile(context, oldName);
            File newFile = new File(getSetPath(context) + File.separator + newName);
            file.renameTo(newFile);
            shwMessage(context,"RENAMED!");
        }
    }

    public void renameFile(Context context,String dirSrc, String oldName, String newName) {
            File file = new File(dirSrc+File.separator+oldName);
            File renamedFile = new File(dirSrc + File.separator + newName);
            file.renameTo(renamedFile);
    }

    // Checks files in "WordSet Files" directory for duplication
    public boolean chckSetDuplication(Context context, String wordSetName) {
        File directory = new File(getSetPath(context));
        File[] files = directory.listFiles();
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().equals(wordSetName)) {
                    shwMessage(context,"THIS FILE IS ALREADY EXISTS!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean chcFileDuplication(Context context, String fileName, File[] files) {
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }
    // If the direction that is used not exist , it creates.
    private void checkDir(String path) {
        File dir=new File(path);
        if(!dir.exists() && !dir.isDirectory())
            makeDir(path);
    }

    public void removeDir(String path) {
        File dir = new File(path);
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }
    }

    private void makeDir(String path) { File dir=new File(path); dir.mkdir(); }
    private void shwMessage(Context context,String message) {Toast.makeText(context,message,Toast.LENGTH_SHORT).show(); }
}

