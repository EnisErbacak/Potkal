package com.example.worddef_fragment.fragments.processes.operator;

import android.content.Context;

public class FragmentOperatorFactory {
    public FragmentOperator create(String type, Context context) {
        FragmentOperator result;
        switch (type) {
            case "wordset":
                result=new WordSetOperator(context);
                break;
            case "worddef":
                result=new WordDefOperator(context);
                break;
            default: result=null;
        }
        return  result;
    }
}
