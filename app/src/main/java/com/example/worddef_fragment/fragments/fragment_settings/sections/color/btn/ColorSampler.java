package com.example.worddef_fragment.fragments.fragment_settings.sections.color.btn;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class ColorSampler extends androidx.appcompat.widget.AppCompatButton {

    private int r,g,b;
    public ColorSampler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRed(int r) {
        this.r = r;
    }

    public void setGreen(int g) {
        this.g = g;
    }

    public void setBlue(int b) {
        this.b = b;
    }



    public int[] getRGB() {
        return new int[] { this.r, this.g, this.b};
    }

    public void update() {
        this.setBackgroundColor(Color.rgb( this.r, this.g ,this.b));
    }
}