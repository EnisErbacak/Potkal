package com.example.worddef_fragment.file.editor;

import android.content.Context;

import com.example.worddef_fragment.file.operator2.FileExplorer;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentEditorWordSet implements FragmentEditor{

    private FileExplorer fileExplorer;
    private Context context;

    public FragmentEditorWordSet(Context context) {
        this.context = context;
    }

    @Override
    public void initialize() {
        this.fileExplorer=new FileExplorer();
    }

    @Override
    public JSONObject get(String name) {
        JSONObject jObj;
        try{
            jObj=new JSONObject(fileExplorer.readSetFile(context,name));
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
            jObj=null;
        }
        return jObj;
    }

    @Override
    public boolean add(String name, JSONObject jObj) {
        return false;
    }

    @Override
    public void remove(String name) {

    }

    @Override
    public boolean change(String nameOld, String nameNew, JSONObject jObj) {
        return false;
    }

    @Override
    public boolean checkDuplication(String name) {
        return false;
    }

    @Override
    public void update() {

    }
}
