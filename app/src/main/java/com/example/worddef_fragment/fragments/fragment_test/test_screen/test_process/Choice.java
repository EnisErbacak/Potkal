package com.example.worddef_fragment.fragments.fragment_test.test_screen.test_process;

import android.graphics.Color;
import android.widget.TextView;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_test.test_screen.views.buttons.ButtonChoice;

public class Choice {
    private String str;
    private int btnNo, defNo;
    private boolean isSelected, isCorrect, isSelectable=true;
    private ButtonChoice buttonChoice;
    private TextView tvScoreCorrect, tvScoreIncorrect;
    private TestScreen testScreen;

    public Choice(TestScreen testScreen) {
        this.defNo=-1;
        this.testScreen=testScreen;
    }

    public ButtonChoice getButtonChoice() {
        return buttonChoice;
    }

    public void setButtonChoice(ButtonChoice buttonChoice) {
        this.buttonChoice = buttonChoice;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getBtnNo() {
        return btnNo;
    }

    public void setBtnNo(int btnNo) {
        this.btnNo = btnNo;
    }

    public int getDefNo() {
        return defNo;
    }

    public void setDefNo(int defNo) {
        this.defNo = defNo;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setTvScoreCorrect(TextView tvScoreCorrect) {
        this.tvScoreCorrect = tvScoreCorrect;
    }

    public void setTvScoreIncorrect(TextView tvScoreIncorrect) {
        this.tvScoreIncorrect = tvScoreIncorrect;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }

    public void mark() {
            if (isSelected()) {
                if (isCorrect()) buttonChoice.setBackgroundColor(Color.GREEN);
                else buttonChoice.setBackgroundColor(Color.RED);
            }
         else {
            buttonChoice.setBackgroundColor(Integer.parseInt(new SPEditor().getValue(buttonChoice.getContext(), SPEditor.COL_TEST_CHOICE_BG)));
        }
    }

    public void select() {
        if(isSelectable()) {
            testScreen.makeAllUnselectable();
            setSelected(true);
            if (isCorrect) {
                testScreen.reDraw();
                tvScoreCorrect.setText(String.valueOf(Integer.parseInt(tvScoreCorrect.getText().toString()) + 1));
            } else {
                testScreen.reDraw();
                tvScoreIncorrect.setText(String.valueOf(Integer.parseInt(tvScoreIncorrect.getText().toString()) + 1));
            }
            testScreen.showCorrect();
        }
    }

    public void draw() {
        buttonChoice.setChoice(Choice.this);
        buttonChoice.setText(str);
        mark();
    }
}