package com.example.worddef_fragment.fragments.fragment_test;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.fragments.fragment_test.question_creator.QuestionCreator;
import com.example.worddef_fragment.fragments.fragment_test.question_creator.TestPool;

import java.util.ArrayList;
import java.util.Collections;

public class TestManager {
    ArrayList<TestScreen> testScreenList;
    private int crntScrnNo=0;
    private ArrayList<String> wordList;
    private Context context;
    private TestPool testPool;
    private ConstraintLayout clTestMain;
    private  FragmentTest fragmentTest;

    public TestManager(Context context, ArrayList<String> setNameList, ConstraintLayout clTestMain, FragmentTest fragmentTest) {
        this.context = context;
        this.clTestMain=clTestMain;
        testScreenList=new ArrayList<>();
        testPool=new QuestionCreator(context).createTestPool(setNameList);
        wordList=testPool.getWordList();
        this.fragmentTest=fragmentTest;
    }

    public void startTest() {
        testScreenList.get(0).show();
        for(TestScreen ts: testScreenList) {
            System.out.println("--------");
            System.out.println(ts.getQuestion());
            for(Choice ch: ts.getChoiceArr()) {
                System.out.print(ch.getBtnNo()+"__"+ch.getStr()+"  ");
            }
            System.out.println("--------");
        }
    }

    public void nextScreen() {
        if(crntScrnNo<=testScreenList.size()-2) {
            testScreenList.get(crntScrnNo+1).show();
            crntScrnNo++;
        }

    }

    public void prevScreen() {
        if(crntScrnNo>=1) {
            testScreenList.get(crntScrnNo-1).show();
            crntScrnNo--;
        }
    }

    public void createTest() {
        for(int i=0;i<wordList.size();i++) {
            testScreenList.add(new TestScreen(clTestMain, testPool, i, fragmentTest));
        }
        Collections.shuffle(testScreenList);
    }
}
