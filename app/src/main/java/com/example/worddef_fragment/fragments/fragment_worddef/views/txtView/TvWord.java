package com.example.worddef_fragment.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.FragmentWordDef;
import com.example.worddef_fragment.fragments.fragment_worddef.popup.PopupEditWorddef;

public class TvWord extends TvWrdDef {

    private final int TXT_SIZE=getTxtSize();
    private String setName;

    private String word;
    public TvWord(@NonNull Context context, String word) {
        super(context);
        this.word=word;
        this.setName= FragmentWordDef.setName;
        onCreate();
    }

    void onCreate() {
        super.onCreate();
        setText(Html.fromHtml(getUnderlined(word)));
        setOnLongClickListener(new ViewWordLongPressListener());
    }

    void setStyle() {
        super.setStyle();
        ((LinearLayout.LayoutParams) getLayoutParams()).setMargins(0,0,getDip(5),0);

        setTextSize(TXT_SIZE);
        setTextColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDDEF_TXT)));
        setTypeface(Typeface.DEFAULT_BOLD);
    }

    private int getTxtSize() {
        return Integer.valueOf(new SPEditor().getValue(getContext(), SPEditor.TXT_SIZE_WORD));
    }

    private int getDip(int value){return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics()));}

    private class ViewWordLongPressListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            PopupEditWorddef menu = new PopupEditWorddef(getContext(), (TvWord) view, setName);
            menu.show();
            return false;
        }
    }
}
