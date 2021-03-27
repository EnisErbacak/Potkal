package com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.panel;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView.TvKind;
import com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView.TvWord;

public class LlUpperSubLeft extends LinearLayout {

    private String word, kind;

    public LlUpperSubLeft(Context context, String word, String kind) {
        super(context);
        this.word = word;
        this.kind = kind;
        onCreate();
    }

    void onCreate() {
        setView();
    }

    void setView() {
        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(new TvWord(getContext(), word));
        if(! kind.equals("")) addView(new TvKind(getContext(), kind));
    }
}
