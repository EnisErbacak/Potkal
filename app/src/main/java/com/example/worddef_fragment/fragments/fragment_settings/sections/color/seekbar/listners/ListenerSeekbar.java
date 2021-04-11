package com.example.worddef_fragment.fragments.fragment_settings.sections.color.seekbar.listners;

import android.widget.SeekBar;
import android.widget.TextView;

import com.example.worddef_fragment.fragments.fragment_settings.sections.color.btn.ColorSampler;

public class ListenerSeekbar implements SeekBar.OnSeekBarChangeListener {

    final int MIN=0;
    final int MAX=255;
    final int STEP=1;

    private TextView tv;
    private ColorSampler cs;
    private String type;

    public ListenerSeekbar(TextView tv, ColorSampler cs, String type) {
        this.tv = tv;
        this.cs=cs;
        this.type=type;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        double value = Math.round((progress * (MAX - MIN)) / 100);
        int displayValue = ( ((int) value + MIN) / STEP) * STEP;
        tv.setText(String.valueOf(displayValue));
        System.out.println("---- "+displayValue);

        setValue(cs, type, displayValue);
        setBar(displayValue);
    }

    public void setBar(int val) {

        int value=((  val/1)*1);
        int progress=Math.round(value*100/255);
        System.out.println(value);


        //int progress=Math.round(value*100/255);
        //double value = Math.round((progress * (MAX - MIN)) / 100);
        //int displayValue = (((int) value + MIN) / STEP) * STEP;
        // ((int)(displayValue/STEP)*STEP) - MIN)= value
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setValue(ColorSampler cs,String type, int val) {
        switch (type.toLowerCase()) {

            case "red":
                cs.setRed(val);
                cs.update();
                break;
            case "green":
                cs.setGreen(val);
                cs.update();
                break;
            case "blue":
                cs.setBlue(val);
                cs.update();
                break;
        }
    }
}
