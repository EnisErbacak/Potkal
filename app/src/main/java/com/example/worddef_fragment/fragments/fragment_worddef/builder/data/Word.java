package com.example.worddef_fragment.fragments.fragment_worddef.builder.data;

public class Word {

    private String wrd,def, exmp, kind, lang;

    public Word() {
        wrd="";
        def ="";
        exmp ="";
        kind="";
        lang="";
    }

    public String getWrd() {
        return wrd;
    }

    public void setWrd(String wrd) {
        this.wrd = wrd;
    }

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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
