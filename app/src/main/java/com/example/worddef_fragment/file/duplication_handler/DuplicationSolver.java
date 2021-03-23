package com.example.worddef_fragment.file.duplication_handler;

import java.io.File;

public class DuplicationSolver implements DuplicationHandler{
    @Override
    public String perform(String fileName, String dir) {
        int fileNo=0;
        File[] files=new File(dir).listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equals(fileName)) {
                fileNo++;
            }
        }
        String newName= fileNo>0 ? fileName+ "(" + fileNo+ ")" : fileName;
        return newName;
    }
}
