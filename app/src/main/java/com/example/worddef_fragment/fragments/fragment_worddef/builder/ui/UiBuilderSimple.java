package com.example.worddef_fragment.fragments.fragment_worddef.builder.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_worddef.FragmentWordDef;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.Word;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.operator.WordOperator;
import com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed.ContainerLower;
import com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed.ContainerMain;
import com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed.ContainerUpper;
import com.example.worddef_fragment.fragments.processes.operator.FragmentOperatorFactory;
import com.example.worddef_fragment.other.ScannerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class UiBuilderSimple implements UiBuilder {

    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A

    private Context context;
    private String setName;

    public UiBuilderSimple(Context context, String setName) {
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

    @Override
    public void buildByCreateDateAsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("wordset",context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys= getKeyList(jObj.keys(), false);
        createView(ll, keys, jObj);
    }

    @Override
    public void buildByCreateDateDsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("wordset",context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys= getKeyList(jObj.keys(), true);
        createView(ll, keys, jObj);
    }

    @Override
    public void buildByAlphabeticalAsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("wordset",context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys=sortAlphabetical(getKeyList(jObj.keys(), false),false);

        createView(ll, keys, jObj);
    }

    @Override
    public void buildByAlphabeticalDsc() {
        JSONObject jObj = new FragmentOperatorFactory().create("wordset", context).get(setName);
        LinearLayout ll = (LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys = sortAlphabetical(getKeyList(jObj.keys(), false), true);

        createView(ll, keys, jObj);
    }

    @Override
    public void createView(LinearLayout ll, ArrayList<String> keys, JSONObject jObj) {
        Word word;
        WordOperator wordOperator=new WordOperator();
        for(int i=0;i<keys.size();i++) {
            try {
                word=wordOperator.convert2Word(jObj.getJSONObject(keys.get(i)), keys.get(i));
            }catch (JSONException je) {
                je.printStackTrace();
                break;
            }
            ll.addView(new ContainerMain(context, new ContainerUpper(context, word.getWrd())
                    , new ContainerLower(context, word.getDef())));
        }
    }

}
