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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.editor.EditorFragmentWordDef;
import com.example.worddef_fragment.file.editor.FragmentEditor;
import com.example.worddef_fragment.file.editor.word_def.WordDefEditor;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.data.Word;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.data.operator.WordOperator;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.ui.operator.EditorOperator;
import com.example.worddef_fragment.tdk.TdkBtnLstner;
import com.example.worddef_fragment.tdk.TdkObject;
import com.example.worddef_fragment.tdk.TdkTxtWatcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDialogChngWrdDef extends AppCompatDialogFragment implements CustomDialogFragment{

    private int MTCH_PRNT = WindowManager.LayoutParams.MATCH_PARENT;

    private Button btnCncl, btnAddWrdDef,btnDlgChngDsplyTdk;
    private BtnChngWrdDefLstner chngWrdDefLstner;

    private EditText etDlgChngWrd, etDlgChngDef,etDlgChngKind,etDlgChngLang,etDlgChngExmp;
    private ProgressBar pbTdkChng;
    private LinearLayout pnlWrdDefVrt;

    private WordOperator wordOperator;
    private EditorFragmentWordDef editorFragmentWordDef;

    private View anchor;
    private WordDefEditor editor;
    private String setName;
    private String wordStr;
    private Word word;
    private Word newWordObj;

    private TdkObject tdkObject;
    private ArrayList<String> defList;

    public FragmentDialogChngWrdDef(LinearLayout pnlWrdDefVrt, View anchor, String setName) {
        this.pnlWrdDefVrt = pnlWrdDefVrt;
        this.anchor=anchor;
        this.setName=setName;
        this.editor=new WordDefEditor(anchor.getContext(),setName);
        this.wordOperator=new WordOperator();
        this.tdkObject=new TdkObject();
    }

    // FIRST CALLED
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void setView() {
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = MTCH_PRNT;
        lp.height = MTCH_PRNT;
        getDialog().getWindow().setLayout(MTCH_PRNT, MTCH_PRNT);
    }

    private void setBckGrnd() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    //2ND CALLED
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_word_def_change, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etDlgChngWrd = view.findViewById(R.id.etDlgAddWrd);
        etDlgChngDef = view.findViewById(R.id.etDlgAddDef);
        etDlgChngExmp = view.findViewById(R.id.etDlgChngExmp);
        etDlgChngKind= view.findViewById(R.id.etDlgChngKind);
        etDlgChngLang = view.findViewById(R.id.etDlgChngLang);

        btnAddWrdDef = view.findViewById(R.id.btnDlgAddWrdSetAdd);
        btnCncl = view.findViewById(R.id.btnDlgAddWrdSetCncl);

        pbTdkChng= view.findViewById(R.id.pbTdkChng);
        btnDlgChngDsplyTdk=view.findViewById(R.id.btnDsplyTdkChng);

        btnDlgChngDsplyTdk.setOnClickListener( new TdkBtnLstner(FragmentDialogChngWrdDef.this, getTdkObject()));
        btnCncl.setOnClickListener(new BtnCnclWrdDefLstner(this));

        chngWrdDefLstner = new BtnChngWrdDefLstner(FragmentDialogChngWrdDef.this, anchor,pnlWrdDefVrt);
        btnAddWrdDef.setOnClickListener(chngWrdDefLstner);

        etDlgChngWrd.addTextChangedListener(new TdkTxtWatcher(FragmentDialogChngWrdDef.this, btnDlgChngDsplyTdk, pbTdkChng, etDlgChngDef));

        this.editorFragmentWordDef=new EditorFragmentWordDef(getContext());
        wordStr= ((TextView) anchor).getText().toString();
        word=getWord();

        setBckGrnd();
        setDefault();
    }


    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    private void setDefault() {
        etDlgChngWrd.setText(word.getWrd());
        etDlgChngDef.setText(word.getDef());
        etDlgChngLang.setText(word.getLang());
        etDlgChngKind.setText(word.getKind());
        etDlgChngExmp.setText(word.getExmp());
    }

    public Word getWord() {
        JSONObject job=editorFragmentWordDef.get(wordStr);
        try {
            job.getJSONObject(wordStr);
        }catch (JSONException je){je.printStackTrace();}
        word= wordOperator.convert2Word(editorFragmentWordDef.get(wordStr), wordStr);
        return word;
    }

    @Override
    public void setWord(Word word) {
        this.word=word;
    }

    @Override
    public EditText getEtWrd() {
    return etDlgChngWrd;
    }

    @Override
    public EditText getEtDef() {
        return  etDlgChngDef;
    }

    @Override
    public EditText getEtLang() {
        return etDlgChngLang;
    }

    @Override
    public EditText getEtKind() {
        return etDlgChngKind;
    }

    @Override
    public EditText getEtExmp() {
        return etDlgChngExmp;
    }

    public ArrayList<String> getDefList() {
        return defList;
    }

    public void setDefList(ArrayList<String> defList) {
        this.defList = defList;
    }

    @Override
    public TdkObject getTdkObject() {
        return this.tdkObject;
    }

    @Override
    public void setTdkObject(TdkObject tdkObject) {
            this.tdkObject=tdkObject;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class BtnCnclWrdDefLstner implements View.OnClickListener {
        FragmentDialogChngWrdDef dialog;
        BtnCnclWrdDefLstner(FragmentDialogChngWrdDef dialog) {
            this.dialog=dialog;
        }
        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class BtnChngWrdDefLstner implements View.OnClickListener {
        private String oldWord, newWord, newDef;
        private FragmentDialogChngWrdDef dialog;
        LinearLayout pnlWrdDefVrt;

        BtnChngWrdDefLstner(FragmentDialogChngWrdDef dialog,View anchor, LinearLayout pnlWrdDefVrt) {
            this.dialog=dialog;
            this.pnlWrdDefVrt=pnlWrdDefVrt;
            this.oldWord=((TextView) anchor).getText().toString();
        }
        @Override
        public void onClick(View view) {
            newWord=((EditText)view.getRootView().findViewById(R.id.etDlgAddWrd)).getText().toString();
            newDef=((EditText)view.getRootView().findViewById(R.id.etDlgAddDef)).getText().toString();

            if(isEmpty(newWord))
                Toast.makeText(view.getContext(),"PLEASE INPUT WORD!",Toast.LENGTH_SHORT).show();

            else {


                    //editor.change2(oldWord, newWord, newDef);
                    //new EditorOperator().getUiEditor(getContext(), setName).updateScreen();
                    //new EditorFragmentWordDef(getContext()).get(oldWord);
                    newWordObj=new Word();
                    newWordObj.setWrd(getEtWrd().getText().toString());
                    newWordObj.setLang(getEtLang().getText().toString());
                    newWordObj.setKind(getEtKind().getText().toString());
                    newWordObj.setExmp(getEtExmp().getText().toString());
                    newWordObj.setDef(getEtDef().getText().toString());

                    new EditorFragmentWordDef(getContext()).change(oldWord, newWord,new WordOperator().convert2Json(newWordObj));
                    new EditorOperator().getUiEditor(getContext(), setName).updateScreen();
                    dialog.dismiss();

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
