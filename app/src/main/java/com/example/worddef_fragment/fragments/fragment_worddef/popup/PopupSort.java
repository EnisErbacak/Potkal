package com.example.worddef_fragment.fragments.fragment_worddef.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_worddef.FragmentWordDef;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;

public class PopupSort extends PopupMenu {
    private  Context context;
    private static boolean toggleDate=true;
    private static boolean toggleAlph=true;

    public PopupSort(Context context, View anchor) {
        super(context, anchor);
        this.context=context;
        onCreate();
    }

    public void onCreate() {
        //	add(int groupId, int itemId, int order, CharSequence title)
        getMenu().add(0, 0, Menu.NONE, context.getResources().getString(R.string.sort_by_created_date));
        getMenu().add(0, 1, Menu.NONE, context.getResources().getString(R.string.sort_by_alphabetical));

        setConditions();
    }

    private void setConditions() {
        setOnMenuItemClickListener(new PopupMainOptsLstnr());
    }

    private class PopupMainOptsLstnr implements OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem itm) {
            switch (itm.getItemId()) {

                // Sort by Created Date
                case 0:
                    if(toggleDate) {
                        new BuilderEditor().getUiEditor(context, FragmentWordDef.setName).buildByCreateDateAsc();
                        toggleDate=false;
                    }
                    else {
                        new BuilderEditor().getUiEditor(context, FragmentWordDef.setName).buildByCreateDateDsc();
                        toggleDate=true;
                    }
                    break;
                case 1:
                    if(toggleAlph) {
                        new BuilderEditor().getUiEditor(context, FragmentWordDef.setName).buildByAlphabeticalDsc();
                        toggleAlph=false;
                    }
                    else {
                        new BuilderEditor().getUiEditor(context, FragmentWordDef.setName).buildByAlphabeticalAsc();
                        toggleAlph=true;
                    }
                    break;
            }
            return false;
        }
    }
}
