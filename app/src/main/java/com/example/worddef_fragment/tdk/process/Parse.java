package com.example.worddef_fragment.tdk.process;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parse {
    private  ArrayList<TdkWord> tdkWordList;
    final String[]  KINDS={"isim","sıfat","zarf","zamir", "fiil","edat","ünlem"};
    public Parse() {
        tdkWordList=new ArrayList<>();
    }

    public ArrayList<TdkWord> parseResult(JSONArray jArray) {
        if(jArray!=null) {
            try {
                for (int i = 0; i < jArray.length(); i++) { // Iterate through mainJSONArray
                    String madde = ((JSONObject) jArray.get(i)).getString("madde");
                    TdkWord tdkWord = new TdkWord(madde);
                    tdkWordList.add(tdkWord);

                    setLang(tdkWord, ((JSONObject) jArray.get(i)));
                    setProperties(tdkWord, jArray.getJSONObject(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        /*
        for(TdkWord tdkWord: tdkWordList)
            tdkWord.display();

         */
        return tdkWordList;
    }

    private void setLang(TdkWord tdkWord, JSONObject jObj) {
        try {
            tdkWord.setLang(jObj.getString("lisan"));
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    private void setProperties(TdkWord tdkWord, JSONObject jObj) {
        String def="";
        try {
            JSONArray anlamlarListe=jObj.getJSONArray("anlamlarListe");
            for(int i=0;i<anlamlarListe.length();i++) {
                def= ((JSONObject) anlamlarListe.get(i)).getString("anlam");

                TdkProperty wp=new TdkProperty();
                wp.setDef(def);
                tdkWord.getPropertyList().add(wp);

                setExample(wp, (JSONObject)anlamlarListe.get(i));// If exmp exists, add it.
                setKind(wp, (JSONObject) anlamlarListe.get(i),i);
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    private void setExample(TdkProperty wp, JSONObject jObj) {
        String example="";
        String auth = "";
            try {
                if(! jObj.isNull("orneklerListe")) {
                    JSONArray orneklerListe = jObj.getJSONArray("orneklerListe");
                    String exmp = ((JSONObject) orneklerListe.get(0)).getString("ornek");
                    JSONArray authArr = ((JSONObject) orneklerListe.get(0)).optJSONArray("yazar");
                    if (authArr != null) auth = ((JSONObject) authArr.opt(0)).optString("kisa_adi");

                    example = authArr == null ? exmp : exmp + " - " + auth;
                    wp.setExmp(example);
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
    }

    ArrayList<String> firstKindList =new ArrayList<>();
    private void setKind(TdkProperty wp, JSONObject jObj, int no) {
        ArrayList<String> kindList=new ArrayList<>();
        String kind="";
        if(no==0) firstKindList.clear();

        try {
            if (!jObj.isNull("ozelliklerListe")) {
                JSONArray ftrsArr = jObj.getJSONArray("ozelliklerListe");
                for (int i = 0; i < ftrsArr.length(); i++) {
                    String tmp=new String(((JSONObject) ftrsArr.get(i)).getString("tam_adi"));
                    if (no == 0) {
                        firstKindList.add(tmp);
                        kindList.add(tmp);
                    } else {
                        if(ftrsArr.length()>1) {
                            if(i==0) {
                                if(getTur(tmp)) kindList.add(tmp);
                                else kindList.add(getFromFirst(true));
                            } else {
                                kindList.add(tmp);
                            }
                        }else {
                            if( getTur(tmp)) {
                                kindList.add(tmp);
                                kindList.add(getFromFirst(false));
                            }else {
                                kindList.add(getFromFirst(true));
                                kindList.add(tmp);
                            }
                        }
                    }
                }
            } else {
                kindList=(ArrayList) firstKindList.clone();
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        for(int i=0;i<kindList.size();i++) {kind = kind.equals("") ? kindList.get(i): kind+", "+ kindList.get(i);}
        wp.setKind(kind);
    }

    private boolean getTur(String tur) {
        boolean result=false;
        for(String str:KINDS) {
            if(str.equals(tur))
                result=true;
        }
        return result;
    }

    private String getFromFirst(boolean isTur){
        String result="";
        for(int i=0;i<firstKindList.size();i++) {
            if(isTur && getTur(firstKindList.get(i))) {
                result=firstKindList.get(i);
            } else {
                if(! getTur(firstKindList.get(i)))
                    result=firstKindList.get(i);
            }
        }
        return result;
    }
}
