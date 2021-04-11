package com.example.worddef_fragment.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.text.Html;

public class TvKind extends TvWrdDef {
    String kind;
    public TvKind(Context context, String kind) {
        super(context);
        this.kind=kind;
        onCreate();
    }

    void onCreate() {
        super.onCreate();
        setText(Html.fromHtml(getItalic(kind)));
    }
}