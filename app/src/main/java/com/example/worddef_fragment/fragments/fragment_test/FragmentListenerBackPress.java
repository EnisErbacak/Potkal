package com.example.worddef_fragment.fragments.fragment_test;

import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;

public class FragmentListenerBackPress implements View.OnKeyListener {
    private Fragment currentFragment, prevFragment;

    public FragmentListenerBackPress(Fragment currentFragment, Fragment prevFragment) {
        this.currentFragment = currentFragment;
        this.prevFragment=prevFragment;
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            ((FragmentActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, prevFragment).commit();
            currentFragment.onDestroy();
            return true;
        }
        return false;
    }
}
