package com.example.worddef_fragment.fragment.fragment_settings.element.expandable_list;

import android.content.Context;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragment.fragment_settings.element.layout.LlHorizontal;
import com.example.worddef_fragment.fragment.fragment_settings.element.spinner.appearance.SpinnerAppearance;
import com.example.worddef_fragment.fragment.fragment_settings.element.spinner.txt_size.SpnrTxtSize;

import java.util.ArrayList;
import java.util.HashMap;

public class Operator {
    HashMap<String, ArrayList<LlHorizontal>> mainList;
    ArrayList<LlHorizontal> txtSizeList;
    ArrayList<LlHorizontal> apperance;
    Context context;

    public Operator(Context context) {
        this.context=context;
        mainList=new HashMap<>();
        txtSizeList=new ArrayList<>();
        apperance=new ArrayList<>();
        createContent();
    }

    private void createContent() {
        txtSizeList.add(new LlHorizontal(context,  "'Set' Text Size"
                                        ,new SpnrTxtSize(context, SPEditor.SET_TXT_SIZE)));

        txtSizeList.add(new LlHorizontal(context, "'WORD' Text Size'"
                                        ,new SpnrTxtSize(context, SPEditor.WORD_TXT_SIZE)));

        txtSizeList.add(new LlHorizontal(context,  "'DEFINITION' Text Size"
                ,new SpnrTxtSize(context,SPEditor.DEF_TXT_SIZE)));

        mainList.put("TEXT SIZE"    // TEXT OF EXPANDABLE LIST GROUP TITLE
                ,txtSizeList);

        apperance.add(new LlHorizontal(context, "'Appearance'", new SpinnerAppearance(context, SPEditor.APPEARANCE)));

        mainList.put("APPEARANCE", apperance);
    }

    public HashMap<String, ArrayList<LlHorizontal>> getMainList() {
        return mainList;
    }
}

