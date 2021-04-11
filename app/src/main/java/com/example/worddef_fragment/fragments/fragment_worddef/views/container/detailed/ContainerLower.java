package com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.worddef_fragment.fragments.fragment_worddef.views.txtView.TvDef;
import com.example.worddef_fragment.fragments.fragment_worddef.views.txtView.TvExmp;
import com.example.worddef_fragment.fragments.fragment_worddef.views.txtView.TvKind;

public class ContainerLower extends LinearLayout {
    private TextView tvDef,tvExmp, tvKind;
    private String strDef, strExmp, strKind;
    private TextView[] tvArr;
    private Context context;
    public ContainerLower(Context context, String strKind, String strDef, String strExmp) {
        super(context);
        this.context=context;
        this.strDef=strDef;
        this.strKind=strKind;
        this.strExmp=strExmp;
        onCreate();
    }

    public ContainerLower(Context context, String strDef) {
        super(context);
        this.context=context;
        this.strDef=strDef;
        onCreate();
    }


    void onCreate() {
        setStyle();
        tvArr=new TextView[]{tvDef, tvExmp, tvKind};
        addChildren();
    }

    void setStyle() {
        setOrientation(VERTICAL);
    }

    void addChildren() {
        createChild();
        TextView[] tvArr={tvKind, tvDef, tvExmp};
        for(int i=0;i<tvArr.length;i++) {
            if(tvArr[i]!=null) addView(tvArr[i]);
        }
    }

    void createChild() {
        if(strKind!=null && !strKind.equals("")) tvKind=new TvKind(context, strKind);
        if(strDef!=null && !strDef.equals("")) tvDef=new TvDef(context, strDef);
        if(strExmp!=null && !strExmp.equals("")) tvExmp=new TvExmp(context, strExmp);
    }

    public TextView getTvDef() {
        return tvDef;
    }
}
