package com.example.worddef_fragment.fragments.processes.explorer;

import android.content.Context;
import android.print.PrintAttributes;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.reaction.Reaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class WordDefExplorer implements FragmentExplorer, ImprovedWorddefExplorer {
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
            if(jObj.isNull(name)) {
                result=false;
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return !result;
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

    @Override
    public boolean checkDuplicationForAll(Context context, String dirOrFile, String name) {
        boolean result=true;
        ArrayList<String> wordsetList=new WordSetExplorer().getNames(new PathPickerFactory().create("wordset").get(context));
        String dir=new PathPickerFactory().create("wordset").get(context);
        System.out.println("asdklfjaşsldjf");
        for(String str: wordsetList) {

            for(String str2: getNames(dir + File.separator+ str)) {

                if(str2.equals(name)) {
                        new Reaction(context).showShort("This Word is Already Exists in: "+str);
                        result=false;
                    }
            }
        }
        return result;
    }
}
