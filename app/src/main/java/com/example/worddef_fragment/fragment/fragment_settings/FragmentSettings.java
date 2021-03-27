package com.example.worddef_fragment.fragment.fragment_settings;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragment.fragmentWordSet.FragmentWordSet;
import com.example.worddef_fragment.fragment.fragment_settings.element.expandable_list.ExpandableListAdapter;
import com.example.worddef_fragment.fragment.fragment_settings.element.expandable_list.Operator;
import com.example.worddef_fragment.fragment.fragment_settings.element.layout.LlHorizontal;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentSettings extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String, ArrayList<LlHorizontal>> mainList;
    ArrayList<String> titles;
    Operator operator;

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

        operator=new Operator(getContext());

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        mainList=operator.getMainList();
        titles=new ArrayList<String>(mainList.keySet());

        expandableListAdapter=new ExpandableListAdapter(getContext(),
                mainList);

        expandableListView.setAdapter(expandableListAdapter);
    }


    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new KeyLstnrFragWrd());
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
