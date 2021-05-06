package com.example.worddef_fragment.fragments.fragment_wordset.process;

import android.content.Context;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class WordSetOperator {

    private FileManager fileManager;
    private Context context;
    private String dirWordSet;

    public WordSetOperator(Context context) {
        this.context = context;
        initialize();
    }

    public void initialize() {
        this.fileManager=new FileManager();
        dirWordSet=new PathPicker(context).get(PathPicker.WORDSET);
    }

    public JSONObject get(String name) {
        JSONObject jsonObject=null;
        try {
            jsonObject= new JSONObject(new FileManager().operate().read(dirWordSet + File.separator + name));
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return jsonObject;
    }

    public boolean add(String name, JSONObject jObj) {
        boolean result=false;
        if(! fileManager.explore().checkDuplication(dirWordSet,name)) {
                fileManager.operate().write(dirWordSet + File.separator + name, jObj.toString());
                result = true;
        }
        return result;
    }

    public void remove(String name) {
        fileManager.operate().delete(dirWordSet + File.separator+name);
    }

    public boolean rename(String nameOld, String nameNew) {
        boolean result=false;
        if(! fileManager.explore().checkDuplication(dirWordSet, nameNew)) {
            result =fileManager.operate().rename(dirWordSet, nameNew, nameOld);
        }
        return result;
    }

    public void update(String name, JSONObject jsonObject) {
        fileManager.operate().write(dirWordSet + File.separator+name, jsonObject.toString());
    }
}
