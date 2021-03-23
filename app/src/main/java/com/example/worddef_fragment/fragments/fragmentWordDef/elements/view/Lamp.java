package com.example.worddef_fragment.fragments.fragmentWordDef.elements.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Lamp  extends androidx.appcompat.widget.AppCompatImageButton {

    private Paint paint=new Paint();
    public Lamp(Context context)
    {
        super(context);
        onCreate();
        paint.setColor(Color.GRAY);
    }

    public void onCreate()
    {
        this.setId(generateViewId());

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        getBackground().setAlpha(0);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        // paint.setStrokeWidth(10);
        //canvas.drawRect(rect, myPaint);
        canvas.drawCircle(getWidth()/2,getHeight()/2,((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics())),paint);
    }

    public void changeCircleColor(int color)
    {
        paint.setColor(color);
        invalidate();
    }

}
