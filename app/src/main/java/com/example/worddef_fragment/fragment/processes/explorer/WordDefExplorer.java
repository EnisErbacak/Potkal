package com.example.worddef_fragment.fragment.processes.explorer;

import com.example.worddef_fragment.file.operator.FileManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class WordDefExplorer implements FragmentExplorer {
    private FileManager fileManager;
    public WordDefExplorer() {
        this.fileManager=new FileManager();
    }

    @Override
    public int getCount(String path) {
        int result=-1;
        try {
            JSONObject jObj=new JSONObject(fileManager.operate().read(path));
            result=jObj.length();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            result=-1;
        }
        return result;
    }

    @Override
    public boolean checkDuplication(String dirOrFile, String name) {
        boolean result=true;
        try {
            JSONObject jObj=new JSONObject(fileManager.operate().read(dirOrFile));
            if(jObj.isNull(name))   result=false;
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<String> getNames(String dirOrFile) {
        ArrayList<String> names=new ArrayList<String>();
        try {
            JSONObject jObj=new JSONObject(fileManager.operate().read(dirOrFile));
            Iterator<String> iterator=jObj.keys();
            while (iterator.hasNext()) {
                names.add(iterator.next());
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return names;
    }
}
