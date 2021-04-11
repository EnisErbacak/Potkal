package com.example.worddef_fragment.fragments.fragment_settings.sections.color.btn;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_settings.sections.color.dialog.FragmentRGBPicker;

public class BtnColor extends androidx.appcompat.widget.AppCompatButton {
    private int colHex;
    public BtnColor(@NonNull Context context, String prefKey) {
        super(context);
        onCreate(context,prefKey);
    }

    private void onCreate(Context context, String prefKey) {
        colHex= Integer.parseInt(new SPEditor().getValue(context, prefKey));
        setPadding(0
                ,(int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics())
                ,0
                ,(int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics()));

        setStyle();
        //this.setBackgroundColor(colHex);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new FragmentRGBPicker(prefKey, colHex).show( ((FragmentActivity)context).getSupportFragmentManager(), "RGB COLOR PICKER");
            }
        });
    }

    private void setStyle() {
        DisplayMetrics displaymetrics = getContext().getResources().getDisplayMetrics();
        int dp = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, displaymetrics );

        setBackground(getGradientDrawable(colHex, dp));
    }
    private void setColor() {

    }

    private Drawable getGradientDrawable(int color,int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }


}
