package com.example.worddef_fragment.fragments.processes.explorer;

import android.content.Context;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;

import java.util.ArrayList;

public class WordSetExplorer implements FragmentExplorer {
    private FileManager fileManager;
    private Context context;
    private String dirWordSet;

    public WordSetExplorer() {
        fileManager=new FileManager();
    }

    @Override
    public int getCount(String path) {
        return fileManager.explore().getFileCount(path);
    }

    @Override
    public boolean checkDuplication(String dir, String name) {
        return !fileManager.explore().checkDuplication(dir, name);
    }

    @Override
    public ArrayList<String> getNames(String dir) {
        return fileManager.explore().getFileNames(dir);
    }
}
