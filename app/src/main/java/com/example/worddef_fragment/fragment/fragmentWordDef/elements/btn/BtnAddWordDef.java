package com.example.worddef_fragment.fragment.fragmentWordDef.elements.btn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragment.fragmentWordDef.dialog.dialog_fragments.FragmentDialogAddWrdDef;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnAddWordDef extends FloatingActionButton {
    private Context context;
    private String setName;

    public BtnAddWordDef(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        onCreate();
    }

    public void onCreate() {
        setCondition();
    }

    private void setCondition() {
        setOnClickListener(new BtnAddWordDefListener((FragmentActivity) context));
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    private class BtnAddWordDefListener implements View.OnClickListener {
        private final FragmentActivity fragmentActivity;
        private LinearLayout pnlWordDefVrt;

        public BtnAddWordDefListener(FragmentActivity fragmentActivity) {
            this.fragmentActivity = fragmentActivity;
        }

        @Override
        public void onClick(View view) {
            pnlWordDefVrt = view.getRootView().findViewById(R.id.pnlWordDefVrt);
            new FragmentDialogAddWrdDef(pnlWordDefVrt, setName).show(fragmentActivity.getSupportFragmentManager(), "ADD NEW WORD");
        }
    }
}


