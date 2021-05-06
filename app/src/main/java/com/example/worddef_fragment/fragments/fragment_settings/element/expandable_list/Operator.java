package com.example.worddef_fragment.fragments.fragment_settings.element.expandable_list;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.element.containers.ClColorContainer;
import com.example.worddef_fragment.fragments.fragment_settings.element.containers.ClSpinnerContainer;
import com.example.worddef_fragment.fragments.fragment_settings.element.containers.ClSubTitleContainer;
import com.example.worddef_fragment.fragments.fragment_settings.sections.appearance.SpinnerAppearance;
import com.example.worddef_fragment.fragments.fragment_settings.sections.lang.SpinnerLang;
import com.example.worddef_fragment.fragments.fragment_settings.sections.other.SpinnerOther;
import com.example.worddef_fragment.fragments.fragment_settings.sections.txt_size.SpnrTxtSize;

import java.util.ArrayList;
import java.util.HashMap;

public class Operator {
    HashMap<String, ArrayList<ConstraintLayout>> mainList;

    ArrayList<ConstraintLayout> clTxtSizeList;
    ArrayList<ConstraintLayout> clAppearanceList;
    ArrayList<ConstraintLayout> llLangList;
    ArrayList<ConstraintLayout> clColorList;
    ArrayList<ConstraintLayout> otherList;
    ArrayList<ConstraintLayout> testSettings;

    Context context;

    public Operator(Context context) {
        this.context=context;
        mainList=new HashMap<>();
        clTxtSizeList =new ArrayList<>();
        clAppearanceList =new ArrayList<>();
        llLangList=new ArrayList<>();
        clColorList=new ArrayList<>();
        otherList=new ArrayList<>();
        testSettings=new ArrayList<>();

        createContent();
    }

