package com.example.worddef_fragment.fragment.fragmentWordDef.elements.popupMenu;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.worddef_fragment.fragment.fragmentWordDef.FragmentWordDef;
import com.example.worddef_fragment.fragment.fragmentWordDef.editor.ui.operator.EditorOperator;

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
        getMenu().add(0, 0, Menu.NONE, "Sort by Created Date");
        getMenu().add(0, 1, Menu.NONE, "Sort by Alphabet");

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
                        new EditorOperator().getUiEditor(context, FragmentWordDef.setName).buildByCreateDateAsc();
                        toggleDate=false;
                    }
                    else {
                        new EditorOperator().getUiEditor(context, FragmentWordDef.setName).buildByCreateDateDsc();
                        toggleDate=true;
                    }
                    break;
                case 1:
                    if(toggleAlph) {
                        new EditorOperator().getUiEditor(context, FragmentWordDef.setName).buildByAlphabeticalDsc();
                        toggleAlph=false;
                    }
                    else {
                        new EditorOperator().getUiEditor(context, FragmentWordDef.setName).buildByAlphabeticalAsc();
                        toggleAlph=true;
                    }
                    break;
            }
            return false;
        }
    }
}
