package com.example.worddef_fragment.file.operator;

import java.util.ArrayList;
/*
    Class for only for gathering information of files.
 */
public interface FileExplorer {
    ArrayList<String> getFileNames(String dir);
    int getFileCount(String dir);
    boolean checkDuplication(String dir, String fileName);
    boolean checkDir(String dir);
    boolean checkFile(String dir, String fileName);
}
