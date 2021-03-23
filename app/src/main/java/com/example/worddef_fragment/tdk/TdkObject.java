package com.example.worddef_fragment.tdk;

import java.util.ArrayList;

public class TdkObject {
    private ArrayList<TdkResult> anlamlar;
    private String lisan,kelime;

    public TdkObject() {
        this.anlamlar = new ArrayList<>();
    }

    public ArrayList<TdkResult> getAnlamlar() {
        return anlamlar;
    }

    public String getLisan() {
        return lisan;
    }

    public void setLisan(String lisan) {
        this.lisan = lisan;
    }

    public String getKelime() {
        return kelime;
    }

    public void setKelime(String kelime) {
        this.kelime = kelime;
    }
}
