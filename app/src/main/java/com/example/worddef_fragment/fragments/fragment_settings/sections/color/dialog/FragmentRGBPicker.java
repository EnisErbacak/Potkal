package com.example.worddef_fragment.fragments.fragment_settings.sections.color.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.worddef_fragment.R;
import com.example.worddef_fragment.fragments.fragment_settings.sections.color.btn.ColorSampler;
import com.example.worddef_fragment.fragments.fragment_settings.sections.color.seekbar.listners.ListenerSeekbar;

public class FragmentRGBPicker extends AppCompatDialogFragment {

    private SeekBar sbBlue, sbGreen, sbRed;
    private TextView tvAlphaVal, tvBlueVal, tvGreenVal, tvRedVal;
    private ColorSampler cs;
    private Button btnOk, btnCncl;
    private int btnCol;
    private String prefKey;

    public FragmentRGBPicker(String prefKey,int btnCol) {
        this.btnCol = btnCol;
        this.prefKey=prefKey;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_rgb_picker,container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sbRed=view.findViewById(R.id.sbRed);
        sbGreen=view.findViewById(R.id.sbGreen);
        sbBlue=view.findViewById(R.id.sbBlue);

        tvRedVal=view.findViewById(R.id.tvRedVal);
        tvGreenVal=view.findViewById(R.id.tvGreenVal);
        tvBlueVal=view.findViewById(R.id.tvBlueVal);

        cs=view.findViewById(R.id.btnClrSampler);
        cs.setBackgroundColor(btnCol);

        int r = Color.red(btnCol);
        int g = Color.green(btnCol);
        int b = Color.blue(btnCol);

        tvRedVal.setText(String.valueOf(r));
        tvGreenVal.setText(String.valueOf(g));
        tvBlueVal.setText(String.valueOf(b));

        sbRed.setProgress(setBar(r));
        sbGreen.setProgress(setBar(g));
        sbBlue.setProgress(setBar(b));

        cs.setRed(r);
        cs.setGreen(g);
        cs.setBlue(b);

        sbRed.setOnSeekBarChangeListener(new ListenerSeekbar(tvRedVal,cs,"red" ));
        sbGreen.setOnSeekBarChangeListener(new ListenerSeekbar(tvGreenVal,cs,"green" ));
        sbBlue.setOnSeekBarChangeListener(new ListenerSeekbar(tvBlueVal,cs, "blue" ));


        btnOk=view.findViewById(R.id.btnRGBOk);
        btnOk.setOnClickListener(new OKListner(cs, this, prefKey));
        btnCncl=view.findViewById(R.id.btnRGBCncl);
        btnCncl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if(getDialog() !=null && getDialog().getWindow() !=null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public int setBar(int val) {

        int value = ((val / 1) * 1);
        int progress = Math.round(value * 100 / 255);
        return progress;
    }
}
