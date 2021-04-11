package com.example.worddef_fragment.tdk.fragment.views.container;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.worddef_fragment.tdk.fragment.views.textview.TvTdkDef;
import com.example.worddef_fragment.tdk.fragment.views.textview.TvTdkExmp;
import com.example.worddef_fragment.tdk.fragment.views.textview.TvTdkKind;

public class TdkPropContainerLl extends LinearLayout {
    private String def, kind, exmp;
    private TdkMainContainerLl mainContainerLl;
    public TdkPropContainerLl(Context context, String def, String kind, String exmp, TdkMainContainerLl mainContainerLl) {
        super(context);
        this.def=def;
        this.exmp=exmp;
        this.kind=kind;
        this.mainContainerLl=mainContainerLl;
        onCreate();
    }

    private void onCreate() {
        setStyle();
        addChildren();
    }

    private void addChildren() {
        if(kind!=null)  addView(new TvTdkKind(getContext(), kind));
        addView(new TvTdkDef(getContext(), def, TdkPropContainerLl.this, mainContainerLl));
        if(exmp!=null) addView(new TvTdkExmp(getContext(), exmp));
    }

    private void setStyle() {
        setOrientation(VERTICAL);
        LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,0, 0, 40);
        setLayoutParams(lp);
        //setBackgroundColor(Color.GRAY);
    }

    public String getDef() {
        return def;
    }

    public String getKind() {
        return kind;
    }

    public String getExmp() {
        return exmp;
    }
}
