package com.example.worddef_fragment.file.editor;

import android.content.Context;

import com.example.worddef_fragment.file.operator2.FileExplorer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class WordSetEditor {

    // EDITOR CLASS FOR WORDSET OBJECTS
    // ADD, DELETE, RENAME, ETC. SOME GET SET PROCESS THAT WORK WITH FILES DIRECTLY BY "FileOperator" CLASS

    private Context context;
    private FileExplorer fileExplorer;
    public WordSetEditor(Context context){
        this.context=context;
        fileExplorer=new FileExplorer();
    }

    // Adds new wordSet
    public boolean createNewWordSet(String wordSetName){
        if(fileExplorer.createSetFile(context,wordSetName)) {


            fileExplorer.write2SetFile(context,wordSetName,new JSONObject().toString());
            //fileExplorer.write2SetFile(context,wordSetName,tmp);
            return true;
        }
        else
            return  false;
    }

    // Sets order no of the "WordSet" object/file.
    public void setOrderNo(Context context, String setName, int orderNo) {
        JSONObject jObj=getAsJObj(context, setName);
        try {
            jObj.remove("orderNo");
            jObj.put("orderNo",orderNo);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        
        fileExplorer.write2SetFile(context, setName, jObj.toString());
    }

    // Renames the wordSet
    public void rename(String oldName, String newName){
        fileExplorer.renameSet(context,oldName,newName);
    }

    // Deletes the wordSet
    public void delete(String wordSetName){
        fileExplorer.deleteSet(context,wordSetName);
    }

    // Fetches the wordSet
    void get(String wordSetName){
        fileExplorer.readSetFile(context,wordSetName);
    }

    // Returns word-def count in the set/file
    public int getWordCount(String wordSetName) {
        JSONObject jObj;
        int wordCount;
        try {
         //   jObj = new JSONObject(fileExplorer.readSetFile(context, wordSetName)).getJSONObject("content");
            jObj=new JSONObject(fileExplorer.readSetFile(context, wordSetName));
            wordCount=jObj.length();
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
            wordCount=0;
        }
        catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            wordCount=0;
        }
        return wordCount;
    }

    // Returns set/file as a JSONObject.
    private JSONObject getAsJObj(Context context,String wordSetName) {
        JSONObject jObj=null;
        try{
            jObj=new JSONObject(fileExplorer.readSetFile(context,wordSetName));
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }
        return jObj;
    }

    // Returns all sets/files as JSONObject.
    private ArrayList<JSONObject> getAllAsJObj() {
        ArrayList<JSONObject> allJObjList = new ArrayList<>();
        File[] files = fileExplorer.getSetFiles(context);

        for (int i = 0; i < files.length; i++) {
                allJObjList.add(getAsJObj(context, files[i].getName()));
            }
        return allJObjList;
    }

    // Returns all set/file names in the "sets" directory.
    public ArrayList<String> getAllSetNames() {
        ArrayList<String> setNames=new ArrayList<>();
        File[] files=fileExplorer.getSetFiles(context);
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                setNames.add(files[i].getName());
            }
        }
        return  setNames;
    }

    public boolean checkDuplication(String wordSetName) {
        return fileExplorer.chckSetDuplication(context,wordSetName);
    }
}