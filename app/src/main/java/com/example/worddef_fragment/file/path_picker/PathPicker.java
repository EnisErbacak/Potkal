package com.example.worddef_fragment.file.path_picker;

import android.content.Context;

public class PathPicker {

    private Context context;
    public static final String WORDSET="wordset", ZIP="zip", UNZIP="unzip", DOWNLOAD="download";

    public PathPicker(Context context){
        this.context=context;
    }

    public String get(String type) {
        String result;
        switch (type) {
            case WORDSET:
                result=new PathWordset().get(context);
                break;
            case ZIP:
                result=new PathZip().get(context);
                break;
            case UNZIP:
                result=new PathUnzip().get(context);
                break;
            case DOWNLOAD:
                result=new PathDownload().get(context);
                break;
            default:
                return null;
        }
        return result;
    }
}
