package com.example.worddef_fragment.fragments.fragment_test.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.worddef_fragment.R;

import java.util.ArrayList;

public class FragmentTestResult extends Fragment {
    private String correct, inCorrect;
    private ArrayList<String> sets;

    private LinearLayout llTestResInner;
    private TextView tvTestResInCrrct, tvTestResCrrct;
    private Button btnTestExit;

    public FragmentTestResult(String correct, String inCorrect /* ArrayList<String> sets*/) {
        this.correct = correct;
        this.inCorrect = inCorrect;
        this.sets = sets;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_result,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llTestResInner=view.getRootView().findViewById(R.id.llTestResInner);
        tvTestResInCrrct=view.getRootView().findViewById(R.id.tvTestResInCrrct);
        tvTestResCrrct=view.getRootView().findViewById(R.id.tvTestResCrrct);
        btnTestExit=view.getRootView().findViewById(R.id.btnTestExit);

        tvTestResCrrct.setText(correct);
        tvTestResInCrrct.setText(inCorrect);
    }
}
