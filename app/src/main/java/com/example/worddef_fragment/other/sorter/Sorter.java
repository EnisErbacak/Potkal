package com.example.worddef_fragment.other.sorter;

import android.content.Context;

import com.example.worddef_fragment.file.operator2.FileExplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    public ArrayList<String> getByModified(Context context, FileExplorer explorer) {
        File[] files=explorer.getSetFiles(context);
        return null;
    }

    public ArrayList<String> getAlphabetical(Context context) {
        ArrayList<String> sorted=new ArrayList<>();
        File[] files=new FileExplorer().getSetFiles(context);
        for (File file : files) {
            sorted.add(file.getName());
        }
        Collections.sort(sorted, new Comparator(){

            @Override
            public int compare(Object s1, Object s2) {
                return ((String)s1).compareToIgnoreCase((String)s2);
            }
        });
        return sorted;
    }
}
