package com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.txtView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragmentWordSet.elements.popup.PopupWordSetEdit;
import com.example.worddef_fragment.fragments.fragmentWordDef.FragmentWordDef;

public class TxtViewWrdSetLft extends androidx.appcompat.widget.AppCompatTextView
{
    private String setName;
    private LinearLayout.LayoutParams lp;

    private final int MTCH_PRNT= LinearLayout.LayoutParams.MATCH_PARENT;
    private final int WRP_CNTNT= LinearLayout.LayoutParams.WRAP_CONTENT;

    private boolean isSingLine=false;

    private final int LYT_WDTH=MTCH_PRNT;
    private final int LYT_HGT=WRP_CNTNT;

    // Margin values
    private final int VAL_MRGN_LFT=pix2Dip(10);
    private final int VAL_MRGN_TOP=pix2Dip(7);
    private final int VAL_MRGN_RGHT=pix2Dip(10);
    private final int VAL_MRGN_BTTM=pix2Dip(7);

    // Padding values
    private final int VAL_PAD_LFT=pix2Dip(5);
    private final int VAL_PAD_TOP=pix2Dip(7);
    private final int VAL_PAD_RGHT=pix2Dip(0);
    private final int VAL_PAD_BTTM=pix2Dip(7);

    private final int CRNR_RDS=pix2Dip(15);

    private final int COL_BG=Color.parseColor("#C7E6FB");
    private final int COL_TXT=Color.parseColor("#111F29");
    //private final int SIZE_TXT=pix2Dip(10);
    private final int SIZE_TXT=28; // IN SP

    private Context context;


    public TxtViewWrdSetLft(Context context, String setName) {
        super(context);
        this.setName=setName;
        this.context=context;
        onCreate();
    }

    void onCreate() {
        setView();
        setConditions(TxtViewWrdSetLft.this);
    }

    void setView() {
        lp=new LinearLayout.LayoutParams(MTCH_PRNT,WRP_CNTNT);

        setLytPrms(lp);
        setTextColor(COL_TXT);

       // setTextSize(TypedValue.COMPLEX_UNIT_SP,SIZE_TXT);
        setTextSize(TypedValue.COMPLEX_UNIT_SP,getTxtSize());
        setTypeface(getTypeface(),Typeface.BOLD);

        setBackgroundColor(COL_BG);
        setText(setName);
        setSingleLine(isSingLine);
    }

    void setConditions(View view) {
        view.setOnClickListener(new TxtViewWrdSetLngClckLstnr(getSetName(),getFragmentManager(view)));
        setOnLongClickListener(new TxtViewWrdSetLngLstnr(view.getContext()));
    }

    //For attaching the new fragment to the listener of textView
    public void update(String setName) {
        setOnClickListener(new TxtViewWrdSetLngClckLstnr(setName,getFragmentManager(TxtViewWrdSetLft.this)));
    }

    public String getSetName(){return setName;}
    public void setSetName(String setName){this.setName=setName;}
    private FragmentActivity getFragmentActivity(View view) {return (FragmentActivity) view.getContext(); }
    private FragmentManager getFragmentManager(View view) {return ((FragmentActivity)getFragmentActivity(view)).getSupportFragmentManager();}
    private void setLytPrms(LinearLayout.LayoutParams lp) {setLayoutParams(lp);}
    private int pix2Dip(int value){return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getResources().getDisplayMetrics()));}

    private int getTxtSize() {
        return Integer.valueOf(new SPEditor().getValue(context, SPEditor.SET_TXT_SIZE));
    }
}

class TxtViewWrdSetLngClckLstnr implements View.OnClickListener {
    private String setName;
    private FragmentManager fragmentManager;
    private FragmentWordDef fragmentWordDef;

    public TxtViewWrdSetLngClckLstnr(String setName, FragmentManager fragmentManager){this.setName=setName;this.fragmentManager=fragmentManager;}
    @Override
    public void onClick(View view) {
        fragmentWordDef=FragmentWordDef.newInstance(setName);
        fragmentManager.beginTransaction().replace(R.id.containerActivityMain, FragmentWordDef.newInstance(setName)).commit();
    }
}

class TxtViewWrdSetLngLstnr implements View.OnLongClickListener {
    private Context context;
    public TxtViewWrdSetLngLstnr(Context context){this.context=context;}
    @Override
    public boolean onLongClick(View view) {
        PopupWordSetEdit menu=new PopupWordSetEdit(context,view);
        menu.show();
        return false;
    }
}
