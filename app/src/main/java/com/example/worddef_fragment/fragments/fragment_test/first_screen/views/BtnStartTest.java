package com.example.worddef_fragment.fragments.fragment_test.first_screen.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.fragments.fragment_test.first_screen.views.container.ContainerTestWordset;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.FragmentTest;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process.question_creator.QuestionCreator;
import com.example.worddef_fragment.reaction.Reaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class BtnStartTest extends androidx.appcompat.widget.AppCompatButton {

    private Context context;
    public BtnStartTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        onCreate();
    }

    private void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout ll=view.getRootView().findViewById(R.id.llTestFirstSets);
                RadioGroup rdGrpQType=view.getRootView().findViewById(R.id.rdGrpQType);

                // getSetNames(ll).size must be greater than 4 at least
                Reaction reaction=new Reaction(context);
                if(getSetNames(ll).size()==0) reaction.showShort(getContext().getResources().getString(R.string.pls_choose_set));
                else if(! checkSet(ll)) reaction.showShort(context.getResources().getString(R.string.pls_choose_safe_set));
                else ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentTest(getSetNames(ll), getQType(rdGrpQType.getCheckedRadioButtonId()))).commit();
            }
        });
    }

    private ArrayList<String> getSetNames(LinearLayout ll) {
        ArrayList<String> names=new ArrayList<>();
        for(int i=0;i<ll.getChildCount();i++) {
            ContainerTestWordset containerTestWordset= (ContainerTestWordset) ll.getChildAt(i);
            if(containerTestWordset.getCheckBox().isChecked()) names.add(containerTestWordset.getSetName());
        }
        return names;
    }

    private boolean checkSet(LinearLayout ll) {
            ArrayList<JSONObject> jObjList=getPairsJ(getSetNames(ll));
            int keyValPair=0; // Number of the word - definition pairs.


            for(int i=0;i<jObjList.size();i++) {
                JSONObject jTemp=jObjList.get(i);

                Iterator<String> keys=jObjList.get(i).keys();
                while (keys.hasNext()) {
                    String key=keys.next();
                    try {
                       jTemp.getJSONObject(key).getString("def");
                       keyValPair++;
                    }catch (JSONException jsonException) {
                        //jsonException.printStackTrace();
                        continue;
                    }
                }
                System.out.println("asdfasdfa");
            }
         boolean result= keyValPair>4? true:false;
            return result;
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



    private int getQType(int id) {
        int result=0;
        switch (id) {
            case R.id.rdBtnQWrdDef:
                result= QuestionCreator.WORD_IS_QUESTION;
                break;
            case R.id.rdBtnQDefWord:
                result= QuestionCreator.DEFINITION_IS_QUESTION;
                break;
        }
        return result;
    }
}