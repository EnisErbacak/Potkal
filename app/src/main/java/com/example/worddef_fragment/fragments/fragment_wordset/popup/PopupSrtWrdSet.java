package com.example.worddef_fragment.fragments.fragment_wordset.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_wordset.editor.UiEdtrWrdSet;

public class PopupSrtWrdSet extends PopupMenu {
    private Context context;
    private View anchor;

    private static boolean dateToggle=false;
    private static boolean alphToggle=false;

    public PopupSrtWrdSet(Context context, View anchor) {
        super(context, anchor);
        this.context=context;
        this.anchor=anchor;
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
        UiEdtrWrdSet uiEditor;

        @Override
        public boolean onMenuItemClick(MenuItem itm) {
            switch (itm.getItemId()) {
                // Sort by Created Date
                case 0:
                    uiEditor=new UiEdtrWrdSet(context);
                    if(dateToggle) {
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_ALPH_DSC);
                        dateToggle=false;
                    } else {
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_CRTD_ASC);
                        dateToggle=true;
                    }
                    break;

                // Sort by Alphabetical
                case 1:
                    uiEditor=new UiEdtrWrdSet(context);
                    if(alphToggle) {
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_ALPH_DSC);
                        alphToggle=false;
                    } else {
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_ALPH_ASC);
                        alphToggle=true;
                    }
                    break;
            }
            return false;
        }
    }
}