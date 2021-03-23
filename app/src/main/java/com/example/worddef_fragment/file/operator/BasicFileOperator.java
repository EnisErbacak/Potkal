package com.example.worddef_fragment.file.operator;

import com.example.worddef_fragment.misc.editText.Toaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

// Class for basic file operations.
public class BasicFileOperator implements FileOperator {
    @Override
    public String read(String dir, String fileName) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;
        String fileContent="";

        try {
            fis = new FileInputStream(new File(dir + File.separator + fileName));
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
        return fileContent;
    }

    @Override
    public boolean write(String dir, String fileName, String content) {
        boolean result=false;

        FileWriter fileWriter=null;
        File file = new File(dir + File.separator + fileName);

        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            result=true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            result=false;
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean create(String dir, String fileName) {
        boolean result=false;
        try {
            new File(dir + File.separator + fileName).createNewFile();
            result= true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            result= false;
        }
        return result;
    }

    @Override
    public boolean delete(String path) {
        File file=new File(path);
        return file.delete();
    }

    @Override
    public boolean rename(String dir, String newName, String oldName) {
        File file = new File(dir+File.separator+oldName);
        File renamedFile = new File(dir + File.separator + newName);
        return file.renameTo(renamedFile);
    }

    @Override
    public boolean deleteDir(String dir) {
        File[] contents = new File(dir).listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f.getAbsolutePath());
            }
        }
        return new File(dir).delete();
    }
}
