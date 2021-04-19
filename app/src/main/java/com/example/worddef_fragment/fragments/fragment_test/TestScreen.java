package com.example.worddef_fragment.fragments.fragment_test;

import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_test.Choice;
import com.example.worddef_fragment.fragments.fragment_test.FragmentTest;
import com.example.worddef_fragment.fragments.fragment_test.question_creator.TestPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestScreen {
    private int questionNo;
    private ConstraintLayout clTestMain;
    private TextView tvQ;

    private TestPool testPool;
    private FragmentTest fragmentTest;
    private String question;
    private int screenNo;
    private Choice[] choiceArr;


    public TestScreen(ConstraintLayout clTestMain, TestPool testPool, int questionNo, FragmentTest fragmentTest) {
        this.clTestMain=clTestMain;
        this.testPool=testPool;
        this.questionNo=questionNo;
        this.fragmentTest=fragmentTest;
        screenNo=questionNo;
        question=testPool.getWordList().get(questionNo);
        tvQ = clTestMain.findViewById(R.id.tvTestQuestion);

        choiceArr=new Choice[]{new Choice(TestScreen.this), new Choice(TestScreen.this), new Choice(TestScreen.this), new Choice(TestScreen.this)};
        this.questionNo=questionNo;

        onCreate();
    }

    public void show() {
        reDraw();
    }

    private void onCreate() {
        tvQ.setText(testPool.getWordList().get(questionNo));

        //generateRandoms(testPool.getDefList().size()-1);
        generateRandoms2(testPool.getDefList().size()-1);

        attachChoiceStr();
        setChoiceBtnNo();

        setCorrectChoice();

        attachBtn2Choice();

    }

    public void generateRandoms2(int max) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<=max; i++) {
            if(i==questionNo) continue;
            list.add(i);
        }

        Collections.shuffle(list);
        for (int i=0; i<choiceArr.length; i++) {
            choiceArr[i].setDefNo(list.get(i));
        }
    }

    private int getRandom(int max) {
        return new Random().nextInt(max+1);
    }

    private void attachChoiceStr(){
        for(Choice ch:choiceArr) {
            ch.setStr(testPool.getDefList().get(ch.getDefNo()));
        }
    }

    private void setChoiceBtnNo() {
        /*
        for(Choice ch:choiceArr) {
            ch.setBtnNo(getRandom(3));
        }
         */
        for(int i=0;i<choiceArr.length;i++) {
            choiceArr[i].setBtnNo(i);
        }
    }

    private void setCorrectChoice() {
        int randomInt=getRandom(3);
        choiceArr[randomInt].setStr(testPool.getDefList().get(questionNo));
        choiceArr[randomInt].setCorrect(true);
    }

    private void attachBtn2Choice() {
        for(Choice ch:choiceArr) {
            fragmentTest.getBtnChArr()[ch.getBtnNo()].setChoice(ch);
            ch.setTvScoreCorrect(fragmentTest.getTvTestScoreCorrect());
            ch.setTvScoreIncorrect(fragmentTest.getTvTestScoreIncorrect());
            ch.setButtonChoice(fragmentTest.getBtnChArr()[ch.getBtnNo()]);
        }
    }

    private void reDraw() {
        //attachBtn2Choice();
        tvQ.setText(testPool.getWordList().get(questionNo));
        for(Choice ch:choiceArr) {
            //fragmentTest.getBtnChArr()[ch.getBtnNo()].setChoice(ch);
            ch.draw();
        }
    }


    public void makeAllUnselectable() {
        for(Choice ch: choiceArr) {
            ch.setSelected(true);
        }
    }

    public Choice[] getChoiceArr() {
        return choiceArr;
    }

    public String getQuestion() {
        return question;
    }
}