package com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process;

import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.FragmentTest;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process.question_creator.TestPool;

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
        question=testPool.getqList().get(questionNo);
        tvQ = clTestMain.findViewById(R.id.tvTestQuestion);

        choiceArr=new Choice[]{new Choice(TestScreen.this), new Choice(TestScreen.this), new Choice(TestScreen.this), new Choice(TestScreen.this)};
        this.questionNo=questionNo;

        onCreate();
    }

    public void show() {
        reDraw();
    }

    private void onCreate() {
        tvQ.setText(testPool.getqList().get(questionNo));

        generateRandoms2(testPool.getChoiceList().size()-1);

        attachChoiceStr();
        setChoiceBtnNo();

        setCorrectChoice();

        attachBtn2Choice();
    }

    // Generates random numbers as count passed by in a range.
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

    // Generates random number in a range.
    private int getRandom(int max) {
        return new Random().nextInt(max+1);
    }

    // Sets the buttons texts.
    private void attachChoiceStr(){
        for(Choice ch:choiceArr) {
            ch.setStr(testPool.getChoiceList().get(ch.getDefNo()));
        }
    }

    // Makes placements of the the buttons
    private void setChoiceBtnNo() {
        for(int i=0;i<choiceArr.length;i++) {
            choiceArr[i].setBtnNo(i);
        }
    }

    // Selects a random number and sets it as a correct choice
    private void setCorrectChoice() {
        int randomInt=getRandom(3);
        choiceArr[randomInt].setStr(testPool.getChoiceList().get(questionNo));
        choiceArr[randomInt].setCorrect(true);
    }

    // Attaches choice objects to the buttons.
    private void attachBtn2Choice() {
        for(Choice ch:choiceArr) {
            fragmentTest.getBtnChArr()[ch.getBtnNo()].setChoice(ch);
            ch.setTvScoreCorrect(fragmentTest.getTvTestScoreCorrect());
            ch.setTvScoreIncorrect(fragmentTest.getTvTestScoreIncorrect());
            ch.setButtonChoice(fragmentTest.getBtnChArr()[ch.getBtnNo()]);
        }
    }

    // Marks the touched buttons red or green.
    protected void reDraw() {
        tvQ.setText(testPool.getqList().get(questionNo));
        for(Choice ch:choiceArr) {
            ch.draw();
        }
    }

    // Makes the other buttons unselectable/touchable if a button touched/selected.
    public void makeAllUnselectable() {
        for(Choice ch: choiceArr) {
            //ch.setSelected(true);
            ch.setSelectable(false);
        }
    }

    protected void showCorrect(){
        for(Choice ch: choiceArr) {
            //ch.setSelected(true);
            ch.setSelectable(false);
            if(ch.isCorrect()) ch.setSelected(true);
            ch.draw();
        }
    }

    public Choice[] getChoiceArr() {
        return choiceArr;
    }

    public String getQuestion() {
        return question;
    }
}