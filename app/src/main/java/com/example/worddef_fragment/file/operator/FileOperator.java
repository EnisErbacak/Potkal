package com.example.worddef_fragment.file.operator;

public interface FileOperator {
    String read(String path);
    boolean write(String path, String content);
    boolean create(String path);
    boolean delete(String path);
    boolean deleteDir(String dir);
    boolean rename(String dir, String newName, String oldName);
}
