package com.example.worddef_fragment.tdk;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_worddef.dialog.dialog_fragments.CustomDialogFragment;
import com.example.worddef_fragment.other.ScannerActivity;
import com.example.worddef_fragment.tdk.process.Fetch;
import com.example.worddef_fragment.tdk.process.Parse;
import com.example.worddef_fragment.tdk.process.TdkWord;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TdkManager {

    private CustomDialogFragment customDialogFragment;
    private Button btnDisplayTdk;
    private ProgressBar pbTdk;
    private String keyWord;
    private Context context;

    private ArrayList<TdkWord> tdkWordList;

    public TdkManager(String keyWord,CustomDialogFragment customDialogFragment, Button btnDisplayTdk, ProgressBar pbTdk) {
        this.customDialogFragment=customDialogFragment;
        this.keyWord=keyWord;
        this.btnDisplayTdk=btnDisplayTdk;
        this.context=btnDisplayTdk.getContext();
        this.pbTdk=pbTdk;
    }

    public void search() {
        ExecutorService executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Fetch fetch=new Fetch();
                Parse parse=new Parse();

                tdkWordList=parse.parseResult(fetch.fetchWord(keyWord));

                if(tdkWordList.size()!=0) {
                    new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnDisplayTdk.setClickable(true);
                            btnDisplayTdk.setEnabled(true);
                            btnDisplayTdk.setText(context.getResources().getString(R.string.tdk_results));
                            pbTdk.setAlpha(0);

                            customDialogFragment.setTdkWordList(tdkWordList);
                        }
                    });
                }else {
                    new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnDisplayTdk.setClickable(false);
                            btnDisplayTdk.setEnabled(false);
                            btnDisplayTdk.setText("");
                            pbTdk.setAlpha(0);
                        }
                    });
                }
            }
        });
    }
}
