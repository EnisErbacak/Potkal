package com.example.worddef_fragment.tdk.process;

public class TdkProperty {
    private String def, exmp, kind;

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getExmp() {
        return exmp;
    }

    public void setExmp(String exmp) {
        this.exmp = exmp;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void display() {
        System.out.println(def);
        System.out.println(exmp);
        System.out.println(kind);
    }
}
