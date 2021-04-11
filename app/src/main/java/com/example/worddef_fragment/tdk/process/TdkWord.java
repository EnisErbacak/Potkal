package com.example.worddef_fragment.tdk.process;

import java.util.ArrayList;

public class TdkWord { // Holds result data fetched from TDK.
    private String wordStr, lang;
    private ArrayList<TdkProperty> propertyList;

    public TdkWord(String wordStr) {
        this.wordStr=wordStr;
        propertyList=new ArrayList<>();
    }

    public ArrayList<TdkProperty> getPropertyList() {
        return propertyList;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getWordStr() {
        return wordStr;
    }

    public void display() {
        System.out.println(wordStr);
        for(int i=0;i<propertyList.size();i++) {
            System.out.println("------------");
            propertyList.get(i).display();
            System.out.println("------------");
        }
    }
}
