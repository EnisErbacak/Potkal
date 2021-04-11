package com.example.worddef_fragment.fragments.fragment_worddef.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_worddef.FragmentWordDef;
import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.FragmentDialogAddWrdDef;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnAddWorddef extends FloatingActionButton {
    private Context context;

    public BtnAddWorddef(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        onCreate();
    }

    public void onCreate() {
        setCondition();
        setStyle();
    }

    private void setCondition() {
        setOnClickListener(new BtnAddWordDefListener());
    }

    private void setStyle() {
        setSupportBackgroundTintList(ColorStateList.valueOf(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_WORDDEF_BTN_BG))));
    }

    private class BtnAddWordDefListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            LinearLayout pnlWordDefVrt = view.getRootView().findViewById(R.id.pnlWordDefVrt);
            new FragmentDialogAddWrdDef(pnlWordDefVrt, FragmentWordDef.setName).show(((FragmentActivity)context).getSupportFragmentManager(), "ADD NEW WORD");
        }
    }
}


