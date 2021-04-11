package com.example.worddef_fragment.tdk.fragment.views.container;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.CustomDialogFragment;
import com.example.worddef_fragment.tdk.fragment.FragmentTdk;
import com.example.worddef_fragment.tdk.process.TdkProperty;

import java.util.ArrayList;

public class TdkMainContainerLl extends LinearLayout{

    private  String  strWrd, lang;
    private ArrayList<TdkProperty> tdkPropertyList;
    private TdkTitleContainerLl titleContainerLl;
    private CustomDialogFragment customDialogFragment;
    private FragmentTdk fragmentTdk;

    public TdkMainContainerLl(Context context, String strWrd, String lang, ArrayList<TdkProperty> tdkPropertyList, CustomDialogFragment customDialogFragment, FragmentTdk fragmentTdk) {
        super(context);
        this.strWrd=strWrd;
        this.tdkPropertyList=tdkPropertyList;
        this.lang=lang;
        this.customDialogFragment=customDialogFragment;
        this.fragmentTdk=fragmentTdk;
        onCreate();
    }

    void onCreate() {
        setStyle();
        addChildren();
    }

    void addChildren() {
        titleContainerLl=new TdkTitleContainerLl(getContext(), strWrd, lang);
        addView(titleContainerLl);
        addView(new TdkDefContainerLl(getContext(), tdkPropertyList, TdkMainContainerLl.this));
    }

    void setStyle() {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        setOrientation(VERTICAL);
    }

    public String getStrWrd() {
        return strWrd;
    }

    public String getLang() {
        return lang;
    }

    public CustomDialogFragment getCustomDialogFragment() {
        return customDialogFragment;
    }

    public FragmentTdk getFragmentTdk() {
        return fragmentTdk;
    }
}