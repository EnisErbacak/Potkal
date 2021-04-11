package com.example.worddef_fragment.tdk;

import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.CustomDialogFragment;
import com.example.worddef_fragment.tdk.fragment.FragmentTdk;

public class TdkBtnLstner implements View.OnClickListener {

    private CustomDialogFragment customDialogFragment;

    public TdkBtnLstner(CustomDialogFragment customDialogFragment) {
        this.customDialogFragment=customDialogFragment;
    }

    @Override
    public void onClick(View view) {
            new FragmentTdk(customDialogFragment).show( ((AppCompatDialogFragment)customDialogFragment).getActivity().getSupportFragmentManager(), "Anlamlar");
    }
}
