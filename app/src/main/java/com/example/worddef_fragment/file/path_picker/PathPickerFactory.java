package com.example.worddef_fragment.file.path_picker;

public class PathPickerFactory {
    public PathPicker create(String type) {
        PathPicker pathPicker;
        switch (type.toLowerCase()) {
            case "wordset":
                pathPicker= new PathWordSet();
                break;
            case "zip":
                pathPicker= new PathZip();
                break;
            case "unzip":
                pathPicker= new PathUnzip();
                break;
            case "download":
                pathPicker= new PathDownload();
                break;
            default:
                return null;
        }
        return pathPicker;
    }
}
