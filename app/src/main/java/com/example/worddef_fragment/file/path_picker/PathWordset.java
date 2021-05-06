package com.example.worddef_fragment.file.path_picker;

import android.content.Context;

import java.io.File;

public class PathWordset implements CustomPath {
    private final String FOLDERNAME="wordset_files";

    @Override
    public String get(Context context) {
        return context.getFilesDir().getPath() + File.separator + FOLDERNAME;
    }
}
