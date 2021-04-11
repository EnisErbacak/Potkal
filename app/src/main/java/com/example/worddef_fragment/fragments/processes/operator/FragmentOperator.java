package com.example.worddef_fragment.fragments.processes.operator;

import org.json.JSONObject;

public interface FragmentOperator {
    void initialize();
    JSONObject get(String name);
    boolean add(String name, JSONObject jObj);
    void remove(String name);
    boolean rename(String nameOld, String nameNew);
    void update(String name, JSONObject jObj);
}
