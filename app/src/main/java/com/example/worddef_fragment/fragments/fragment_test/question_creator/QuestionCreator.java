package com.example.worddef_fragment.fragments.fragment_test.question_creator;

import android.content.Context;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class QuestionCreator {
    ArrayList<String> wordList, defList;
    ArrayList<Integer> orderList;
    Context context;

    public QuestionCreator(Context context) {
        this.context = context;
        wordList=new ArrayList<>();
        defList=new ArrayList<>();
        orderList=new ArrayList<>();
    }

    public TestPool createTestPool(ArrayList<String> setNameList) {
        TestPool testPool =new TestPool();
        createPool(setNameList);
        testPool.setDefList(defList);
        testPool.setOrderList(orderList);
        testPool.setWordList(wordList);
        return testPool;
    }

    private ArrayList<Integer> createPool(ArrayList<String> setNameList) {
        ArrayList<JSONObject> jObjList=getPairsJ(setNameList);

        try {
            int order=0;
            for(int i=0;i<jObjList.size();i++) {
                JSONObject jTemp=jObjList.get(i);

                Iterator<String> keys=jObjList.get(i).keys();
                while (keys.hasNext()) {
                    String key=keys.next();

                    wordList.add(key);
                    defList.add(jTemp.getJSONObject(key).getString("def"));
                    orderList.add(order);
                    order++;
                }
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return orderList;
    }

    private ArrayList<JSONObject> getPairsJ(ArrayList<String> setNameList) {
        ArrayList<JSONObject> jObjList=new ArrayList<>();
        FileManager fileManager=new FileManager();
        for(int i=0;i<setNameList.size();i++) {
            try {
                jObjList.add(new JSONObject(fileManager.operate().read(new PathPickerFactory().create("wordset").get(context) + File.separator+ setNameList.get(i))));
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        return jObjList;
    }
}