    private void createContent() {
        String str_set=context.getResources().getString(R.string.set);
        String str_def=context.getResources().getString(R.string.definition);
        String str_bg=context.getResources().getString(R.string.background);
        String str_btn=context.getResources().getString(R.string.button);
        String str_txt=context.getResources().getString(R.string.text);
        String str_settings=context.getResources().getString(R.string.settings);
        String str_topbar=context.getResources().getString(R.string.status_bar);
        String str_dialog=context.getResources().getString(R.string.dialog);

        clTxtSizeList.add(new ClSpinnerContainer(context,  "\""+ context.getResources().getString(R.string.set) +"\" " + context.getResources().getString(R.string.text_size) //"'Set' Text Size"
                                        ,new SpnrTxtSize(context, SPEditor.TXT_SIZE_SET)));

        clTxtSizeList.add(new ClSpinnerContainer(context, "\""+ context.getResources().getString(R.string.word) +"\" " + context.getResources().getString(R.string.text_size)
                                        ,new SpnrTxtSize(context, SPEditor.TXT_SIZE_WORD)));

        clTxtSizeList.add(new ClSpinnerContainer(context,  "\""+ context.getResources().getString(R.string.definition) +"\" " + context.getResources().getString(R.string.text_size)
                ,new SpnrTxtSize(context,SPEditor.TXT_SIZE_DEF)));

        clTxtSizeList.add(new ClSpinnerContainer(context, context.getResources().getString(R.string.question_txt_size), new SpnrTxtSize(context, SPEditor.TXT_SIZE_TEST_QUESTION)));
        clTxtSizeList.add(new ClSpinnerContainer(context, context.getResources().getString(R.string.choice_txt_size), new SpnrTxtSize(context, SPEditor.TXT_SIZE_TEST_CHOICE)));

        mainList.put(context.getResources().getString(R.string.text_size)    // TEXT OF EXPANDABLE LIST GROUP TITLE
                , clTxtSizeList);

        clAppearanceList.add(new ClSpinnerContainer(context, context.getResources().getString(R.string.appearance), new SpinnerAppearance(context, SPEditor.APPEARANCE)));
        llLangList.add(new ClSpinnerContainer(context,context.getResources().getString(R.string.language), new SpinnerLang(context, SPEditor.APP_LANG)));

        ///WORDSET/////////////////////////////////////////////////////////////////////////////////////////
        clColorList.add(new ClSubTitleContainer(context, str_set));
        clColorList.add(new ClColorContainer(context,str_set +" "+str_topbar, SPEditor.COL_WORDSET_STATUSBAR)); // WORDSET TOP BAR
        clColorList.add(new ClColorContainer(context,str_set +" "+str_topbar+" "+str_txt, SPEditor.COL_WORDSET_STATUSBAR_TXT)); // WORDSET TOP BAR TXT

        clColorList.add(new ClColorContainer(context,str_set, SPEditor.COL_WORDSET)); // WORDSET
        clColorList.add(new ClColorContainer(context,str_set +" "+str_bg, SPEditor.COL_WORDSET_BG)); // WORDSET BG
        clColorList.add(new ClColorContainer(context,str_set +" "+str_btn+" "+str_bg, SPEditor.COL_WORDSET_BTN_BG)); // WORDSET BTN BG
        clColorList.add(new ClColorContainer(context,str_set +" "+str_txt, SPEditor.COL_WORDSET_TXT)); // WORDSET TXT


        ///WORDDEF/////////////////////////////////////////////////////////////////////////////////////////
        clColorList.add(new ClSubTitleContainer(context, str_def));
        clColorList.add(new ClColorContainer(context,str_def +" "+str_topbar+" "+str_txt, SPEditor.COL_WORDDEF_STATUSBAR_TXT)); // WORDDEF TOP BAR TXT
        clColorList.add(new ClColorContainer(context,str_def, SPEditor.COL_WORDDEF));// WORDDEF
        clColorList.add(new ClColorContainer(context,str_def +" "+str_bg, SPEditor.COL_WORDDEF_BG));//WORDDEF BG
        clColorList.add(new ClColorContainer(context,str_def +" "+str_btn+" "+str_bg, SPEditor.COL_WORDDEF_BTN_BG)); // WORDSET BTN BG
        clColorList.add(new ClColorContainer(context,str_def +" "+str_txt, SPEditor.COL_WORDDEF_TXT)); // WORDSET TXT
        clColorList.add(new ClColorContainer(context,str_def +" "+str_topbar, SPEditor.COL_WORDDEF_STATUSBAR)); // WORDSET STATUS BAR

        ///SETTINGS/////////////////////////////////////////////////////////////////////////////////////////
        clColorList.add(new ClSubTitleContainer(context, str_settings));
        clColorList.add(new ClColorContainer(context,str_settings+" "+str_bg, SPEditor.COL_SETTINGS_BG)); // SETTINGS BG
        clColorList.add(new ClColorContainer(context,str_settings+" "+str_txt, SPEditor.COL_SETTINGS_TXT)); // SETTINGS BG


        ///TEST/////////////////////////////////////////////////////////////////////////////////////////
        clColorList.add(new ClSubTitleContainer(context, "Test"));
        clColorList.add(new ClColorContainer(context, context.getResources().getString(R.string.test_background),SPEditor.COL_TEST_BG));
        clColorList.add(new ClColorContainer(context, context.getResources().getString(R.string.score_panel_bg_col), SPEditor.COL_TEST_SCORE_BG));
        clColorList.add(new ClColorContainer(context, context.getResources().getString(R.string.question_bg_col), SPEditor.COL_TEST_QUESTION_BG));
        clColorList.add(new ClColorContainer(context, context.getResources().getString(R.string.choice_bg_col), SPEditor.COL_TEST_CHOICE_BG));

        clColorList.add(new ClColorContainer(context, context.getResources().getString(R.string.score_txt_col), SPEditor.COL_TEST_SCORE_TXT));
        clColorList.add(new ClColorContainer(context, context.getResources().getString(R.string.question_txt_col), SPEditor.COL_TEST_QUESTION_TXT));
        clColorList.add(new ClColorContainer(context, context.getResources().getString(R.string.choice_txt_col), SPEditor.COL_TEST_CHOICE_TXT));


        otherList.add(new ClSpinnerContainer(context, context.getResources().getString(R.string.duplication_check), new SpinnerOther(context, SPEditor.DUPLICATION_CHCK)));


        mainList.put(context.getResources().getString(R.string.color), clColorList);
        mainList.put(context.getResources().getString(R.string.language), llLangList);
        mainList.put(context.getResources().getString(R.string.appearance), clAppearanceList);
        mainList.put(context.getResources().getString(R.string.other), otherList);
    }

    public HashMap<String, ArrayList<ConstraintLayout>> getMainList() {
        return mainList;
    }
}

