package com.example.worddef_fragment.fragments.fragmentWordDef.dialog.dialog_fragments;

import android.app.Dialog;
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
import com.example.worddef_fragment.file.editor.EditorFragmentWordDef;
import com.example.worddef_fragment.file.editor.word_def.WordDefEditor;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.data.operator.WordOperator;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.ui.operator.EditorOperator;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.data.Word;
import com.example.worddef_fragment.tdk.TdkBtnLstner;
import com.example.worddef_fragment.tdk.TdkObject;
import com.example.worddef_fragment.tdk.TdkTxtWatcher;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDialogAddWrdDef extends AppCompatDialogFragment implements CustomDialogFragment {

    private int MTCH_PRNT=WindowManager.LayoutParams.MATCH_PARENT;

    private Button btnCncl,btnAddWrdDef, btnDsplyTdk;
    private BtnAddWrdDefLstnr addWrdDefLstnr;
    private ProgressBar pbTdk;

    private EditText etDlgAddWrd, etDlgAddDef, etDlgAddKind, etDlgAddLang, etDlgAddExmp;
    private LinearLayout pnlWrdDefVrt;

    private String setName;

    private ArrayList<String> defList;
    private TdkObject tdkObject; // Object that holds all fetched result from "TDK".

    private Word word; // Object that holds selected word's features.

    public FragmentDialogAddWrdDef(LinearLayout pnlWrdDefVrt, String setName) {
        this.pnlWrdDefVrt=pnlWrdDefVrt;
        this.setName=setName;
        word=new Word();
        this.tdkObject=new TdkObject();
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

        etDlgAddWrd = view.findViewById(R.id.etDlgAddWrd);
        etDlgAddDef = view.findViewById(R.id.etDlgAddDef);
        etDlgAddExmp=view.findViewById(R.id.etDlgAddExmp);
        etDlgAddLang= view.findViewById(R.id.etDlgAddLang);
        etDlgAddKind= view.findViewById(R.id.etDlgAddKind);

        btnAddWrdDef = view.findViewById(R.id.btnDlgAddWrdSetAdd);
        btnCncl = view.findViewById(R.id.btnDlgAddWrdSetCncl);
        btnDsplyTdk=view.findViewById(R.id.btnDsplyTdkAdd);
        pbTdk=view.findViewById(R.id.pbTdkAdd);

        btnDsplyTdk.setOnClickListener(new TdkBtnLstner(FragmentDialogAddWrdDef.this, getTdkObject()));
        btnCncl.setOnClickListener(new BtnCnclWrdDefLstenr(this));
        btnAddWrdDef.setOnClickListener(addWrdDefLstnr);

        etDlgAddWrd.addTextChangedListener(new TdkTxtWatcher(FragmentDialogAddWrdDef.this, btnDsplyTdk, pbTdk, etDlgAddDef));

        setBckGrnd();
    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    public ArrayList<String> getDefList() {
        return defList;
    }

    public void setDefList(ArrayList<String> defList) {
        this.defList = defList;
    }

    public TdkObject getTdkObject() {
        return tdkObject;
    }

    public void setTdkObject(TdkObject tdkObject) {
        this.tdkObject = tdkObject;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public EditText getEtWrd() {
        return etDlgAddWrd;
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
        private WordDefEditor editor;
        private  LinearLayout pnlWrdDefVrt;
        private String setName;
        private String appearance= new SPEditor().getValue(getContext(), SPEditor.APPEARANCE);

        public BtnAddWrdDefLstnr(FragmentDialogAddWrdDef dialog,String setName, LinearLayout pnlWrdDefVrt) {
            this.dialog=dialog;
            this.pnlWrdDefVrt=pnlWrdDefVrt;
            this.setName=setName;
            this.editor=new WordDefEditor(getContext(), setName);
        }

        @Override
        public void onClick(View view) {
            String wrdStr = ((EditText) view.getRootView().findViewById(R.id.etDlgAddWrd)).getText().toString();
            String defStr= ((EditText) view.getRootView().findViewById(R.id.etDlgAddDef)).getText().toString();
            int ptsDefault = 10;

            if (isEmpty(wrdStr))
                Toast.makeText(view.getContext(), "PLEASE INPUT WORD!", Toast.LENGTH_SHORT).show();

            else {
                getWord().setWrd(wrdStr);
                getWord().setDef(defStr);

                new EditorFragmentWordDef(getContext()).add(word.getWrd(), new WordOperator().convert2Json(getWord()));

                new EditorOperator().getUiEditor(getContext(), setName).updateScreen();
            }
            dialog.dismiss();
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

