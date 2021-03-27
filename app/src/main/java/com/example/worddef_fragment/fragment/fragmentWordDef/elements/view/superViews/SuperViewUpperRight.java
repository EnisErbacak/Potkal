package com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.superViews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SuperViewUpperRight extends View {
    public SuperViewUpperRight(Context context) {
        super(context);
    }

    private void Oncreate() {

    }

    private void setView(){
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
