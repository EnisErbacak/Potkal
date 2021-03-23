package com.example.worddef_fragment.tdk;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.worddef_fragment.fragments.fragmentWordDef.dialog.dialog_fragments.CustomDialogFragment;

public class TdkBtnLstner implements View.OnClickListener {

    private CustomDialogFragment customDialogFragment;
    private EditText txtViewDef;
    private TdkObject tdkObject;

    public TdkBtnLstner(CustomDialogFragment customDialogFragment, TdkObject tdkObject) {
        this.customDialogFragment=customDialogFragment;
        this.txtViewDef=txtViewDef;
        this.tdkObject=tdkObject;
    }

    @Override
    public void onClick(View view) {
        if(customDialogFragment.getDefList()==null) {
            Toast.makeText(view.getContext(), "NO RESULT TO DISPLAY", Toast.LENGTH_SHORT).show();
        }
        else
            new TdkFragment(customDialogFragment.getDefList(), customDialogFragment, txtViewDef, tdkObject).show( ((AppCompatDialogFragment)customDialogFragment).getActivity().getSupportFragmentManager(), "Anlamlar");
    }
}
