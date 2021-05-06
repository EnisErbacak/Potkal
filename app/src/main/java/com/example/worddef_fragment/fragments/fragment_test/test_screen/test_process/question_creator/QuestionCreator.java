package com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process.question_creator;

import android.content.Context;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class QuestionCreator {
    ArrayList<String> qList, choiceList;
    //ArrayList<Integer> orderList;
    Context context;

    public static final int WORD_IS_QUESTION=0;
    public static final int DEFINITION_IS_QUESTION=1;

    private int questionChoiceType;

    public QuestionCreator(Context context, int questionChoiceType) {
        this.context = context;
        qList =new ArrayList<>();
        choiceList =new ArrayList<>();
      //  orderList=new ArrayList<>();
        this.questionChoiceType=questionChoiceType;
    }

    public TestPool createTestPool(ArrayList<String> setNameList) {
        TestPool testPool =new TestPool();
        createPool(setNameList);

        testPool.setChoiceList(choiceList);
        //testPool.setOrderList(orderList);
        testPool.setqList(qList);
        return testPool;
    }

    private void setQuestionChoicePair(String key, String value) {
        switch (questionChoiceType) {
            case WORD_IS_QUESTION:
                    qList.add(key);
                    choiceList.add(value);
                break;

            case DEFINITION_IS_QUESTION:
                qList.add(value);
                choiceList.add(key);
                break;
        }
    }

    private void createPool(ArrayList<String> setNameList) {
        ArrayList<JSONObject> jObjList=getPairsJ(setNameList);

            int order=0;
            for(int i=0;i<jObjList.size();i++) {
                JSONObject jTemp=jObjList.get(i);

                Iterator<String> keys=jObjList.get(i).keys();
                while (keys.hasNext()) {
                    String key=keys.next();
                    try {
                    setQuestionChoicePair(key, jTemp.getJSONObject(key).getString("def"));
                    order++;
                }catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                        continue;
                    }
            }
            System.out.println("asdfasdfa");
        }
    }

    private ArrayList<JSONObject> getPairsJ(ArrayList<String> setNameList) {
        ArrayList<JSONObject> jObjList=new ArrayList<>();
        FileManager fileManager=new FileManager();
        for(int i=0;i<setNameList.size();i++) {
            try {
                jObjList.add(new JSONObject(fileManager.operate().read(new PathPicker(context).get(PathPicker.WORDSET) + File.separator+ setNameList.get(i))));
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        return jObjList;
    }
}