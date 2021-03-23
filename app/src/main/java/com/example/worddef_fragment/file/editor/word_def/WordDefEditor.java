package com.example.worddef_fragment.file.editor.word_def;

import android.content.Context;
import android.widget.Toast;

import com.example.worddef_fragment.file.operator2.FileExplorer;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.data.Word;

import org.json.JSONException;
import org.json.JSONObject;

public class WordDefEditor
{
    // JObject object that belongs to that class as long as it lives.
    private String wordSetName;
    private long id;
    private int orderNo;
    private  JSONObject jObj=null;
    private FileExplorer fileExplorer=null;
    private Context context;

    public WordDefEditor(Context context , String wordSetName) {
        this.context=context;
        this.wordSetName=wordSetName;
        fileExplorer=new FileExplorer();
        jObj=initializeJObj(context,wordSetName,fileExplorer);
        //jObj=getContent(context, wordSetName, fileExplorer);
    }

    public void setWordSetName(String wordSetName) {
        this.wordSetName=wordSetName;
        jObj=initializeJObj(context,wordSetName,fileExplorer);}
    public String getWordSetName() {return wordSetName;}


// Reads the file, and creates the JSONObject.
    private JSONObject initializeJObj(Context context,String wordSetName,FileExplorer fileExplorer){
        try{
            return new JSONObject(fileExplorer.readSetFile(context,wordSetName));
        }
        catch (JSONException jsonException){
            return null;
        }
    }

    public JSONObject getJSONObj() {
        try{
            return new JSONObject(new FileExplorer().readSetFile(context,wordSetName));
        }
        catch (JSONException jsonException){
            return null;
        }
    }
// Gets word-definition part as JSONObject from the main JSONObject.
    private JSONObject getContent(Context context,String wordSetName,FileExplorer fileExplorer) {
        JSONObject jObj=initializeJObj(context, wordSetName, fileExplorer);
        getInfo(jObj);
        JSONObject jCntnt=null;
        try {
            jCntnt = jObj.getJSONObject("content");
        }catch (JSONException je) {
            je.printStackTrace();
        }
        return jCntnt;
    }

    // Gets "id, orderNo" from the main JSONObject, instead word-definition part.
    private void getInfo(JSONObject jObj) {
        try {
            this.id = jObj.getLong("id");
            this.orderNo = jObj.getInt("orderNo");
        }catch (JSONException jsonException) { jsonException.printStackTrace();}
    }

    // Merges "id,orderNo" and word-definitinon parts into the final JSONObject for writing into the file.
    private JSONObject mergeData(JSONObject jObj) {
        JSONObject jMrgd=new JSONObject();
        try {
            jMrgd.put("id", id);
            jMrgd.put("orderNo", orderNo);
            jMrgd.put("content",jObj);
        }catch (JSONException je) { je.printStackTrace(); return null;}
        return jMrgd;
    }

    // Fetches the word and def as a JSON Object from the jObj
    private JSONObject getWord(String wordName){
        JSONObject wordDef;
        try {
            wordDef = jObj.getJSONObject(wordName);
        }
        catch (JSONException jsonException){
            wordDef=null;
        }
        return wordDef;
    }

// Returns definition.
    public String getDef(String word) {
        String def=null;
        try {
            def=jObj.getString(word);
        }catch (JSONException jsonException) {jsonException.printStackTrace();}
        return def;
    }

    public Word getWord2(String strWord) {
        Word word=new Word();
        try {
            JSONObject jtmp=jObj.getJSONObject(strWord);
            word.setWrd(strWord);
            word.setDef(safeGet("def",jtmp));
            word.setExmp(safeGet("exmp",jtmp));
            word.setKind(safeGet("kind", jtmp));
            word.setLang(safeGet("lang",jtmp));
        }catch (JSONException jsonException) {jsonException.printStackTrace();}
        return word;
    }

    private String safeGet(String str, JSONObject jObj) {
        String res="";
        try {
            if(jObj.has(str))
                res=jObj.getString(str);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return res;
    }

// Adds word-definition if there is no word duplication.
    public boolean safeAdd(String word, String def){
        boolean result=false;
        JSONObject jWrd=new JSONObject();
        try {
            if(!checkDuplication(word)) {
                jWrd.put("def",def);
                jWrd.put("pts",10);
                jObj.put(word,jWrd);

                //jObj.put(word, def);
                shwMessage("ADDED");
                result=true;
            }
        }catch (JSONException jsonException) {
            jsonException.getMessage();
            result=false;
        }
        return result;
    }

    public boolean safeAdd2(Word word) {
        boolean result=false;
        JSONObject jWrd=new JSONObject();

        String wordStr=word.getWrd();
        String lang=word.getLang();


        String def= word.getDef();
        String exmp=word.getExmp();
        String kind=word.getKind();

        try {
            if (!checkDuplication(wordStr)) {
                jWrd.put("def",def);
                jWrd.put("pts",10);

                if(! exmp.equals(""))
                    jWrd.put("exmp", exmp);
                if(! kind.equals(""))
                    jWrd.put("kind", kind);
                if(! lang.equals(""))
                    jWrd.put("lang",lang);

                jObj.put(wordStr,jWrd);
                shwMessage("ADDED");
                result=true;
            }
        }catch (JSONException jsonException) {
                jsonException.getMessage();
                result=false;
            }
            return result;
    }

    public void delete(String wordName) {
        if(isWrdExst(wordName)) {
            jObj.remove(wordName);
            shwMessage("DELETED "+"\""+wordSetName+"\"");
            updateWordSetFile();

        } else
            shwMessage("WORD NOT EXIST!");
    }

// If word exists, return true else returns false.
    private boolean isWrdExst(String wordName) {
        return !jObj.isNull(wordName);
    }

// Checks if the new word exists or not.
    public boolean checkDuplication(String wordName) {
        if(isWrdExst(wordName)) {
            shwMessage("THIS ALREADY EXISTS!");
            return true;
        }
        else {
            return false;
        }
    }

// Changes the word or the definition of it.
    public boolean change(String oldWord,String newWord, String newDef){
            jObj.remove(oldWord);
            try {
                jObj.put(newWord, newDef);
                updateWordSetFile();
                shwMessage("CHANGED!");
                return true;
            }catch (JSONException jsonException){
                jsonException.printStackTrace();
                return false;
            }
    }

    public boolean change2(String oldWord,String newWord, String newDef){
        jObj.remove(oldWord);
        JSONObject jNew=new JSONObject();
        try {
            jNew.put("def",newDef);
            jObj.put(newWord, jNew);
            updateWordSetFile();
            shwMessage("CHANGED!");
            return true;
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
            return false;
        }
    }


    public boolean safeChange(String oldWord,String newWord, String newDef) {
        if(!checkDuplication(newWord))
        {
            jObj.remove(oldWord);
            try {
                jObj.put(newWord, newDef);
                updateWordSetFile();
                shwMessage("CHANGED!");
                return true;
            }catch (JSONException jsonException){
                jsonException.printStackTrace();
                return false;
            }
        }
        else
        {
            System.out.println("THIS WORD IS ALREADY EXISTS");
            return false;
        }
    }

    public  void updateWordSetFile() {
        fileExplorer.write2SetFile(context,wordSetName,jObj.toString());
    }

    private void shwMessage(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
