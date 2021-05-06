package com.example.worddef_fragment.fragments.fragment_worddef.process;

import android.content.Context;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.fragments.fragment_wordset.process.WordSetExplorer;
import com.example.worddef_fragment.reaction.Reactor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class WordDefExplorer {
    private FileManager fileManager;
    public WordDefExplorer() {
        this.fileManager=new FileManager();
    }


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

    public boolean checkDuplication(String dirOrFile, String name) {
        boolean result=false;
        try {
            JSONObject jObj=new JSONObject(fileManager.operate().read(dirOrFile));
            if(jObj.isNull(name)) {
                result=true;
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return result;
    }

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

    public boolean checkDuplicationForAll(Context context, String name) {
        boolean result=true;
        PathPicker pathPicker=new PathPicker(context);
        ArrayList<String> wordsetList=new WordSetExplorer().getNames(pathPicker.get(PathPicker.WORDSET));
        String dir=pathPicker.get(PathPicker.WORDSET);
        for(String str: wordsetList) {

            for(String str2: getNames(dir + File.separator+ str)) {
                if(str2.equals(name)) {
                        new Reactor(context).showShort(context.getResources().getString(R.string.word_exists_in)+ "\""+str+"\"");
                        result=false;
                    }
            }
        }
        return result;
    }
}
