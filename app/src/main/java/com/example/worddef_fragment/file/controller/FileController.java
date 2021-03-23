package com.example.worddef_fragment.file.controller;

import java.io.File;

public class FileController {
    public void controlDir(String dir) {
        File file=new File(dir);

        if( !file.isDirectory())
            file.mkdir();
    }
}
