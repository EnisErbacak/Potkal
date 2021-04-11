package com.example.worddef_fragment.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.text.Html;

public class TvLang extends TvWrdDef {
    private String lang;
    public TvLang(Context context,String lang) {
        super(context);
        this.lang=lang;
        onCreate();
    }

    void onCreate() {
        super.onCreate();
        setText(Html.fromHtml(getItalic(lang)));
        setStyle();
    }
}
