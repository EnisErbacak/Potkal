package com.example.worddef_fragment.file.path_picker;

import android.content.Context;

import java.io.File;

public class PathUnzip implements PathPicker{
    private final String FOLDERNAME="Unzip Files";

    @Override
    public String get(Context context) {
        return context.getFilesDir().getPath() + File.separator + FOLDERNAME;
    }
}
