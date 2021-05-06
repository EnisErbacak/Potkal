package com.example.worddef_fragment.fragments.fragment_worddef;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;

public class FragmentWordDef extends Fragment
{
    //********REFACTORED
    private TextView tvWrdDefTop;
    private ConstraintLayout clMainWorddef;
    private ScrollView svMainWorddef;
    private TextView tvWorddefTop;

    public static int ORDER_BY=0;

    public static String setName;

    public FragmentWordDef(String setName)
    {
        this.setName=setName;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvWrdDefTop=getView().findViewById(R.id.tvWrdDefTop);
        tvWrdDefTop.setText(setName);
        tvWrdDefTop.setTextColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDDEF_STATUSBAR_TXT)));

        clMainWorddef=view.findViewById(R.id.clMainWorddef);
        svMainWorddef=view.findViewById(R.id.svMainWorddef);
        setStyle();
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
        new BuilderEditor().getUiEditor(getContext(), setName).updateScreen();
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new KeyLstnrFragWrd());
    }

    private void setStyle() {
        SPEditor spEditor=new SPEditor();
        svMainWorddef.setBackgroundColor(Integer.parseInt(spEditor.getValue(getContext(), SPEditor.COL_WORDDEF_BG)));
        clMainWorddef.setBackgroundColor(Integer.parseInt(spEditor.getValue(getContext(), SPEditor.COL_WORDDEF_STATUSBAR)));
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
                ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, FragmentWordSet.getInstance()).commit();
                onDestroy();
                return true;
            }
            return false;
        }
    }
}