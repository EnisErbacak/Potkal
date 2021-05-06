package com.example.worddef_fragment.fragments.fragment_worddef.process;

import android.content.Context;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.FragmentWordDef;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.Word;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.operator.WordOperator;
import com.example.worddef_fragment.fragments.fragment_worddef.manager.WorddefManager;
import com.example.worddef_fragment.fragments.fragment_wordset.manager.WordsetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class WordDefOperator {
    private String setName;
    private FileManager fileManager;
    private Context context;
    private JSONObject jObjMain;
    private String dirWordSet;

    public WordDefOperator(Context context) {
        this.setName=FragmentWordDef.setName;
        this.context=context;
        this.fileManager=new FileManager();
        this.dirWordSet=new PathPicker(context).get(PathPicker.WORDSET);
        initialize();
    }

    public void initialize() {
        try{
            jObjMain= new JSONObject(fileManager.operate().read(dirWordSet + File.separator + setName));
        }
        catch (JSONException jsonException){
            jObjMain= null;
        }
    }

    // RETURNS WORD-DEFINITION PAIR.

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

    public boolean add(String name, JSONObject jObj) {
        boolean result=false;
        String checkType=new SPEditor().getValue(context, SPEditor.DUPLICATION_CHCK);
        String dir2Srch=dirWordSet+File.separator+setName;
        WorddefManager manager=new WorddefManager();

        switch (checkType) {
            case SPEditor.DUPLICATION_CHCK_CURRENT:
                if(manager.explore(context).checkDuplication(dir2Srch, name)) {
                    try {
                        jObjMain.put(name, jObj);
                        update(setName, jObjMain);
                        result = true;
                    } catch (JSONException je) {
                        je.printStackTrace();
                        result = false;
                    }
                }

                break;
            case SPEditor.DUPLICATION_CHCK_ALL:
                if(manager.explore(context).checkDuplicationForAll(context, name)) {
                    try {
                        jObjMain.put(name, jObj);
                        update(setName, jObjMain);
                        result = true;
                    } catch (JSONException je) {
                        je.printStackTrace();
                        result = false;
                    }
                }
                break;
        }
        return result;
    }

    public void remove(String name) {
        jObjMain.remove(name);
        update(setName, jObjMain);
    }

    public void update(String name, JSONObject job) {
        new WordsetManager().operate(context).update(name,job);
    }

    public boolean change(String oldName,String newName, Word wordObj) {
        boolean result = false;
        try {
            if(newName.equals(oldName)) {
                jObjMain.remove(oldName);
                //new WordsetManager().operate(context).update(setName, jObjMain);
                jObjMain.put(newName,new WordOperator().convert2Json(wordObj));
                new WordsetManager().operate(context).update(setName, jObjMain);
                result=true;
            }
            else {
                if(new WorddefManager().explore(context).checkDuplicationForAll(context, newName)) {
                    jObjMain.remove(oldName);
                    jObjMain.put(newName,new WordOperator().convert2Json(wordObj));
                    new WordsetManager().operate(context).update(setName, jObjMain);
                    result=true;
                }
            }
        }catch (JSONException je) {
            je.printStackTrace();
        }
        return result;
    }
}
