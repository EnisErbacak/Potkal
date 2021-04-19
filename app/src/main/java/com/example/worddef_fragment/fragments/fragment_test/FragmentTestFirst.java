package com.example.worddef_fragment.fragments.fragment_test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;

public class FragmentTestFirst extends Fragment {
    private LinearLayout llTestFirst;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_first,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCondition(getView());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llTestFirst=view.findViewById(R.id.llTestFirst);
        setStyle(getContext());
        new CreateFirstTestScreen().create(getContext(), llTestFirst);
    }

    private void setStyle(Context context) {
        SPEditor spEditor=new SPEditor();
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new FragmentListenerBackPress(FragmentTestFirst.this, new FragmentWordSet()));
    }
}
