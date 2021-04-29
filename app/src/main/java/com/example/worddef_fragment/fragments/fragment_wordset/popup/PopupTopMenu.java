package com.example.worddef_fragment.fragments.fragment_wordset.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_settings.FragmentSettings;
import com.example.worddef_fragment.cloud_service.manager.CloudManagerFactory;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.example.worddef_fragment.other.ScannerActivity;

import java.io.File;

public class PopupTopMenu extends PopupMenu {
    private Context context;
    private View anchor;
    private ProgressBar pb;

    public PopupTopMenu(Context context, View anchor) {
        super(context, anchor);
        this.context = context;
        this.anchor = anchor;
        setOnMenuItemClickListener(new PopupMainOptsLstnr());
        onCreate();
    }

    public void onCreate() {
        //	add(int groupId, int itemId, int order, CharSequence title)
        getMenu().add(0, 0, Menu.NONE, context.getResources().getString(R.string.backup));
        getMenu().add(0, 1, Menu.NONE, context.getResources().getString(R.string.restore));
        getMenu().add(0, 2, Menu.NONE, context.getResources().getString(R.string.settings));
    }

    private class PopupMainOptsLstnr implements PopupMenu.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem itm) {
            pb=new ScannerActivity().scanForActivity(anchor.getContext()).findViewById(R.id.pBarWordSet);
            switch (itm.getItemId()) {

                case 0:
                    String srcPath=anchor.getContext().getFilesDir().getPath() + File.separator + "wordset_files";
                    new CloudManagerFactory().create("gdrive",pb).backup(context, srcPath);
                    break;

                case 1:
                    String srcPath2=anchor.getContext().getFilesDir().getPath() + File.separator + "unzip_files";
                    if(new CloudManagerFactory().create("gdrive", pb).restore(context, srcPath2))
                        new UiEdtrWrdSet(context).updateScrn(FragmentWordSet.ORDER_BY);
                    break;

                case 2:
                    ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentSettings()).commit();
                    break;
            }
            return false;
        }
    }
}
