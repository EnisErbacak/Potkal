package com.example.worddef_fragment.fragments.fragment_worddef.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.editor.UiEditorWorddef;
import com.example.worddef_fragment.other.ScannerActivity;

public class PopupWorddefOpt extends PopupMenu
{
    public PopupWorddefOpt(Context context, View anchor) {
        super(context, anchor);
        onCreate();

        setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            LinearLayout ll=new ScannerActivity().scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 0:
                        new UiEditorWorddef().hide(ll);
                        break;
                    case 1:
                        new UiEditorWorddef().show(ll);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
    }

    public void onCreate() {
        getMenu().add(0, 0, Menu.NONE,"Hide All");
        getMenu().add(1,1,Menu.NONE,"Show All");
        getMenu().add(2,2,Menu.NONE,"Show Corrects and Mistakes");
    }
}
