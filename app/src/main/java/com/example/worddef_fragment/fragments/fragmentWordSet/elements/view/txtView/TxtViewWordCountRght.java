package com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.txtView;

import android.content.Context;

public class TxtViewWordCountRght extends SuperTxtViewRght {

    private int wrdCount;
    public TxtViewWordCountRght(Context context, int wordCnt) {
        super(context,"Word Count: "+wordCnt);
        this.wrdCount=wordCnt;
    }

    public int getWrdCount() {
        return wrdCount;
    }
}
