package com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.worddef_fragment.fragments.fragment_worddef.views.txtView.TvLang;
import com.example.worddef_fragment.fragments.fragment_worddef.views.txtView.TvWord;

public class ContainerUpper extends LinearLayout {
    private String strWord, strLang;
    TextView tvWord, tvLang;
    private Context context;

    public ContainerUpper(Context context, String strWord, String  strLang) {
        super(context);
        this.context=context;
        this.strWord=strWord;
        this.strLang=strLang;
        onCreate();
    }

    public ContainerUpper(Context context, String strWord) {
        super(context);
        this.context=context;
        this.strWord=strWord;
        onCreate();
    }

    void onCreate() {
        setStyle();
        addChildren();
    }

    void setStyle() {
        setOrientation(HORIZONTAL);
    }

    void addChildren() {
        createChild();
        TextView[] tvArr={tvWord, tvLang};
        for(int i=0;i<tvArr.length;i++) {
            if(tvArr[i]!=null) addView(tvArr[i]);
        }
    }

    void createChild() {
        if(strWord!=null) tvWord=new TvWord(context, strWord);
        if(strLang!=null && !strLang.equals("")) {
            tvLang=new TvLang(context, strLang);
        }
    }
}
