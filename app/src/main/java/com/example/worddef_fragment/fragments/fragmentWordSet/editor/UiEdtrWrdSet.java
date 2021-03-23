package com.example.worddef_fragment.fragments.fragmentWordSet.editor;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.widget.LinearLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.editor.WordSetEditor;
import com.example.worddef_fragment.fragments.fragmentWordSet.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.panel.PnlContainerWrdSet;
import com.example.worddef_fragment.other.sorter.Sorter;

import java.util.ArrayList;

/*
    CLASS FOR UI UPDATION FOR WORDSET FRAGMENT.
 */

public class UiEdtrWrdSet {
    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A

    private Context context;
    private WordSetEditor editor;

    public UiEdtrWrdSet(Context context) {
        this.context = context;
        this.editor=new WordSetEditor(context);
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
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        ArrayList<String> fileNames=editor.getAllSetNames();

        for(int i=(fileNames.size()-1);i>=0;i--) {
            llWrdSet.addView(new PnlContainerWrdSet(context,fileNames.get(i)));
        }
        FragmentWordSet.ORDER_BY=BY_CRTD_DSC;
    }

    private void buildByCrtdDateAsc() {
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        ArrayList<String> fileNames=editor.getAllSetNames();

        for(int i=0;i<fileNames.size();i++) {
            llWrdSet.addView(new PnlContainerWrdSet(context,fileNames.get(i)));
        }
        FragmentWordSet.ORDER_BY=BY_CRTD_ASC;
    }

    private void buildByAlphAsc() {
        ArrayList<String> list=new Sorter().getAlphabetical(context);
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        for(int i=0;i<list.size();i++) {
            llWrdSet.addView(new PnlContainerWrdSet(context,list.get(i)));
        }
        FragmentWordSet.ORDER_BY=BY_ALPH_ASC;
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
