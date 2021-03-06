package com.example.worddef_fragment.fragments.fragment_worddef.builder.data.operator;

import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.Word;

import org.json.JSONException;
import org.json.JSONObject;

public class WordOperator {

    public JSONObject convert2Json(Word word) {
        JSONObject jobj=new JSONObject();
        String def,exmp,lang,kind;
        def=word.getDef();
        exmp=word.getExmp();
        lang=word.getLang();
        kind=word.getKind();

        try {
            if(!def.equals("")) jobj.put("def", def);
            if(!exmp.equals("")) jobj.put("exmp", exmp);
            if(!lang.equals("")) jobj.put("lang", lang);
            if(!kind.equals("")) jobj.put("kind", kind);
            jobj.put("pts",10);

        }catch (JSONException je){je.printStackTrace();}
        return jobj;
    }

    public Word convert2Word(JSONObject job, String name) {
        String wordStr,def="",exmp="",lang="",kind="";
        Word word=new Word();

            try {
                wordStr=name;

                def = job.has("def") ? job.getString("def") : "";
                exmp = job.has("exmp") ? job.getString("exmp") : "";
                lang = job.has("lang") ? job.getString("lang") : "";
                kind = job.has("kind") ? job.getString("kind") : "";

                word.setWrd(wordStr);
                word.setDef(def);
                word.setExmp(exmp);
                word.setKind(kind);
                word.setLang(lang);
            }catch (JSONException je){je.printStackTrace();}

        return word;
    }
}
