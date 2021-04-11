package com.example.worddef_fragment.fragments.fragment_wordset;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.example.worddef_fragment.R;
import com.example.worddef_fragment.file.shared_preferences.SPEditor;
import com.example.worddef_fragment.fragments.fragment_wordset.editor.UiEdtrWrdSet;

public class FragmentWordSet extends Fragment
{
    public static int ORDER_BY=0;
    private ConstraintLayout clMain; // Top parent container of wordset.
    private ScrollView svSub; // Inner container for wordset fragment.

    public static FragmentWordSet getInstance() {
        return new FragmentWordSet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word_set,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clMain=view.findViewById(R.id.clMainWordset);
        svSub=view.findViewById(R.id.svMainWordset);
        setStyle(getContext());
        new UiEdtrWrdSet(getContext()).updateScrn(ORDER_BY);
    }

    private void setStyle(Context context) {
        SPEditor spEditor=new SPEditor();
        clMain.setBackgroundColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_WORDSET_STATUSBAR)));
        svSub.setBackgroundColor(Integer.parseInt(spEditor.getValue(context, SPEditor.COL_WORDSET_BG)));
    }
}
