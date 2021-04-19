package com.example.worddef_fragment.fragments.fragment_test;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPickerFactory;
import com.example.worddef_fragment.fragments.fragment_test.views.ContainerTestWordset;
import com.example.worddef_fragment.fragments.fragment_wordset.views.container.ContainerInnerRght;
import com.example.worddef_fragment.fragments.fragment_wordset.views.container.ContainerWrdset;

import java.io.File;
import java.util.ArrayList;

public class CreateFirstTestScreen {
    private String dir;
    FileManager fileManager;
    public void create(Context context, LinearLayout ll) {
        fileManager=new FileManager();
        ArrayList<String> fileName=fileManager.explore().getFileNames(new PathPickerFactory().create("wordset").get(context));
        for(String str:fileName) {
            ll.addView(new ContainerTestWordset(context,str));
        }
    }
}
