package com.example.worddef_fragment.fragments.fragmentWordSet.elements.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.worddef_fragment.file.editor.WordSetEditor;
import com.example.worddef_fragment.fragments.fragmentWordSet.editor.UiEdtrWrdSet;

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
        getMenu().add(0, 0, Menu.NONE, "Sort by Created Date");
        getMenu().add(0, 1, Menu.NONE, "Sort by Modified Date");
        getMenu().add(0, 2, Menu.NONE, "Sort by Alphabetical");

        setConditions();
    }

    private void setConditions() {
        setOnMenuItemClickListener(new PopupMainOptsLstnr());
    }

    private class PopupMainOptsLstnr implements OnMenuItemClickListener {
        WordSetEditor editor;
        UiEdtrWrdSet uiEditor;

        @Override
        public boolean onMenuItemClick(MenuItem itm) {
            switch (itm.getItemId()) {
                // Sort by Created Date
                case 0:
                    editor=new WordSetEditor(context);
                    uiEditor=new UiEdtrWrdSet(context);
                    if(dateToggle) {

                        //editor.buildByCrtdDateDsc(anchor.getRootView().findViewById(R.id.pnlWrdSetMain));
                        //editor.buildByCrtdDateDsc();
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_ALPH_DSC);
                        dateToggle=false;
                    } else {
                        //editor.buildByCrtdDateAsc();
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_CRTD_ASC);
                        dateToggle=true;
                    }
                    break;

                // Sort by Modified Date
                case 1:
                    break;

                // Sort by Alphabetical
                case 2:
                    uiEditor=new UiEdtrWrdSet(context);
                    if(alphToggle) {
                        //new WordSetEditor(context).buildByAlphDsc(new Sorter().getAlphabetical(context));
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_ALPH_DSC);
                        alphToggle=false;
                    } else {
                        //new WordSetEditor(context).buildByAlphAsc(new Sorter().getAlphabetical(context));
                        uiEditor.updateScrn(UiEdtrWrdSet.BY_ALPH_ASC);
                        alphToggle=true;
                    }
                    break;
            }
            return false;
        }
    }
}
