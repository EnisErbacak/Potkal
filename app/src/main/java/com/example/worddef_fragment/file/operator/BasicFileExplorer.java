package com.example.worddef_fragment.file.operator;

import com.example.worddef_fragment.file.operator.FileExplorer;

import java.io.File;
import java.util.ArrayList;

public class BasicFileExplorer implements FileExplorer {
    @Override
    public ArrayList<String> getFileNames(String dir) {
        ArrayList<String> listFileName=new ArrayList<>();
        File[] files=new File(dir).listFiles();
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                listFileName.add(files[i].getName());
            }
        }
        return  listFileName;
    }

    @Override
    public int getFileCount(String dir) {
        return new File(dir).listFiles().length;
    }

    @Override
    public boolean checkDuplication(String dir, String fileName) {
        boolean result=false;
        File directory = new File(dir);
        File[] files = directory.listFiles();
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().equals(fileName)) {
                    result= true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean checkDir(String dir) {
        boolean result=true;
        File file=new File(dir);
        if(!file.exists() && !file.isDirectory())
            return false;

        return result;
    }

    @Override
    public boolean checkFile(String dir, String fileName) {
        return new File(dir + File.separator+ fileName).exists();
    }
}
