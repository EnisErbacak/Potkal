package com.example.worddef_fragment.fragments.fragment_worddef.popup;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.FragmentDialogChngWrdDef;
import com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;
import com.example.worddef_fragment.fragments.processes.operator.FragmentOperatorFactory;
import com.example.worddef_fragment.reaction.Reaction;

public class PopupEditWorddef extends PopupMenu {
    private String setName;
    private Context context;
    public PopupEditWorddef(Context context, TextView anchor, String setName) {
        super(context, anchor);
        onCreate();
        this.setName=setName;
        this.context=context;
        setOnMenuItemClickListener(new PopupEditWordDefLstner(anchor, setName));
    }

    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }


    public void onCreate() {
        getMenu().add(0, 0, Menu.NONE, "Change");
        getMenu().add(0, 1, Menu.NONE, "Delete Word");
    }


    private class PopupEditWordDefLstner implements PopupMenu.OnMenuItemClickListener {
        private TextView viewAnchor;
        private FragmentActivity fragmentActivity;

        public PopupEditWordDefLstner(TextView viewAnchor, String setName) {
            this.viewAnchor = viewAnchor;
            fragmentActivity = (FragmentActivity) scanForActivity(context);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                //  CHANGE WORD
                case 0:
                    FragmentDialogChngWrdDef fragmentDialogChngWrdDef = new FragmentDialogChngWrdDef((LinearLayout) viewAnchor.getRootView().findViewById(R.id.pnlWordDefVrt), viewAnchor, setName);
                    fragmentDialogChngWrdDef.show(fragmentActivity.getSupportFragmentManager(), "CHANGE WORD");

                    break;

                //  DELETE WORD
                case 1:
                    final AlertDialog dialogDeleteWord = new AlertDialog.Builder(viewAnchor.getContext()).create();
                    dialogDeleteWord.setMessage("WORD WILL BE DELETED!");

                    dialogDeleteWord.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    new FragmentOperatorFactory().create("worddef",context).remove(viewAnchor.getText().toString());
                                    //editor.delete();
                                    new BuilderEditor().getUiEditor(context, setName).updateScreen();

                                    new Reaction(context).showShort("WORD IS DELETED");
                                }
                            });

                    dialogDeleteWord.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialogDeleteWord.dismiss();
                                }
                            });
                    dialogDeleteWord.show();
                    return false;
            }
            return false;
        }
    }
}
