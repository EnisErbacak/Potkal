package com.example.worddef_fragment.fragments.fragmentWordSet.elements.popup;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.worddef_fragment.file.editor.WordSetEditor;
import com.example.worddef_fragment.fragments.fragmentWordSet.FragmentWordSet;
import com.example.worddef_fragment.fragments.fragmentWordSet.editor.UiEdtrWrdSet;
import com.example.worddef_fragment.fragments.fragmentWordSet.elements.view.txtView.TxtViewWrdSetLft;
import com.example.worddef_fragment.misc.editText.EtEmptyOnTch;

public class PopupWordSetEdit extends PopupMenu
{

    public PopupWordSetEdit(Context context, View anchor) {
        super(context, anchor);
        onCreate();
        setOnMenuItemClickListener(new PopupWordSetEditLstnr(anchor));
    }

    public void onCreate()
    {
        getMenu().add(0,0, Menu.NONE,"Change Set Name");
        getMenu().add(0,1, Menu.NONE,"Delete Set");
    }
}

class PopupWordSetEditLstnr implements OnMenuItemClickListener
{
    private TxtViewWrdSetLft view;
    public PopupWordSetEditLstnr(View view){this.view=(TxtViewWrdSetLft)view;}

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            //Change Set Name
            case 0:
                AlertDialog dialogChangeSetName = new AlertDialog.Builder(view.getContext()).create();
                dialogChangeSetName.setTitle("CHANGE SET NAME");
                final EtEmptyOnTch editTextsetName=new EtEmptyOnTch(view.getContext(),"Set Name");
                editTextsetName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                dialogChangeSetName.setView(editTextsetName);
                dialogChangeSetName.setButton(AlertDialog.BUTTON_POSITIVE, "CHANGE",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String oldWordSetName=view.getSetName();
                                String newWordSetName=editTextsetName.getText().toString();

                                if(!new WordSetEditor(view.getContext()).checkDuplication(newWordSetName)) {
                                    //MainFileOperator.getInstance(view.getContext()).renameFile(view.getSetName(),editTextsetName.getText().toString());
                                    new WordSetEditor(view.getContext()).rename(oldWordSetName,newWordSetName);
                                    view.setText(editTextsetName.getText().toString());
                                    view.update(editTextsetName.getText().toString());
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
                WordSetEditor editor=new WordSetEditor(view.getContext());
                AlertDialog dialogDeleteSet = new AlertDialog.Builder(view.getContext()).create();
                dialogDeleteSet.setMessage("SET WILL BE DELETED!");
                dialogDeleteSet.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                editor.delete(view.getSetName());
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