package com.example.worddef_fragment.fragments.fragmentWordDef.editor;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.widget.LinearLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.editor.word_def.WordDefEditor;
import com.example.worddef_fragment.file.editor.WordSetEditor;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.LlLower;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.LlMain;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.LlUpper;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.LlUpperSubLeft;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.PnlContainerWrdDef;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.PnlUpper;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView.TvLang;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView.ViewDefinition;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView.ViewWord;
import com.example.worddef_fragment.fragments.fragmentWordSet.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.panel.PnlContainerWrdSet;
import com.example.worddef_fragment.other.sorter.Sorter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class UiEdtrWrdDef {

    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A


    private Context context;
    private WordSetEditor editor;
    private String setName;

    public UiEdtrWrdDef(Context context,String setName) {
        this.context = context;
        this.editor=new WordSetEditor(context);
        this.setName=setName;
    }

    public void updateScrn(int order) {
        switch (order) {
            case 0: // Default first build
                //buildByCrtdDateDsc();
                newBuild();
                break;
            case 1:
                buildByCrtdDateAsc();
                break;
            case 2:
                //buildByAlphAsc();
                break;
            case 3:
                //buildByAlphDsc();
                break;
        }
    }

    private void buildByCrtdDateDsc() {
        JSONObject jObj=new WordDefEditor(context,setName).getJSONObj();
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
                pts=ji.getInt("pts");


                ll.addView(new PnlContainerWrdDef(context,
                        new PnlUpper(context
                                , new ViewWord(context, word, setName)),
                        new ViewDefinition(context, def)));
            }
        } catch (JSONException jsonException) { jsonException.printStackTrace(); }
    }

    private void buildByCrtdDateAsc() {
        JSONObject jObj=new WordDefEditor(context,setName).getJSONObj();
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

                /*
                ll.addView(new PnlContainerWrdDef(context,
                        new PnlUpper(context
                                , new ViewWord(context, word, setName)
                                ,new TvPts(context,pts)
                                ,new Lamp(context)),
                        new ViewDefinition(context, def)));

                word = itrtKeys.next();
                ll.addView(new PnlContainerWrdDef(context,
                        new PnlUpper(context, new ViewWord(context, word, setName), new Lamp(context)),
                        new ViewDefinition(context, jObj.getString(word))));

                 */
            }
        }catch (JSONException je){je.printStackTrace();}
    }

    private void buildByAlphDsc() {
        ArrayList<String> list=new Sorter().getAlphabetical(context);
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        for(int i=(list.size()-1);i>=0;i--) {
            llWrdSet.addView(new PnlContainerWrdSet(context,list.get(i)));
        }
        FragmentWordSet.ORDER_BY= BY_ALPH_DSC;
    }

    private void buildByCrtdDateDsc2() {
        JSONObject jObj=new WordDefEditor(context,setName).getJSONObj();
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
                pts=ji.getInt("pts");


                ll.addView(new PnlContainerWrdDef(context,
                        new PnlUpper(context
                                , new ViewWord(context, word, setName)),
                        new ViewDefinition(context, def)));
            }
        } catch (JSONException jsonException) { jsonException.printStackTrace(); }
    }

    public void newBuild() {
        JSONObject jObj=new WordDefEditor(context,setName).getJSONObj();
        JSONObject jsub;
        LinearLayout ll=scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
        ll.removeAllViews();

        String word,def="",exmp="",lang="",kind="",author="";
        try {
            Iterator<String> itrtKeys=jObj.keys();

            while (itrtKeys.hasNext()) {

                word = itrtKeys.next();
                jsub = jObj.getJSONObject(word);

                def=jsub.getString("def");

                if(jsub.has("exmp"))
                    exmp=jsub.getString("exmp");
                if(jsub.has("lang"))
                    lang=jsub.getString("lang");
                if(jsub.has("author"))
                    author=jsub.getString("author");
                if(jsub.has("kind"))
                    kind=jsub.getString("kind");

                ll.addView(new LlMain(context,
                        new LlUpper(context
                                        //,new LlUpperSubLeft(context, lang, kind)
                                        //,new TvWord(context,word))
                                ,new LlUpperSubLeft(context, word, kind)
                                , lang)
                        ,new LlLower(context,def,exmp)));

            }
        }catch (JSONException je) {je.printStackTrace();}
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
