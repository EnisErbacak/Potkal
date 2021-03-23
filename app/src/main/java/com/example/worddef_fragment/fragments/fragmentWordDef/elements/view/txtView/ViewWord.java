package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.txtView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.popupMenu.PopupEditWordDef;
import com.example.worddef_fragment.other.PixelConverter;

public class ViewWord extends androidx.appcompat.widget.AppCompatTextView {
    private String word, setName;
    private Context context;

    private final int WDTH = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGT = ViewGroup.LayoutParams.WRAP_CONTENT;

    private int COL_BG = Color.parseColor("#dcdcdc");

    private final int COL_TXT = Color.BLACK;
    private final int SIZE_TXT = PixelConverter.pix2Sp(getContext(), 8);

    public ViewWord(@NonNull Context context, String word, String setName) {
        super(context);
        this.context=context;
        this.word = word;
        this.setName = setName;
        onCreate();
    }

    void onCreate() {

        this.setId(generateViewId());
        setText(word);
        setView();
        setOnLongClickListener(new ViewWordLongPressListener());
    }

    void setView() {
        this.setLayoutParams(new LinearLayout.LayoutParams(WDTH, HGT));
        setSingleLine();
        setTxtStyle();
    }

    private void setTxtStyle() {
        setTextSize(getTxtSize());
        setTextColor(COL_TXT);
        setTypeface(getTypeface(), Typeface.BOLD);
    }

    private int getTxtSize() {
        return Integer.valueOf(new SPEditor().getValue(context, SPEditor.WORD_TXT_SIZE));
    }


    private class ViewWordLongPressListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View view) {
            PopupEditWordDef menu = new PopupEditWordDef(context, (ViewWord) view, setName);
            menu.show();
            return false;
        }
    }
}
