package com.example.worddef_fragment.fragments.fragment_test.first_screen;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_test.FragmentListenerBackPress;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;

public class FragmentTestFirst extends Fragment {
    private LinearLayout llTestFirstSets, llTestFirstUpper, llTestFirstLower;
    private ConstraintLayout clTestFirstMain;
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
        llTestFirstSets =view.findViewById(R.id.llTestFirstSets);
        new CreateFirstTestScreen().create(getContext(), llTestFirstSets);

        clTestFirstMain=view.findViewById(R.id.clTestFirstMain);
        llTestFirstUpper =view.findViewById(R.id.llTestFirstUpper);
        llTestFirstLower=view.findViewById(R.id.llTestFirstLower);

        setStyle(getContext());
    }

    private void setStyle(Context context) {
        SPEditor spEditor=new SPEditor();
        clTestFirstMain.setBackgroundColor(Color.parseColor("#FF000000"));
//        llTestFirstUpper.getBackground().setColorFilter(Color.parseColor("#1565C0"), PorterDuff.Mode.SRC_ATOP);
  //      llTestFirstLower.getBackground().setColorFilter(Color.parseColor("#1565C0"), PorterDuff.Mode.SRC_ATOP);

    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new FragmentListenerBackPress(FragmentTestFirst.this, new FragmentWordSet()));
    }
}
