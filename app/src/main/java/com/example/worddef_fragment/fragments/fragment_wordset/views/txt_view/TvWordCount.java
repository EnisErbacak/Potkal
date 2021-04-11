package com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view;

import android.content.Context;

import com.example.worddef_fragment.R;

public class TvWordCount extends SuperTvRght {

    private int wrdCount;
    public TvWordCount(Context context, int wordCnt) {
        super(context,context.getResources().getString(R.string.word_count) +": "+wordCnt);
        this.wrdCount=wordCnt;
    }
}
