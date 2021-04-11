package com.example.worddef_fragment.tdk.fragment.views.textview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.CustomDialogFragment;
import com.example.worddef_fragment.tdk.fragment.FragmentTdk;
import com.example.worddef_fragment.tdk.fragment.views.container.TdkMainContainerLl;
import com.example.worddef_fragment.tdk.fragment.views.container.TdkPropContainerLl;

public class TvTdkDef extends androidx.appcompat.widget.AppCompatTextView {
    private FragmentTdk dialog;
    private  String def, kind;
    private TdkMainContainerLl mainContainerLl;
    private TdkPropContainerLl propContainerLl;
    private  FragmentTdk fragmentTdk;
    private CustomDialogFragment customDialogFragment;

    public TvTdkDef(@NonNull Context context, String def, TdkPropContainerLl propContainerLl, TdkMainContainerLl mainContainerLl) {
        super(context);
        this.def=def;
        this.kind=kind;
        this.propContainerLl=propContainerLl;
        this.mainContainerLl=mainContainerLl;
        setText(def);
        this.fragmentTdk=mainContainerLl.getFragmentTdk();
        this.customDialogFragment=mainContainerLl.getCustomDialogFragment();
        onCreate();
    }

    private void onCreate() {
        setView();
        setOnClickListener(new ClckLstnr(this));
    }

    private void setView() {
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        this.setTextColor(Color.BLACK);
        setTextSize(20);
        setTypeface(Typeface.DEFAULT);
    }

    private class ClckLstnr implements OnClickListener {
        private View view;
        public ClckLstnr(View txtView) {
            this.view=txtView;
        }

        @Override
        public void onClick(View view) {
            System.out.println("----------------");
            System.out.println("Word: "+ mainContainerLl.getStrWrd());
            System.out.println("Lang: "+ mainContainerLl.getLang());
            System.out.println("Kind: "+ propContainerLl.getKind());
            System.out.println("Def: "+ propContainerLl.getDef());
            System.out.println("Exmp: "+ propContainerLl.getExmp());
            System.out.println("----------------");

            customDialogFragment.getEtWrd().setText(mainContainerLl.getStrWrd());
            customDialogFragment.getEtExmp().setText(propContainerLl.getExmp());
            customDialogFragment.getEtLang().setText(mainContainerLl.getLang());
            customDialogFragment.getEtKind().setText(propContainerLl.getKind());
            customDialogFragment.getEtDef().setText(propContainerLl.getDef());
            fragmentTdk.dismiss();

        }
    }
}