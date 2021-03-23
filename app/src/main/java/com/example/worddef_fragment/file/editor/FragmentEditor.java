package com.example.worddef_fragment.file.editor;

import android.content.Context;
import android.view.View;

import org.json.JSONObject;

public interface FragmentEditor {
    public void initialize();
    public JSONObject get(String name);
    public boolean add(String name, JSONObject jObj);
    public void remove(String name);
    public boolean change(String nameOld, String nameNew, JSONObject jObj);
    public boolean checkDuplication(String name);
    public void update();
}
