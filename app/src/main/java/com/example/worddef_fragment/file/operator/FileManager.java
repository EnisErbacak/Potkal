package com.example.worddef_fragment.file.operator;

public class FileManager {
    private BasicFileOperator basicFileOperator;
    private BasicFileExplorer basicFileExplorer;

    public FileManager() {
        this.basicFileOperator = new BasicFileOperator();
        this.basicFileExplorer = new BasicFileExplorer();
    }

    public BasicFileOperator operate() {
        return basicFileOperator;
    }

    public BasicFileExplorer explore() {
        return basicFileExplorer;
    }
}
