package com.example.worddef_fragment.fragments.fragment_settings.sections.color.dialog;

import android.graphics.Color;
import android.view.View;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.sections.color.btn.ColorSampler;

public class OKListner implements View.OnClickListener{
    private ColorSampler cs;
    private FragmentRGBPicker dialog;
    private String prefKey;

    public OKListner(ColorSampler cs, FragmentRGBPicker dialog, String prefKey) {
        this.cs = cs;
        this.dialog = dialog;
        this.prefKey = prefKey;
    }

    @Override
    public void onClick(View view) {
        int[] rgb=cs.getRGB();
        int hex=Color.rgb(rgb[0], rgb[1], rgb[2]);
        new SPEditor().setValues(view.getContext(), prefKey, String.valueOf(hex));
        dialog.dismiss();
    }
}
