package com.example.worddef_fragment.fragments.fragment_worddef.dialog.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.FragmentDialogAddWrdDef;
import com.example.worddef_fragment.tdk.process.Fetch;
import com.example.worddef_fragment.tdk.process.Parse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EtWrd extends androidx.appcompat.widget.AppCompatEditText {

    public EtWrd(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private class WrdWtcher implements TextWatcher {

        private FragmentDialogAddWrdDef dialogAddWrdDef;
        private Button btnDsply;
        private TextView txtViewDef;
        private String wrd;
        private ProgressBar pbTdk;
        private Thread fetcher1,fetcher2;

        private boolean turn1=true;

        public WrdWtcher(FragmentDialogAddWrdDef dialogAddWrdDef, Button btnDsply, ProgressBar pbTdk, TextView txtViewDef) {
            this.dialogAddWrdDef = dialogAddWrdDef;
            this.btnDsply = btnDsply;
            this.txtViewDef=txtViewDef;
            this.pbTdk=pbTdk;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            while(charSequence.length()>0)
            //while(!charSequence.toString().equals(""))
            {
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
        }

        @Override
        public void afterTextChanged(Editable editable) {
            wrd=editable.toString();

            while(!wrd.equals("")) {
                if (turn1) {
                    fetcher1 = new Fetcher1(dialogAddWrdDef, btnDsply, pbTdk, txtViewDef, wrd);
                    fetcher1.start();
                    break;
                } else {
                    fetcher2 = new Fetcher2(dialogAddWrdDef, btnDsply,pbTdk, txtViewDef, wrd);
                    fetcher2.start();
                    break;
                }
            }
        }
    }

    private class Fetcher1 extends Thread {
        private FragmentDialogAddWrdDef dialogAddWrdDef;
        private Button btnDsply;
        private TextView txtViewDef;
        private String keyWord;
        private ProgressBar pbTdk;

        public Fetcher1(FragmentDialogAddWrdDef dialogAddWrdDef,Button btnDsply, ProgressBar pbTdk, TextView txtViewDef, String keyWord) {
            this.dialogAddWrdDef=dialogAddWrdDef;
            this.btnDsply = btnDsply;
            this.txtViewDef = txtViewDef;
            this.keyWord = keyWord;
            this.pbTdk=pbTdk;
        }

        @Override
        public void run() {
            try {
                this.sleep(1500);
                /*
                TaskTdkFetcher taskFtchWrdTdk = new TaskTdkFetcher(dialogAddWrdDef, "Fetch Word From TDK", wrd, btnDsply, pbTdk, txtViewDef);
                new TaskRunner().executeAsync(taskFtchWrdTdk);
                 */

                ExecutorService executor= Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {

                    @Override
                    public void run() {
                        Fetch fetch=new Fetch();
                        Parse parse=new Parse();
                        parse.parseResult(fetch.fetchWord(keyWord));
                    }
                });

                System.out.println("1  DONE");
            } catch (InterruptedException e) {
                System.out.println("1  Dead");
                this.interrupt();
            }
        }
    }

    private class Fetcher2 extends Thread {
        private FragmentDialogAddWrdDef dialogAddWrdDef;
        private Button btnDsply;
        private TextView txtViewDef;
        private String keyWord;
        ProgressBar pbTdk;

        public Fetcher2(FragmentDialogAddWrdDef dialogAddWrdDef,Button btnDsply, ProgressBar pbTdk, TextView txtViewDef, String keyWord) {
            this.dialogAddWrdDef=dialogAddWrdDef;
            this.btnDsply = btnDsply;
            this.txtViewDef = txtViewDef;
            this.keyWord = keyWord;
            this.pbTdk=pbTdk;
        }

        @Override
        public void run() {
            try {
                this.sleep(1000);
                /*
                TaskTdkFetcher taskFtchWrdTdk = new TaskTdkFetcher(dialogAddWrdDef, "Fetch Word From TDK", wrd, btnDsply, pbTdk, txtViewDef);
                new TaskRunner().executeAsync(taskFtchWrdTdk);
                System.out.println("2   DONE");
                 */
                ExecutorService executor= Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {

                    @Override
                    public void run() {
                        Fetch fetch=new Fetch();
                        Parse parse=new Parse();
                        parse.parseResult(fetch.fetchWord(keyWord));
                    }
                });

            } catch (InterruptedException e) {
                System.out.println(" 2  Dead");
                this.interrupt();
            }
        }
    }
}
