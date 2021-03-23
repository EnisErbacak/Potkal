package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView;

import android.content.Context;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TvExmp extends TvWrdDef {
    public TvExmp(Context context,String exmp) {
        super(context);
        setText(Html.fromHtml(getItalic(exmp)));
    }
}
