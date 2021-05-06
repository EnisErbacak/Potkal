package com.example.worddef_fragment.fragments.fragment_test.first_screen;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.worddef_fragment.file.operator.FileManager;
import com.example.worddef_fragment.file.path_picker.PathPicker;
import com.example.worddef_fragment.fragments.fragment_test.first_screen.views.container.ContainerTestWordset;

import java.util.ArrayList;

public class CreateFirstTestScreen {
    private String dir;
    FileManager fileManager;
    public void create(Context context, LinearLayout ll) {
        fileManager=new FileManager();


        ArrayList<String> fileName=fileManager.explore().getFileNames(new PathPicker(context).get(PathPicker.WORDSET));
        for(String str:fileName) {
            ll.addView(new ContainerTestWordset(context,str));
        }
    }

}
