package com.example.worddef_fragment.fragments.fragmentWordDef;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.editor.word_def.WordDefEditor;
import com.example.worddef_fragment.fragments.fragmentWordDef.editor.ui.operator.EditorOperator;
import com.example.worddef_fragment.fragments.fragmentWordSet.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.btn.BtnAddWordDef;

public class FragmentWordDef extends Fragment
{
    //********REFACTORED
    private WordDefEditor editor;
    private BtnAddWordDef btnAddWord;
    private LinearLayout pnlWordDefVrt;
    private TextView tvWrdDefTop;

    public static int ORDER_BY=0;

    public static String setName;

    public static FragmentWordDef newInstance(String setName) {
        return new FragmentWordDef(setName);
    }
    private FragmentWordDef(String setName)
    {
        this.setName=setName;
    }

    public WordDefEditor getEditor() {
        return editor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.editor=new WordDefEditor(view.getContext(),setName);
        btnAddWord=view.findViewById(R.id.btnWordDefAddWord);
        btnAddWord.setSetName(setName);
        pnlWordDefVrt=getView().findViewById(R.id.pnlWordDefVrt);
        tvWrdDefTop=getView().findViewById(R.id.tvWrdDefTop);
        tvWrdDefTop.setText(setName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word_def,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCondition(getView());
        //new UiEdtrWrdDef(getContext(),setName).updateScrn(ORDER_BY);
        new EditorOperator().getUiEditor(getContext(), setName).updateScreen();
        //editor.buildByCrtdDateDsc();
    }

    // Returns the panel that is necessary and top parent for the fragment.
    private LinearLayout getPanelParent(View view) {
        return view.findViewById(R.id.pnlWordDefVrt);
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new KeyLstnrFragWrd());
    }

    private FragmentActivity getFragmentActivity(View view) {
        return (FragmentActivity) view.getContext();
    }

    private FragmentManager getFragmentManager(View view) {
        return ((FragmentActivity)getFragmentActivity(view)).getSupportFragmentManager();
    }

    public static String getSetName() {
        return setName;
    }

    @Override
    public void onDestroy() {
        setName=null;
        super.onDestroy();
    }

    private class KeyLstnrFragWrd implements View.OnKeyListener {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if(keyCode==KeyEvent.KEYCODE_BACK) {
                getFragmentManager(view).beginTransaction().replace(R.id.containerActivityMain, FragmentWordSet.getInstance()).commit();
                onDestroy();
                return true;
            }
            return false;
        }
    }
}