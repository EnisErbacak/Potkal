package com.example.worddef_fragment.fragments.fragment_test;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_test.question_creator.QuestionCreator;
import com.example.worddef_fragment.fragments.fragment_test.question_creator.TestPool;
import com.example.worddef_fragment.fragments.fragment_test.views.ButtonChoice;

import java.util.ArrayList;

public class FragmentTest extends Fragment {
    private ArrayList<String> setNames;
    private QuestionCreator questionCreator;
    private TestManager testManager;
    private Button btnTestPrevScrn, btnTestNextScrn;
    private ConstraintLayout clTestMain;
    TestPool testPool;
    ButtonChoice[] btnChArr;
    private TextView tvTestScoreCorrect, tvTestScoreIncorrect;
    public FragmentTest(ArrayList<String> setNames) {
        this.setNames=setNames;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCondition(getView());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStyle(getContext());
        btnTestPrevScrn=view.findViewById(R.id.btnTestPrevScrn);
        btnTestNextScrn=view.findViewById(R.id.btnTestNextScrn);
        clTestMain=view.findViewById(R.id.clTestMain);
        tvTestScoreCorrect=view.findViewById(R.id.tvTestScoreCrrct);
        tvTestScoreIncorrect=view.findViewById(R.id.tvTestScoreIncrrct);


        ButtonChoice btnCh1=view.findViewById(R.id.btnTestCh1);
        ButtonChoice btnCh2=view.findViewById(R.id.btnTestCh2);
        ButtonChoice btnCh3=view.findViewById(R.id.btnTestCh3);
        ButtonChoice btnCh4=view.findViewById(R.id.btnTestCh4);

        btnChArr=new ButtonChoice[]{btnCh1, btnCh2, btnCh3, btnCh4};


        questionCreator=new QuestionCreator(getActivity().getApplicationContext());
        testPool = questionCreator.createTestPool(setNames);
        TestManager testManager=new TestManager(getContext(), setNames, clTestMain, FragmentTest.this);
        testManager.createTest();
        testManager.startTest();

        btnTestPrevScrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testManager.prevScreen();
            }
        });
        btnTestNextScrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testManager.nextScreen();
            }
        });
    }

    public ButtonChoice[] getBtnChArr() {
        return btnChArr;
    }

    private void setStyle(Context context) {
        SPEditor spEditor=new SPEditor();
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new FragmentListenerBackPress(FragmentTest.this, new FragmentTestFirst()));
    }

    public TextView getTvTestScoreCorrect() {
        return tvTestScoreCorrect;
    }

    public TextView getTvTestScoreIncorrect() {
        return tvTestScoreIncorrect;
    }
}
