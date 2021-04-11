package com.example.worddef_fragment.fragments.fragment_wordset.popup;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.fragments.fragment_wordset.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.example.worddef_fragment.fragments.fragment_wordset.views.txt_view.TvWordsetLeft;
import com.example.worddef_fragment.fragments.processes.operator.FragmentOperatorFactory;

public class PopupWordSetEdit extends PopupMenu
{

    public PopupWordSetEdit(Context context, View anchor) {
        super(context, anchor);
        onCreate();
        setOnMenuItemClickListener(new PopupWordSetEditLstnr(anchor));
    }

    public void onCreate() {
        getMenu().add(0,0, Menu.NONE,"Change Set Name");
        getMenu().add(0,1, Menu.NONE,"Delete Set");
    }
}

class PopupWordSetEditLstnr implements OnMenuItemClickListener {
    private TvWordsetLeft view;
    public PopupWordSetEditLstnr(View view){this.view=(TvWordsetLeft)view;}

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            //Change Set Name
            case 0:
                AlertDialog dialogChangeSetName = new AlertDialog.Builder(view.getContext()).create();
                dialogChangeSetName.setTitle("CHANGE SET NAME");
                final EditText editTextsetName=new EditText(view.getContext());
                editTextsetName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                dialogChangeSetName.setView(editTextsetName);
                dialogChangeSetName.setButton(AlertDialog.BUTTON_POSITIVE, "CHANGE",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String oldWordSetName=view.getWordsetName();
                                String newWordSetName=editTextsetName.getText().toString();

                                if(!new FileManager().explore().checkDuplication(new PathPickerFactory().create("wordset").get(view.getContext()), newWordSetName)) {
                                    //MainFileOperator.getInstance(view.getContext()).renameFile(view.getSetName(),editTextsetName.getText().toString());
                                    //new WordSetEditor2(view.getContext()).rename(oldWordSetName,newWordSetName);
                                    new FragmentOperatorFactory().create("wordset",view.getContext())
                                            .rename(oldWordSetName, newWordSetName);
                                    view.setText(editTextsetName.getText().toString());
                                }
                                else
                                {
                                    Toast.makeText(view.getContext(),"There is already a set has same name",Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        });
                dialogChangeSetName.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialogChangeSetName.show();

                break;

            //Delete Set
            case 1:
                AlertDialog dialogDeleteSet = new AlertDialog.Builder(view.getContext()).create();
                dialogDeleteSet.setMessage("SET WILL BE DELETED!");
                dialogDeleteSet.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new FragmentOperatorFactory().create("wordset", view.getContext()).remove(view.getWordsetName());
                                //editor.buildByCrtdDateDsc();
                                new UiEdtrWrdSet(view.getContext()).updateScrn(FragmentWordSet.ORDER_BY);
                            }
                        });
                dialogDeleteSet.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialogDeleteSet.show();

                break;
        }
        return false;
    }
}