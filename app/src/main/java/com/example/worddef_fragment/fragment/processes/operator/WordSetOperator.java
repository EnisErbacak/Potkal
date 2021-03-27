package com.example.worddef_fragment.fragment.processes.operator;

import android.content.Context;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class WordSetOperator implements FragmentOperator {

    private FileManager fileManager;
    private Context context;
    private String dirWordSet;

    public WordSetOperator(Context context) {
        this.context = context;
        initialize();
    }

    @Override
    public void initialize() {
        this.fileManager=new FileManager();
        dirWordSet=new PathPickerFactory().create("wordset").get(context);
    }

    @Override
    public JSONObject get(String name) {
        JSONObject jsonObject=null;
        try {
            jsonObject= new JSONObject(new FileManager().operate().read(dirWordSet + File.separator + name));
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public boolean add(String name, JSONObject jObj) {
        boolean result=false;
        if(! fileManager.explore().checkDuplication(dirWordSet,name)) {
                fileManager.operate().write(dirWordSet + File.separator + name, jObj.toString());
                result = true;
        }
        return result;
    }

    @Override
    public void remove(String name) {
        fileManager.operate().delete(dirWordSet + File.separator+name);
    }

    @Override
    public boolean rename(String nameOld, String nameNew) {
        boolean result=false;
        if(! fileManager.explore().checkDuplication(dirWordSet, nameNew)) {
            result =fileManager.operate().rename(dirWordSet, nameNew, nameOld);
        }
        return result;
    }

    @Override
    public void update(String name, JSONObject jsonObject) {
        fileManager.operate().write(dirWordSet + File.separator+name, jsonObject.toString());
    }
}
