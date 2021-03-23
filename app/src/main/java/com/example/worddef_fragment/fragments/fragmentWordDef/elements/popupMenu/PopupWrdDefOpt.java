package com.example.worddef_fragment.fragments.fragmentWordDef.elements.popupMenu;

import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragmentWordDef.elements.view.panel.PnlContainerWrdDef;
import com.example.worddef_fragment.other.ScannerActivity;

public class PopupWrdDefOpt extends PopupMenu
{
    public PopupWrdDefOpt(Context context, View anchor) {
        super(context, anchor);
        onCreate();

        setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            LinearLayout ll=new ScannerActivity().scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+menuItem.getItemId());
                switch (menuItem.getItemId()) {
                    case 0:for(int i=0;i<ll.getChildCount();i++) {
                            PnlContainerWrdDef wrdDef= (PnlContainerWrdDef) ll.getChildAt(i);
                            wrdDef.getViewDef().setTextColor(Color.TRANSPARENT);
                        }
                        break;
                    case 1:
                        for(int i=0;i<ll.getChildCount();i++) {
                            PnlContainerWrdDef wrdDef= (PnlContainerWrdDef) ll.getChildAt(i);
                            wrdDef.getViewDef().setTextColor(Color.BLACK);
                        }
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
