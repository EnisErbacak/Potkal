package com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.popup.PopupWordSetEdit;
import com.example.worddef_fragment.fragments.fragment_worddef.FragmentWordDef;

public class TvWordsetLeft extends androidx.appcompat.widget.AppCompatTextView {
    private String setName;
    private boolean attachListener;

    private final int COL_TXT = Color.parseColor("#111F29");
    //private final int SIZE_TXT=pix2Dip(10);
    private final int SIZE_TXT = 28; // IN SP

    private Context context;


    public TvWordsetLeft(Context context, String setName, boolean attachListener) {
        super(context);
        this.setName = setName;
        this.context = context;
        this.attachListener=attachListener;
        onCreate();
    }

    void onCreate() {
        setStyle();
        setConditions(TvWordsetLeft.this);
        setText(setName);
    }

    // SETS VISUAL FEATURES
    void setStyle() {
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setTextColor(COL_TXT);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, getTxtSize());
        setTypeface(getTypeface(), Typeface.BOLD);
        setSingleLine(false);
    }

    //SETS BACKGROUND THINGS
    void setConditions(View view) {
        if (attachListener) {
            view.setOnClickListener(new TxtViewWrdSetLngClckLstnr());
            setOnLongClickListener(new TxtViewWrdSetLngLstnr());
        }

    }

    public String getWordsetName() {
        return setName;
    }

    private int getTxtSize() {
        return Integer.valueOf(new SPEditor().getValue(context, SPEditor.SET_TXT_SIZE));
    }

    private class TxtViewWrdSetLngClckLstnr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentWordDef(setName)).commit();
        }
    }

    private class TxtViewWrdSetLngLstnr implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            new PopupWordSetEdit(context, view).show();
            return false;
        }
    }
}
