package com.example.worddef_fragment.tdk.process;

import org.json.JSONArray;

import static org.junit.jupiter.api.Assertions.*;

class FetchTest {

    JSONArray result;
    @org.junit.jupiter.api.Test
    void fetchWord() {
        Fetch fetch=new Fetch();
        String BASE_URL="https://sozluk.gov.tr/gts?ara=";
        String wordKey="temayül";
        result=fetch.fetchWord(BASE_URL+wordKey);
        assertNotEquals(null,result);
    }
}