package com.example.worddef_fragment.fragments.fragment_test.test_screen;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_test.FragmentListenerBackPress;
import com.example.worddef_fragment.fragments.fragment_test.first_screen.FragmentTestFirst;
import com.example.worddef_fragment.fragments.fragment_test.result.FragmentTestResult;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.listener.OnSwipeTouchListener;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process.question_creator.QuestionCreator;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process.question_creator.TestPool;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process.TestManager;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.views.buttons.ButtonChoice;
import com.example.worddef_fragment.other.PixelConverter;

import java.util.ArrayList;

public class FragmentTest extends Fragment {
    private SPEditor spEditor;
    private ArrayList<String> setNames;
    private QuestionCreator questionCreator;
    private Button btnFinishTest;
    private ConstraintLayout clTestMain, clTestScore;

    private ScrollView svTestQuestion, svTestChoice;
    TestPool testPool;
    ButtonChoice[] btnChArr;
    private TextView tvTestScoreCorrect, tvTestScoreIncorrect, tvTestScoreCorrectStr, tvTestScoreIncorrectStr, tvTestQuestion;
    private int questionChoiceType;

    public FragmentTest(ArrayList<String> setNames, int questionChoiceType) {
        this.setNames=setNames;
        this.questionChoiceType=questionChoiceType;
        spEditor=new SPEditor();
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

        clTestMain=view.findViewById(R.id.clTestMain);
        clTestScore=view.findViewById(R.id.clTestScore);
        svTestChoice=view.findViewById(R.id.svTestChoice);
        svTestQuestion=view.findViewById(R.id.svTestQuestion);

        tvTestScoreCorrect=view.findViewById(R.id.tvTestScoreCorrect);
        tvTestScoreIncorrect=view.findViewById(R.id.tvTestScoreIncorrect);

        tvTestScoreCorrectStr =view.findViewById(R.id.tvTestScoreCorrectStr);
        tvTestScoreIncorrectStr =view.findViewById(R.id.tvTestScoreIncorrectStr);

        tvTestQuestion=view.findViewById(R.id.tvTestQuestion);

        btnFinishTest=view.findViewById(R.id.btnTestFinish);
        btnFinishTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentTestResult(tvTestScoreCorrect.getText().toString(), tvTestScoreIncorrect.getText().toString())).commit();
            }
        });

        ButtonChoice btnCh1=view.findViewById(R.id.btnTestCh1);
        ButtonChoice btnCh2=view.findViewById(R.id.btnTestCh2);
        ButtonChoice btnCh3=view.findViewById(R.id.btnTestCh3);
        ButtonChoice btnCh4=view.findViewById(R.id.btnTestCh4);

        btnChArr=new ButtonChoice[]{btnCh1, btnCh2, btnCh3, btnCh4};



        questionCreator=new QuestionCreator(getActivity().getApplicationContext(), questionChoiceType);
        testPool = questionCreator.createTestPool(setNames);
        TestManager testManager=new TestManager(getContext(), setNames, testPool, clTestMain, FragmentTest.this);
        testManager.createTest();
        testManager.startTest();
        setSwing(view.getContext(), testManager);

        setStyle(getContext());
    }

    public ButtonChoice[] getBtnChArr() {
        return btnChArr;
    }

    private void setStyle(Context context) {
        SPEditor spEditor=new SPEditor();
        PixelConverter pixelConverter=new PixelConverter(context);
        clTestMain.setBackgroundColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_BG)));
        clTestScore.setBackgroundColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_SCORE_BG)));
        svTestQuestion.setBackgroundColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_QUESTION_BG)));


        tvTestQuestion.setTextColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_QUESTION_TXT)));
        tvTestScoreCorrect.setTextColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_SCORE_TXT)));
        tvTestScoreIncorrect.setTextColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_SCORE_TXT)));
        tvTestScoreCorrectStr.setTextColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_SCORE_TXT)));
        tvTestScoreIncorrectStr.setTextColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_SCORE_TXT)));

        tvTestQuestion.setTextSize(Integer.parseInt(spEditor.getValue(context, SPEditor.TXT_SIZE_TEST_QUESTION)));

        for(Button btn: btnChArr) {
            btn.setBackgroundColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_CHOICE_BG)));
            btn.setTextColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_TEST_CHOICE_TXT)));
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, Integer.parseInt(spEditor.getValue(context, SPEditor.TXT_SIZE_TEST_CHOICE)));
            }

    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new FragmentListenerBackPress(FragmentTest.this, new FragmentTestFirst()));
    }

    private void setSwing(Context context, TestManager testManager) {
        clTestMain.setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                testManager.prevScreen();
            }
            public void onSwipeLeft() {
                testManager.nextScreen();
            }
            public void onSwipeBottom() {
            }

        });
    }

    public TextView getTvTestScoreCorrect() {
        return tvTestScoreCorrect;
    }

    public TextView getTvTestScoreIncorrect() {
        return tvTestScoreIncorrect;
    }
}
