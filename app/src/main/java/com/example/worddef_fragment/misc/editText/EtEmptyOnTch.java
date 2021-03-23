package com.example.worddef_fragment.misc.editText;

import android.content.Context;
import android.view.View;

public class EtEmptyOnTch extends androidx.appcompat.widget.AppCompatEditText
{
    public EtEmptyOnTch(Context context, String initial) {
        super(context);
        setText(initial);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setText("");
            }
        });
    }

}
