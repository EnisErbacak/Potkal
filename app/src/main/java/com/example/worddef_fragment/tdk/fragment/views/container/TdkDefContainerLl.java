package com.example.worddef_fragment.tdk.fragment.views.container;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.worddef_fragment.tdk.process.TdkProperty;

import java.util.ArrayList;

public class TdkDefContainerLl extends LinearLayout {
    private ArrayList<TdkProperty> tdkPropertyList;
    private TdkMainContainerLl mainContainerLl;
    public TdkDefContainerLl(Context context, ArrayList<TdkProperty> tdkPropertyList, TdkMainContainerLl mainContainerLl) {
        super(context);
        this.tdkPropertyList=tdkPropertyList;
        this.mainContainerLl=mainContainerLl;
        onCreate();
    }

    private void onCreate() {
        setStyle();
        addChildren();
    }

    private void setStyle() {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        setOrientation(VERTICAL);
        //lp.setMargins(0, 5, 0, 5);
    }

    private void addChildren() {
        for(TdkProperty property: tdkPropertyList) {
            addView(new TdkPropContainerLl(getContext(), property.getDef(), property.getKind(), property.getExmp(), mainContainerLl));
            //addView(new TvTdkDef(getContext(), property.getDef()));
            //if(property.getKind()!=null) addView(new TvTdkKind(getContext(), property.getKind()));
            //if(property.getExmp()!=null) addView(new TvTdkExmp(getContext(), property.getExmp()));
        }
    }


}
