package com.example.worddef_fragment.fragments.fragment_settings;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragment_settings.element.expandable_list.ExpandableListAdapter;
import com.example.worddef_fragment.fragments.fragment_settings.element.expandable_list.Operator;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentSettings extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String, ArrayList<ConstraintLayout>> mainList;
    ArrayList<String> titles;
    Operator operator;
    ExpandableListView elvFragmentSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_settings,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition(getView());
        elvFragmentSettings=view.findViewById(R.id.elvFragmentSettings);

        operator=new Operator(getContext());

        expandableListView = (ExpandableListView) view.findViewById(R.id.elvFragmentSettings);

        mainList=operator.getMainList();
        titles=new ArrayList<String>(mainList.keySet());

        expandableListAdapter=new ExpandableListAdapter(getContext(),
                mainList);

        expandableListView.setAdapter(expandableListAdapter);

        setStyle();
    }


    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new KeyLstnrFragWrd());
    }

    private void setStyle() {
        elvFragmentSettings.setBackgroundColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_SETTINGS_BG)));
    }


    private class KeyLstnrFragWrd implements View.OnKeyListener {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if(keyCode==KeyEvent.KEYCODE_BACK) {
                getFragmentManager(view).beginTransaction().replace(R.id.containerActivityMain, FragmentWordSet.getInstance()).commit();
                onDestroy();
                return true;
            }
            return false;
        }
    }

    private FragmentActivity getFragmentActivity(View view) {return (FragmentActivity) view.getContext(); }
    private FragmentManager getFragmentManager(View view) {return ((FragmentActivity)getFragmentActivity(view)).getSupportFragmentManager();}

}
