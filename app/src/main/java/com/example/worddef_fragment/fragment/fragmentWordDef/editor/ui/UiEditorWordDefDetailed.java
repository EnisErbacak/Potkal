package com.example.worddef_fragment.fragment.fragmentWordDef.editor.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragment.fragmentWordDef.FragmentWordDef;
import com.example.worddef_fragment.fragment.fragmentWordDef.editor.data.Word;
import com.example.worddef_fragment.fragment.fragmentWordDef.editor.data.operator.WordOperator;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel.LlLower;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel.LlMain;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel.LlUpper;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel.LlUpperSubLeft;
import com.example.worddef_fragment.fragment.processes.operator.FragmentOperatorFactory;
import com.example.worddef_fragment.other.ScannerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class UiEditorWordDefDetailed implements UiEditorWordDef {

    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A

    private Context context;
    private String setName;

    public UiEditorWordDefDetailed(Context context, String setName) {
        this.context = context;
        this.setName = setName;
    }

    @Override
    public void updateScreen() {
        buildScreen(FragmentWordDef.ORDER_BY);
    }

    public void buildScreen(int order) {
        switch (order) {
            case 0: // Default first build
                buildByCreateDateDsc();
                break;
            case 1:
                buildByCreateDateAsc();
                break;
            case 2:
                //buildByAlphAsc();
                break;
            case 3:
                //buildByAlphDsc();
                break;
        }
    }


    @Override
    public void buildByCreateDateAsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("worddef",context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);

        Word word;
        WordOperator wordOperator=new WordOperator();

        ArrayList<String> keys= getKeyList(jObj.keys(), false);

        for(int i=0;i<keys.size();i++) {
            try {
                word=wordOperator.convert2Word(jObj.getJSONObject(keys.get(i)), keys.get(i));
            }catch (JSONException je) {
                je.printStackTrace();
                break;
            }
            ll.addView(new LlMain(context,
                    new LlUpper(context
                            ,new LlUpperSubLeft(context, word.getWrd(), word.getKind())
                            ,word.getLang())
                    ,new LlLower(context, word.getDef(), word.getExmp())));
        }
    }

    @Override
    public void buildByCreateDateDsc() {
        //JSONObject jObj=new FragmentOperatorFactory().create("worddef",context).get(setName);
        JSONObject jObj=new FragmentOperatorFactory().create("wordset",context).get(setName);
        JSONObject jSub;
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);

        Word word;
        WordOperator wordOperator=new WordOperator();

        ArrayList<String> keys= getKeyList(jObj.keys(), true);

        for(int i=0;i<keys.size();i++) {
            try {
                word=wordOperator.convert2Word(jObj.getJSONObject(keys.get(i)), keys.get(i));
            }catch (JSONException je) {
                je.printStackTrace();
                break;
            }

            ll.addView(new LlMain(context,
                    new LlUpper(context
                            ,new LlUpperSubLeft(context, word.getWrd(), word.getKind())
                            ,word.getLang())
                    ,new LlLower(context, word.getDef(), word.getExmp())));
        }
    }

    @Override
    public void buildByAlphabeticalAsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("worddef",context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        WordOperator wordOperator=new WordOperator();
        Word word;

        ArrayList<String> keys=sortAlphabetical(getKeyList(jObj.keys(), false),false);

        for(int i=0;i<keys.size();i++) {
            try {
                word=wordOperator.convert2Word(jObj.getJSONObject(keys.get(i)), keys.get(i));
            }catch (JSONException je) {
                je.printStackTrace();
                break;
            }
            ll.addView(new LlMain(context,
                    new LlUpper(context
                            ,new LlUpperSubLeft(context, word.getWrd(), word.getKind())
                            ,word.getLang())
                    ,new LlLower(context, word.getDef(), word.getExmp())));
        }
    }

    @Override
    public void buildByAlphabeticalDsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("worddef",context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        WordOperator wordOperator=new WordOperator();
        Word word;

        ArrayList<String> keys=sortAlphabetical(getKeyList(jObj.keys(), false),true);

        for(int i=0;i<keys.size();i++) {
            try {
                word=wordOperator.convert2Word(jObj.getJSONObject(keys.get(i)), keys.get(i));
            }catch (JSONException je) {
                je.printStackTrace();
                break;
            }
            ll.addView(new LlMain(context,
                    new LlUpper(context
                            ,new LlUpperSubLeft(context, word.getWrd(), word.getKind())
                            , word.getLang())
                    ,new LlLower(context, word.getDef(), word.getExmp())));
        }
    }

    @Override
    public ArrayList<String> getKeyList(Iterator<String> iterator, boolean reversed) {
        ArrayList<String> keys=new ArrayList<>();
        while(iterator.hasNext())
            keys.add(iterator.next());

        if(reversed==true)
            Collections.reverse(keys);
        return keys;
    }

    @Override
    public ArrayList<String> sortAlphabetical(ArrayList<String> keys, boolean reversed) {
        ArrayList<String> keyList=keys;
        Collections.sort(keyList, new Comparator(){
            @Override
            public int compare(Object s1, Object s2) {
                return ((String)s1).compareToIgnoreCase((String)s2);
            }
        });
        if(reversed)
            Collections.reverse(keyList);
        return keyList;
    }

    @Override
    public View getCleanedPanel(Context context) {
        LinearLayout ll= new ScannerActivity().scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
        ll.removeAllViews();
        return ll;
    }
}
