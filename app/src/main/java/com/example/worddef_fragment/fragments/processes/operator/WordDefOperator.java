package com.example.worddef_fragment.fragments.processes.operator;

import android.content.Context;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.FragmentWordDef;
import com.example.worddef_fragment.fragments.processes.explorer.FragmentExplorerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class WordDefOperator implements FragmentOperator {
    private String setName;
    private FileManager fileManager;
    private Context context;
    private JSONObject jObjMain;
    private String dirWordSet;

    public WordDefOperator(Context context) {
        this.setName=FragmentWordDef.setName;
        this.context=context;
        this.fileManager=new FileManager();
        this.dirWordSet=new PathPickerFactory().create("wordset").get(context);
        initialize();
    }

    @Override
    public void initialize() {
        try{
            jObjMain= new JSONObject(fileManager.operate().read(dirWordSet + File.separator + setName));
        }
        catch (JSONException jsonException){
            jObjMain= null;
        }
    }

    // RETURNS WORD-DEFINITION PAIR.
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
        String checkType=new SPEditor().getValue(context, SPEditor.DUPLICATION_CHCK);
        String dir2Srch=dirWordSet+File.separator+setName;

        switch (checkType) {
            case SPEditor.DUPLICATION_CHCK_CURRENT:
                if(new FragmentExplorerFactory().create("worddef").checkDuplication(dir2Srch, name)) {
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
                if(new FragmentExplorerFactory().createImproved("worddef").checkDuplicationForAll(context,dir2Srch, name)) {
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

    @Override
    public void remove(String name) {
        jObjMain.remove(name);
        update(setName, jObjMain);
    }

    @Override
    public boolean rename(String nameOld, String nameNew) {
        boolean result=false;
        String dir2Srch=dirWordSet+File.separator+setName;
            try {
                if(new FragmentExplorerFactory().create("worddef").checkDuplication(dir2Srch, nameNew)) {
                    JSONObject newJson=get(nameOld);
                    jObjMain.remove(nameOld);
                    jObjMain.put(nameNew, newJson);
                    System.out.println("**********CHANGED");
                    update(setName, jObjMain);
                } else {
                    // THIS WORD IS ALREADY EXISTS.
                    System.out.println("**********THIS WORD IS ALREADY EXISTS");
                }

                result=true;
            }catch (JSONException je) {je.printStackTrace();}

        return result;
    }

    @Override
    public void update(String name, JSONObject job) {
        new FragmentOperatorFactory().create("wordset", context).update(name,job);
        //fileExplorer.write2SetFile(context,setName,jObjMain.toString());
    }
}
