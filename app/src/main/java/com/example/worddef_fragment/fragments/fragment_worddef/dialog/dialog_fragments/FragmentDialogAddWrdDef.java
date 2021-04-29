package com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.processes.operator.FragmentOperatorFactory;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.operator.WordOperator;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.data.Word;
import com.example.worddef_fragment.reaction.Reaction;
import com.example.worddef_fragment.tdk.TdkBtnLstner;
import com.example.worddef_fragment.tdk.TdkTxtWatcher;
import com.example.worddef_fragment.tdk.process.TdkWord;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDialogAddWrdDef extends AppCompatDialogFragment implements CustomDialogFragment {

    private int MTCH_PRNT=WindowManager.LayoutParams.MATCH_PARENT;

    private Button btnCncl,btnAddWrdDef, btnDsplyTdk;
    private BtnAddWrdDefLstnr addWrdDefLstnr;
    private ProgressBar pbTdk;

    private EditText etDlgAddWrdStr, etDlgAddDef, etDlgAddKind, etDlgAddLang, etDlgAddExmp;
    private LinearLayout pnlWrdDefVrt;

    private String setName;

    private ArrayList<TdkWord> tdkWordList;

    private TdkTxtWatcher etWrdTxtWtchr;

    private Word word; // Object that holds selected word's features.

    public FragmentDialogAddWrdDef(LinearLayout pnlWrdDefVrt, String setName) {
        this.pnlWrdDefVrt=pnlWrdDefVrt;
        this.setName=setName;
        word=new Word();
    }

    // FIRST CALLED
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void setView() {
        WindowManager.LayoutParams lp=getDialog().getWindow().getAttributes();
        lp.width=MTCH_PRNT;
        lp.height=MTCH_PRNT;
        getDialog().getWindow().setLayout(MTCH_PRNT,MTCH_PRNT);
    }

    private void setBckGrnd() {
        if(getDialog() !=null && getDialog().getWindow() !=null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    //2ND CALLED
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_word_def_add,container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addWrdDefLstnr = new BtnAddWrdDefLstnr(FragmentDialogAddWrdDef.this,setName, pnlWrdDefVrt);

        etDlgAddWrdStr = view.findViewById(R.id.etDlgAddWrd);
        etDlgAddDef = view.findViewById(R.id.etDlgAddDef);
        etDlgAddExmp=view.findViewById(R.id.etDlgAddExmp);
        etDlgAddLang= view.findViewById(R.id.etDlgAddLang);
        etDlgAddKind= view.findViewById(R.id.etDlgAddKind);

        btnAddWrdDef = view.findViewById(R.id.btnDlgAddWrdSetAdd);
        btnCncl = view.findViewById(R.id.btnDlgAddWrdSetCncl);
        btnDsplyTdk=view.findViewById(R.id.btnDsplyTdkAdd);
        pbTdk=view.findViewById(R.id.pbTdkAdd);

        btnDsplyTdk.setOnClickListener(new TdkBtnLstner(FragmentDialogAddWrdDef.this));
        btnCncl.setOnClickListener(new BtnCnclWrdDefLstenr(this));
        btnAddWrdDef.setOnClickListener(addWrdDefLstnr);

        etWrdTxtWtchr=new TdkTxtWatcher(FragmentDialogAddWrdDef.this, btnDsplyTdk, pbTdk, etDlgAddDef);
        etDlgAddWrdStr.addTextChangedListener(etWrdTxtWtchr);

        setBckGrnd();
    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public EditText getEtWrd() {
        return etDlgAddWrdStr;
    }

    @Override
    public EditText getEtDef() {
        return etDlgAddDef;
    }

    @Override
    public EditText getEtLang() {
        return etDlgAddLang;
    }

    @Override
    public EditText getEtKind() {
        return etDlgAddKind;
    }

    @Override
    public EditText getEtExmp() {
        return etDlgAddExmp;
    }

    @Override
    public Word getWord() {
        return word;
    }

    @Override
    public void setWord(Word word) {
        this.word=word;
    }

    public Button getBtnDsplyTdk() {
        return btnDsplyTdk;
    }

    @Override
    public void setTdkWordList(ArrayList<TdkWord> tdkWordList) {
        this.tdkWordList=tdkWordList;
    }

    @Override
    public ArrayList<TdkWord> getTdkWordList() {
        return this.tdkWordList;
    }

    @Override
    public ProgressBar getPbTdk() {
        return pbTdk;
    }

    public void removeEtWrdTxtWtcher() {
        System.out.println("--Txt Watcher Removed!");
        etDlgAddWrdStr.removeTextChangedListener(etWrdTxtWtchr);
    }

    public void reAttachEtWrdListener() {
        System.out.println("--Txt Watcher Reattached!");
        etDlgAddWrdStr.addTextChangedListener(etWrdTxtWtchr);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class BtnCnclWrdDefLstenr implements View.OnClickListener {

        private FragmentDialogAddWrdDef dialog;
        public BtnCnclWrdDefLstenr(FragmentDialogAddWrdDef dialog) { this.dialog=dialog;}

        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class BtnAddWrdDefLstnr implements View.OnClickListener {

        private FragmentDialogAddWrdDef dialog;
        private  LinearLayout pnlWrdDefVrt;
        private String setName;
        private String appearance= new SPEditor().getValue(getContext(), SPEditor.APPEARANCE);

        public BtnAddWrdDefLstnr(FragmentDialogAddWrdDef dialog,String setName, LinearLayout pnlWrdDefVrt) {
            this.dialog=dialog;
            this.pnlWrdDefVrt=pnlWrdDefVrt;
            this.setName=setName;
        }

        @Override
        public void onClick(View view) {
            String wrdStr = ((EditText) view.getRootView().findViewById(R.id.etDlgAddWrd)).getText().toString();
            String defStr= ((EditText) view.getRootView().findViewById(R.id.etDlgAddDef)).getText().toString();
            String exmpStr=((EditText) view.getRootView().findViewById(R.id.etDlgAddExmp)).getText().toString();
            String kindStr=((EditText) view.getRootView().findViewById(R.id.etDlgAddKind)).getText().toString();
            String langStr=((EditText) view.getRootView().findViewById(R.id.etDlgAddLang)).getText().toString();
            int ptsDefault = 10;

            if (isEmpty(wrdStr))
                Toast.makeText(view.getContext(), "PLEASE INPUT WORD!", Toast.LENGTH_SHORT).show();
            else {
                getWord().setWrd(wrdStr);
                getWord().setDef(defStr);
                if(exmpStr!=null) getWord().setExmp(exmpStr);
                if(kindStr!=null) getWord().setKind(kindStr);
                if(langStr!=null) getWord().setLang(langStr);

                if(! new FragmentOperatorFactory().create("worddef",getContext()).add(word.getWrd(), new WordOperator().convert2Json(getWord())))
                    new Reaction(getContext()).showShort(getContext().getResources().getString(R.string.word_exists));
                else {
                    new BuilderEditor().getUiEditor(getContext(), setName).updateScreen();
                    dialog.dismiss();
                }
            }
        }

        private boolean isEmpty(String txt) {
            Pattern p = Pattern.compile("\\w");
            Matcher m = p.matcher(txt);

            if(m.find()) {
                return false;
            }
            else return true;
        }
    }
}

