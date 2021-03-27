package com.example.worddef_fragment.fragment.fragmentWordDef.editor.ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.widget.LinearLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragment.fragmentWordDef.FragmentWordDef;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel.PnlContainerWrdDef;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel.PnlUpper;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView.ViewDefinition;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView.ViewWord;
import com.example.worddef_fragment.fragment.processes.operator.FragmentOperatorFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class UiEditorWordDefSimple implements UiEditorWordDef {

    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A

    private Context context;
    private String setName;

    public UiEditorWordDefSimple(Context context, String setName) {
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
        return null;
    }

    @Override
    public ArrayList<String> sortAlphabetical(ArrayList<String> keys, boolean reversed) {
        return null;
    }

    @Override
    public View getCleanedPanel(Context context) {
        return null;
    }

    @Override
    public void buildByCreateDateAsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("worddef",context).get(setName);
        JSONObject ji;
        LinearLayout ll=scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
        ll.removeAllViews();
        Iterator<String> itrtKeys=jObj.keys();
        String  word,def;
        int pts;
        try {
            while (itrtKeys.hasNext()) {

                word=itrtKeys.next();
                ji=jObj.getJSONObject(word);

                def=ji.getString("def");
                pts=ji.getInt("pts");

                ll.addView(new PnlContainerWrdDef(context,
                        new PnlUpper(context
                                , new ViewWord(context, word, setName)),
                        new ViewDefinition(context, def)));
            }
        }catch (JSONException je){je.printStackTrace();}
    }

    @Override
    public void buildByCreateDateDsc() {
        JSONObject jObj=new FragmentOperatorFactory().create("worddef",context).get(setName);
        JSONObject ji;
        LinearLayout ll=scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
        ll.removeAllViews();
        String word,def;
        int pts;
        try {

            Iterator<String> itrtKeys=jObj.keys();
            ArrayList<String> rvrsdKeys=new ArrayList<String>();

            while(itrtKeys.hasNext())
                rvrsdKeys.add(itrtKeys.next());

            for(int i=rvrsdKeys.size()-1;i>=0;i--) {
                word=rvrsdKeys.get(i);
                ji=jObj.getJSONObject(word);

                def=ji.getString("def");
                //pts=ji.getInt("pts");

                ll.addView(new PnlContainerWrdDef(context,
                        new PnlUpper(context
                                , new ViewWord(context, word, setName)),
                        new ViewDefinition(context, def)));
            }
        } catch (JSONException jsonException) { jsonException.printStackTrace(); }
    }

    @Override
    public void buildByAlphabeticalAsc() {

    }

    @Override
    public void buildByAlphabeticalDsc() {

    }

    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());
        return null;
    }
}
