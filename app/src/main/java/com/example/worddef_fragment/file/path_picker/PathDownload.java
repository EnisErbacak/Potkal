package com.example.worddef_fragment.file.path_picker;

import android.content.Context;

import java.io.File;

public class PathDownload implements PathPicker{
    private final String FOLDERNAME="download";

    @Override
    public String get(Context context) {
        return context.getFilesDir().getPath() + File.separator + FOLDERNAME;
    }
}
