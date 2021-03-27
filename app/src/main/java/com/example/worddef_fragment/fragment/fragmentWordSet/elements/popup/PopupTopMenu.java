package com.example.worddef_fragment.fragment.fragmentWordSet.elements.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragment.fragment_settings.FragmentSettings;
import com.example.worddef_fragment.cloud_service.gDrive.GClass;
import com.example.worddef_fragment.cloud_service.manager.CloudManagerFactory;
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
        LinearLayout pnlWrdSetMain = anchor.getRootView().findViewById(R.id.pnlWrdSetMain);
        setOnMenuItemClickListener(new PopupMainOptsLstnr(pnlWrdSetMain));
        onCreate();
    }



    public void onCreate() {
        //	add(int groupId, int itemId, int order, CharSequence title)
        getMenu().add(0, 0, Menu.NONE, "Sign In Google");
        getMenu().add(0, 1, Menu.NONE, "Backup");
        getMenu().add(0, 2, Menu.NONE, "Restore");
        getMenu().add(0, 3, Menu.NONE, "Settings");
    }

    private class PopupMainOptsLstnr implements PopupMenu.OnMenuItemClickListener {

        LinearLayout pnlWrdSetMain;

        PopupMainOptsLstnr(LinearLayout pnlWrdSetMain) {
            this.pnlWrdSetMain = pnlWrdSetMain;
        }

        @Override
        public boolean onMenuItemClick(MenuItem itm) {
            pb=new ScannerActivity().scanForActivity(anchor.getContext()).findViewById(R.id.pBarWordSet);
            switch (itm.getItemId()) {
                case 0:

                    new GClass(context).login();
                    break;
                case 1:
                    String srcPath=anchor.getContext().getFilesDir().getPath() + File.separator + "wordset_files";
                    new CloudManagerFactory().create("gdrive",pb).backup(context, srcPath);
                    break;
                case 2:
                    //new GClass(context).restore();

                    String srcPath2=anchor.getContext().getFilesDir().getPath() + File.separator + "unzip_files";
                    new CloudManagerFactory().create("gdrive", pb).restore(context, srcPath2);
                    break;

                case 3:
                    FragmentSettings fragmentSettings=new FragmentSettings();
                    getFragmentManager(anchor).beginTransaction().replace(R.id.containerActivityMain, fragmentSettings).commit();
                    break;
            }
            return false;
        }

        private FragmentActivity getFragmentActivity(View view) {return (FragmentActivity) view.getContext(); }
        private FragmentManager getFragmentManager(View view) {return ((FragmentActivity)getFragmentActivity(view)).getSupportFragmentManager();}
    }
}
