package com.example.worddef_fragment.file.editor;

import android.content.Context;

import com.example.worddef_fragment.file.operator2.FileExplorer;
import com.example.worddef_fragment.fragments.fragmentWordDef.FragmentWordDef;

import org.json.JSONException;
import org.json.JSONObject;

public class EditorFragmentWordDef implements FragmentEditor {
    private String setName;
    private FileExplorer fileExplorer;
    private Context context;
    private JSONObject jObjMain;

    public EditorFragmentWordDef(Context context) {
        this.setName=FragmentWordDef.setName;
        this.context=context;
        initialize();
    }

    @Override
    public void initialize() {
        this.fileExplorer=new FileExplorer();
        try{
            jObjMain= new JSONObject(fileExplorer.readSetFile(context,setName));
        }
        catch (JSONException jsonException){
            jObjMain= null;
        }
    }

    @Override
    public JSONObject get(String name) {
        JSONObject job;
        try{
           job=jObjMain.getJSONObject(name);
        }
        catch (JSONException jsonException){
            jsonException.printStackTrace();
            job= null;
        }
        return job;
    }

    @Override
    public boolean add(String name, JSONObject jObj) {
        boolean result=false;

        if( checkDuplication(name)) {
            try {
                jObjMain.put(name, jObj);
                update();
                result = true;
            } catch (JSONException je) {
                je.printStackTrace();
                result = false;
            }
        }
        else{
            // This word is already exists.
            result=false;
        }

        return result;
    }

    @Override
    public void remove(String name) {
        jObjMain.remove(name);
    }

    @Override
    public boolean change(String nameOld, String nameNew, JSONObject jObj) {
        boolean result=false;

            try {
                jObjMain.remove(nameOld);
                if(checkDuplication(nameNew)) {
                    jObjMain.put(nameNew, jObj);
                    update();
                } else {
                    // THIS WORD IS ALREADY EXISTS.
                }

                result=true;
            }catch (JSONException je) {je.printStackTrace();}

        return result;
    }

    @Override
    public boolean checkDuplication(String name) {
        return jObjMain.isNull(name);
    }

    @Override
    public void update() {
        fileExplorer.write2SetFile(context,setName,jObjMain.toString());
    }
}
