package com.example.worddef_fragment.fragments.fragment_settings.element.expandable_list;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.worddef_fragment.fragments.fragment_settings.element.layout.LlHorizontal;
import com.example.worddef_fragment.fragments.fragment_settings.element.textview.TvGroupTitle;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    HashMap<String, ArrayList<LlHorizontal>> mainList;
    ArrayList<String> titles;
    Context context;

    public ExpandableListAdapter(Context context, HashMap<String, ArrayList<LlHorizontal>> mainList) {
        this.context=context;
        this.mainList = mainList;
        titles=new ArrayList<>(mainList.keySet()); // Holds titles/ keys of the hashmap
    }


    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return mainList.get(titles.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return mainList.get(listPosition);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mainList.get(titles.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView= (TextView) new TvGroupTitle(context);
        ((TextView)convertView).setText(titles.get(listPosition));
        //((TextView)convertView).setText("TITLE");
        ((TextView) convertView).setGravity(Gravity.CENTER_HORIZONTAL);
        //convertView.setPadding(500,50,50,50);


        return convertView;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LlHorizontal ll=mainList.get(titles.get(listPosition)).get(expandedListPosition);
        return ll;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}