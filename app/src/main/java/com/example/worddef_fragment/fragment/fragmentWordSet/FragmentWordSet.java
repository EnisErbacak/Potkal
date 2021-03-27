package com.example.worddef_fragment.fragment.fragmentWordSet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragment.fragmentWordSet.editor.UiEdtrWrdSet;
import com.example.worddef_fragment.fragment.fragmentWordSet.elements.btn.BtnMenuTop;

public class FragmentWordSet extends Fragment
{
    private Context context;
    private ProgressBar pBarWordSet;
    private BtnMenuTop btnMenuTop;
    public static int ORDER_BY=0;
    private LinearLayout panelMainVrt;

    public static FragmentWordSet getInstance() {
        return new FragmentWordSet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_word_set,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        panelMainVrt=getView().findViewById(R.id.pnlWrdSetMain);
        pBarWordSet=getView().findViewById(R.id.pBarWordSet);
        btnMenuTop =getView().findViewById(R.id.btnMenuTop);
        btnMenuTop.setPBar(pBarWordSet);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=getContext();

        new UiEdtrWrdSet(context).updateScrn(ORDER_BY);
    }
}
