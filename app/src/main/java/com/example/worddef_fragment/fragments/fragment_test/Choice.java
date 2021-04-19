package com.example.worddef_fragment.fragments.fragment_test;

import android.graphics.Color;
import android.widget.TextView;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_test.views.ButtonChoice;

public class Choice {
    private String str;
    private int btnNo, defNo;
    private boolean isSelected, isCorrect;
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

    public void mark() {
        if(isSelected()) {
            if(isCorrect()) buttonChoice.setBackgroundColor(Color.GREEN);
            else buttonChoice.setBackgroundColor(Color.RED);
        } else {
            buttonChoice.setBackgroundColor(buttonChoice.getContext().getResources().getColor(R.color.choice_tint));
        }
    }

    public void select() {
        if( isSelected==false) {
            testScreen.makeAllUnselectable();
            if (isCorrect) {
                buttonChoice.setBackgroundColor(Color.GREEN);
                tvScoreCorrect.setText(String.valueOf(Integer.parseInt(tvScoreCorrect.getText().toString()) + 1));
            } else {
                buttonChoice.setBackgroundColor(Color.RED);
                tvScoreIncorrect.setText(String.valueOf(Integer.parseInt(tvScoreIncorrect.getText().toString()) + 1));
            }
        }
    }

    public void draw() {
        buttonChoice.setChoice(Choice.this);
        buttonChoice.setText(str);
        mark();
    }
}