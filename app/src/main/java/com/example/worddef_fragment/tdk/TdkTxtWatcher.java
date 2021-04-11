package com.example.worddef_fragment.tdk;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.CustomDialogFragment;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.TaskRunner;

public class TdkTxtWatcher implements TextWatcher {

    private CustomDialogFragment customDialogFragment;
    private Button btnDsply;
    private TextView txtViewDef;
    private String wrd;
    private Thread fetcher1,fetcher2;
    private boolean turn1=true;

    private ProgressBar pbTdk;

    public TdkTxtWatcher(CustomDialogFragment customDialogFragment, Button btnDsply, ProgressBar pbTdk, TextView txtViewDef) {
        this.customDialogFragment = customDialogFragment;
        this.btnDsply = btnDsply;
        this.txtViewDef=txtViewDef;
        this.pbTdk=pbTdk;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        while(charSequence.length()>0) {
            if(turn1) {
                fetcher1.interrupt();
                turn1=false;
                break;
            }

            else {
                if(fetcher2!=null) {
                    fetcher2.interrupt();
                }
                turn1 = true;
                break;
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        pbTdk.setAlpha(0);
        pbTdk.setAlpha(1);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        wrd=editable.toString();

        while(!wrd.equals("")) {
            if (turn1) {
                pbTdk.setAlpha(1);
                pbTdk.setVisibility(View.VISIBLE);
                fetcher1 = new Fetcher1(customDialogFragment, btnDsply, txtViewDef, wrd);
                fetcher1.start();
                break;
            } else {
                pbTdk.setAlpha(1);
                pbTdk.setVisibility(View.VISIBLE);
                fetcher2 = new Fetcher2(customDialogFragment, btnDsply, txtViewDef, wrd);
                fetcher2.start();
                break;
            }
        }
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class Fetcher1 extends Thread {
        private CustomDialogFragment customDialogFragment;
        private Button btnDsply;
        private TextView txtViewDef;
        private String wrd;

        public Fetcher1(CustomDialogFragment customDialogFragment,Button btnDsply, TextView txtViewDef, String wrd) {
            this.customDialogFragment = customDialogFragment;
            this.btnDsply = btnDsply;
            this.txtViewDef = txtViewDef;
            this.wrd = wrd;
        }

        @Override
        public void run() {
            try {
                this.sleep(1500);
                new TdkManager(wrd, customDialogFragment).search();
                System.out.println("1  DONE");
            } catch (InterruptedException e) {
                System.out.println("1  Dead");
                this.interrupt();
            }
        }
    }

    private class Fetcher2 extends Thread {
        private CustomDialogFragment customDialogFragment;
        private Button btnDsply;
        private TextView txtViewDef;
        private String wrd;

        public Fetcher2(CustomDialogFragment customDialogFragment,Button btnDsply, TextView txtViewDef, String wrd) {
            this.customDialogFragment = customDialogFragment;
            this.btnDsply = btnDsply;
            this.txtViewDef = txtViewDef;
            this.wrd = wrd;
        }

        @Override
        public void run() {
            try {
                this.sleep(1000);
                new TdkManager(wrd, customDialogFragment).search();
                System.out.println("2   DONE");
            } catch (InterruptedException e) {
                System.out.println(" 2  Dead");
                this.interrupt();
            }
        }
    }
}
