package com.example.worddef_fragment.fragments.fragment_wordset.editor;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.widget.LinearLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragment_wordset.manager.WordsetManager;
import com.example.worddef_fragment.fragments.fragment_wordset.views.container.ContainerWrdset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    CLASS FOR UI UPDATION FOR WORDSET FRAGMENT.
 */

public class UiEdtrWrdSet {
    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A

    private Context context;

    public UiEdtrWrdSet(Context context) {
        this.context = context;
    }

    public void updateScrn(int order) {
        switch (order) {
            case 0:
                buildByCrtdDateDsc();
                break;
            case 1:
                buildByCrtdDateAsc();
                break;
            case 2:
                buildByAlphAsc();
                break;
            case 3:
                buildByAlphDsc();
                break;
        }
    }

    private void buildByCrtdDateDsc() {
        WordsetManager manager=new WordsetManager();
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        ArrayList<String> fileNames=manager.explore(context).getNames(new PathPicker(context).get(PathPicker.WORDSET));

        for(int i=(fileNames.size()-1);i>=0;i--) {
            llWrdSet.addView(new ContainerWrdset(context,fileNames.get(i),true));
        }
        FragmentWordSet.ORDER_BY=BY_CRTD_DSC;
    }

    private void buildByCrtdDateAsc() {
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        ArrayList<String> fileNames=new WordsetManager().explore(context).getNames(new PathPicker(context).get("wordset"));

        for(int i=0;i<fileNames.size();i++) {
            llWrdSet.addView(new ContainerWrdset(context,fileNames.get(i),true));
        }
        FragmentWordSet.ORDER_BY=BY_CRTD_ASC;
    }

    private void buildByAlphAsc() {
        ArrayList<String> list=new WordsetManager().explore(context).getNames(new PathPicker(context).get(PathPicker.WORDSET));
        Collections.sort(list, new Comparator(){

            @Override
            public int compare(Object s1, Object s2) {
                return ((String)s1).compareToIgnoreCase((String)s2);
            }
        });
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        for(int i=0;i<list.size();i++) {
            llWrdSet.addView(new ContainerWrdset(context,list.get(i),true));
        }
        FragmentWordSet.ORDER_BY=BY_ALPH_ASC;
    }

    private void buildByAlphDsc() {
        ArrayList<String> list=new WordsetManager().explore(context).getNames(new PathPicker(context).get(PathPicker.WORDSET));
        Collections.sort(list, new Comparator(){

            @Override
            public int compare(Object s1, Object s2) {
                return ((String)s1).compareToIgnoreCase((String)s2);
            }
        });
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        for(int i=(list.size()-1);i>=0;i--) {
            llWrdSet.addView(new ContainerWrdset(context,list.get(i),true));
        }
        FragmentWordSet.ORDER_BY= BY_ALPH_DSC;
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
