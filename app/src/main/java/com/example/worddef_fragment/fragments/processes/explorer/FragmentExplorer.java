package com.example.worddef_fragment.fragments.processes.explorer;

import java.util.ArrayList;

public interface FragmentExplorer {
    int getCount(String dirOrFile);
    boolean checkDuplication(String dirOrFile, String name);
    ArrayList<String> getNames(String dirOrFile);
}
