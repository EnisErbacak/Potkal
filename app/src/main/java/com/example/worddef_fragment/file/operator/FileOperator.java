package com.example.worddef_fragment.file.operator;

public interface FileOperator {
    String read(String dir, String fileName);
    boolean write(String dir, String fileName, String content);
    boolean create(String dir, String fileName);
    boolean delete(String path);
    boolean deleteDir(String dir);
    boolean rename(String dir, String newName, String oldName);
}
