package com.example.worddef_fragment.fragment.fragmentWordDef.elements.view.txtView;

import android.content.Context;
import android.text.Html;

public class TvExmp extends TvWrdDef {
    public TvExmp(Context context,String exmp) {
        super(context);
        setText(Html.fromHtml(getItalic(exmp)));
    }
}
