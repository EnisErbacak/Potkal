package com.example.worddef_fragment.fragment.processes.operator;

import org.json.JSONObject;

public interface FragmentOperator {
    public void initialize();
    public JSONObject get(String name);
    public boolean add(String name, JSONObject jObj);
    public void remove(String name);
    public boolean rename(String nameOld, String nameNew);
    public void update(String name, JSONObject jObj);
}
