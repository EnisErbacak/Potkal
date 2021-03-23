package com.example.worddef_fragment.tdk;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragmentWordDef.dialog.dialog_fragments.CustomDialogFragment;

import java.util.ArrayList;

public class TdkFragment extends DialogFragment {
    private ArrayList<String> defs;
    private TdkObject tdkObject;
    private LinearLayout pnlDefs;
    private EditText etDef;
    private TextView tvTdkWord;
    private final String[]  KELIME_TURLERI={"isim","sıfat","zarf","zamir", "fiil","edat"};
    private CustomDialogFragment customDialogFragment;

    public TdkFragment(ArrayList<String> defs, CustomDialogFragment customDialogFragment, EditText etDef, TdkObject tdkObject) {
        this.defs=defs;
        this.etDef =etDef;
        this.tdkObject = tdkObject;
        this.customDialogFragment = customDialogFragment;
    }

    public EditText getEtDef() {
        return etDef;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tdk_def,container);

        pnlDefs=view.getRootView().findViewById(R.id.pnlTdkDef);
        pnlDefs.removeAllViews();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context=getContext();
        String lisan="",wrdStr="";
        wrdStr= tdkObject.getKelime();

        tvTdkWord=view.findViewById(R.id.tvTdkWord);
        tvTdkWord.setText(tdkObject.getKelime().toUpperCase());
        customDialogFragment.getWord().setWrd(tdkObject.getKelime());
        pnlDefs.removeAllViews();

        lisan= tdkObject.getLisan();
        if(tdkObject !=null) {
            for (int i = 0; i < tdkObject.getAnlamlar().size(); i++) {
                String tur="",anlam="", ornek="";

                tur= tdkObject.getAnlamlar().get(i).getKelimeTuru();

                if(tur.equals("")) {
                    tur= tdkObject.getAnlamlar().get(i-1).getKelimeTuru();
                }

                if(! checkTur(tur)) {
                    for(int k=i;k>=0;k--) {
                        if(getTur(tdkObject.getAnlamlar().get(k).getKelimeTuru()))
                            tur= tdkObject.getAnlamlar().get(k).getKelimeTuru() +", "+tur;
                    }
                }
                anlam= tdkObject.getAnlamlar().get(i).getAnlam();

                ornek= tdkObject.getAnlamlar().get(i).getOrnek();
                if(tdkObject.getAnlamlar().get(i).getYazar()!="")
                    ornek=ornek+" - "+ tdkObject.getAnlamlar().get(i).getYazar();

                pnlDefs.addView(new LlContainer(context, TdkFragment.this, customDialogFragment, wrdStr, anlam, lisan, tur, ornek));
            }
        }

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private boolean checkTur(String tur) {
        boolean result=false;
        for(String str:KELIME_TURLERI) {
            if( str.equals(tur)) {
                result=true;
            }
        }
        return result;
    }

    private boolean getTur(String tur) {
        boolean result=false;
        for(String str:KELIME_TURLERI) {
            if(str.equals(tur))
                result=true;
        }
        return result;
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class LlContainer extends LinearLayout {

    private String wrdStr,def,lang,exmp,kind;
    TdkFragment dialog;
    private CustomDialogFragment customDialogFragment;

    public LlContainer(Context context,TdkFragment dialog,CustomDialogFragment customDialogFragment,String wrdStr, String def, String lang, String kind, String exmp) {
        super(context);
        this.wrdStr = wrdStr;
        this.def = def;
        this.exmp = exmp;
        this.lang=lang;
        this.kind=kind;
        this.dialog=dialog;
        this.customDialogFragment = customDialogFragment;
        onCreate();
    }

    void onCreate() {
        setOrientation(VERTICAL);
        setView();
        addView(new TvAnlam(getContext(), def, kind,dialog ));
        if(!exmp.equals(""))
            addView(new TvOrnek(getContext(), exmp));
    }

    void setView() {
        LinearLayout.LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        lp.setMargins(0, 20, 0, 20);
    }

    public void setData() {

        customDialogFragment.getWord().setDef(def);
        customDialogFragment.getWord().setExmp(exmp);
        customDialogFragment.getWord().setKind(kind);
        customDialogFragment.getWord().setLang(lang);
        //dialog.getEtDef().setText(def);

        customDialogFragment.getEtDef().setText(def);
        customDialogFragment.getEtExmp().setText(exmp);
        customDialogFragment.getEtKind().setText(kind);
        customDialogFragment.getEtLang().setText(lang);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class TvOrnek extends androidx.appcompat.widget.AppCompatTextView{

        private String example, yazar;
        public TvOrnek(@NonNull Context context, String example) {
            super(context);
            this.example=example;
            this.yazar=yazar;
            onCreate();
        }

        void onCreate() {
            setView();

            setText(example);
        }

        void setView() {
            LinearLayout.LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins((int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20,
                    getContext().getResources().getDisplayMetrics()
            ),0,0,0);
            setLayoutParams(lp);
            setTextColor(Color.BLACK);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class TvAnlam extends androidx.appcompat.widget.AppCompatTextView {

        private String def, kind;
        private TextView txtView;
        private TdkFragment dialog;

        public TvAnlam(@NonNull Context context, String def, String kind, TdkFragment dialog) {
        super(context);
        this.def =def;
        this.txtView=txtView;
        this.dialog=dialog;

        String txt="( "+kind+") "+ def;
        setText(txt);
        onCreate();
    }

        public String getDef() {
        return def;
    }

        private void onCreate() {
        setView();
        setOnClickListener(new ClckLstnr(this));
    }

        private void setView() {
        LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(lp);
        this.setTextColor(Color.BLACK);
        setTextSize(16);
    }

        private class ClckLstnr implements OnClickListener {
            private View view;
            public ClckLstnr(View txtView) {
                this.view=txtView;
            }

            @Override
            public void onClick(View view) {
                EditText etDef= view.findViewById(R.id.etDlgAddDef); // Definition on add word def fragment
                LlContainer parent= (LlContainer) getParent();

                parent.setData();
                dialog.dismiss();
            }
        }
    }
}


