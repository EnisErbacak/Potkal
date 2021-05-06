package com.example.worddef_fragment.fragments.fragment_worddef.builder.ui.editor;

import android.widget.LinearLayout;

import com.example.worddef_fragment.fragments.fragment_worddef.views.container.detailed.LlContainerMain;

public class UiEditorWorddef {


    public void show(LinearLayout ll) {
        for(int i=0;i<ll.getChildCount();i++) {
            ((LlContainerMain) ll.getChildAt(i)).getTvDef().setAlpha(1);
        }
    }

    public void hide(LinearLayout ll) {
        for(int i=0;i<ll.getChildCount();i++) {
            ((LlContainerMain) ll.getChildAt(i)).getTvDef().setAlpha(0);
        }
    }
}
