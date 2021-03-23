package com.example.worddef_fragment.fragments.fragmentWordSet.elements.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.operator2.FileOperator;
import com.example.worddef_fragment.fragments.fragment_settings.FragmentSettings;
import com.example.worddef_fragment.cloud_service.gDrive.GClass;
import com.example.worddef_fragment.manager.CloudManagerFactory;

import java.io.File;

public class PopupTopMenu extends PopupMenu {
    private Context context;
    private View anchor;

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

        FileOperator operator;
        LinearLayout pnlWrdSetMain;


        PopupMainOptsLstnr(LinearLayout pnlWrdSetMain) {
            this.pnlWrdSetMain = pnlWrdSetMain;
            operator = new FileOperator();
        }

        @Override
        public boolean onMenuItemClick(MenuItem itm) {

            switch (itm.getItemId()) {
                case 0:
                    //Intent signInIntent = new Intent(context, ActivityGDrive.class);
                    //context.startActivity(signInIntent);
                    new GClass(context).login();
                    //((BtnMenuTop)anchor).getPBar().setVisibility(View.VISIBLE);
                    break;
                case 1:
                    String srcPath=anchor.getContext().getFilesDir().getPath() + File.separator + "wordset_files";
                    new CloudManagerFactory().create("gdrive").backup(context, srcPath);

                    /*
                    if (operator.export(context)) {
                        new GClass(context).backup();
                    }
                    /*
                    Intent intentBackup = new Intent(context, ActivityGDrive.class);
                    intentBackup.putExtra("order", ActivityGDrive.ORDER_UPLOAD);
                    if (operator.export(context)) {
                        context.startActivity(intentBackup);
                    }
                     */
                    break;
                case 2:
                    //new GClass(context).restore();

                    String srcPath2=anchor.getContext().getFilesDir().getPath() + File.separator + "unzip_files";
                    new CloudManagerFactory().create("gdrive").restore(context, srcPath2);

                    /*
                    Intent intentRestore = new Intent(context, ActivityGDrive.class);
                    intentRestore.putExtra("order", ActivityGDrive.ORDER_DOWNLOAD);
                    context.startActivity(intentRestore);
                     */
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
