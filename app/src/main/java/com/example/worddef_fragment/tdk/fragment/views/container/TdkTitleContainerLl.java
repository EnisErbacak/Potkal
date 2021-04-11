package com.example.worddef_fragment.tdk.fragment.views.container;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.example.worddef_fragment.tdk.fragment.views.textview.TvTdkLang;
import com.example.worddef_fragment.tdk.fragment.views.textview.TvTdkTitle;

public class TdkTitleContainerLl extends LinearLayout{

    private  String  strWrd, lang;
    private TvTdkTitle tvTdkTitle;
    private TvTdkLang tvTdkLang;

    public TdkTitleContainerLl(Context context, String strWrd, String lang) {
        super(context);
        this.strWrd=strWrd;
        this.lang=lang;
        onCreate();
    }

    void onCreate() {
        setStyle();
        addChildren();
    }

    void addChildren() {
        tvTdkTitle=new TvTdkTitle(getContext(),strWrd);
        tvTdkLang=new TvTdkLang(getContext(), lang);
        addView(tvTdkTitle);
        addView(tvTdkLang);
    }

    void setStyle() {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
    }
}
