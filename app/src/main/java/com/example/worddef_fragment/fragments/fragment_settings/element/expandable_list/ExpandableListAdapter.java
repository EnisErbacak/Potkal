package com.example.worddef_fragment.fragments.fragment_settings.element.expandable_list;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.worddef_fragment.fragments.fragment_settings.element.textview.TvGroupTitle;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    HashMap<String, ArrayList<ConstraintLayout>> mainList;
    ArrayList<String> titles;
    Context context;

    public ExpandableListAdapter(Context context, HashMap<String, ArrayList<ConstraintLayout>> mainList) {
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
        ((TextView) convertView).setGravity(Gravity.CENTER_HORIZONTAL);
        convertView.setPadding(0
                ,(int)TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, convertView.getContext().getResources().getDisplayMetrics())
                , 0
                ,(int)TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, convertView.getContext().getResources().getDisplayMetrics()));
        return convertView;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ConstraintLayout ll=mainList.get(titles.get(listPosition)).get(expandedListPosition);

        ll.setPadding( (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 5, ll.getContext().getResources().getDisplayMetrics())
                ,(int)TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, ll.getContext().getResources().getDisplayMetrics())
                , 0
                ,(int)TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, ll.getContext().getResources().getDisplayMetrics()));
        return ll;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}