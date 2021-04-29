package com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view;

import android.content.Context;


public class TvWordCount extends SuperTvRght {

    private int wrdCount;
    public TvWordCount(Context context, int wordCnt) {
        super(context,String.valueOf(wordCnt));
        this.wrdCount=wordCnt;
    }
}
