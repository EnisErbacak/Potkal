package com.example.worddef_fragment.fragment.processes.explorer;

import org.json.JSONObject;

import java.util.ArrayList;

public interface FragmentExplorer {
    public int getCount(String dirOrFile);
    public boolean checkDuplication(String dirOrFile, String name);
    public ArrayList<String> getNames(String dirOrFile);
}
