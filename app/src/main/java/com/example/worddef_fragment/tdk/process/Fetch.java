package com.example.worddef_fragment.tdk.process;

import com.example.worddef_fragment.reaction.Reaction;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Fetch {
    private final String BASE_URL="https://sozluk.gov.tr/gts?ara=";
    private String keyWord;

    public JSONArray fetchWord(String keyWord) {
        JSONArray result;
        String str = BASE_URL+keyWord;
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            result= new JSONArray(stringBuffer.toString());

        } catch (JSONException jsonException) { // Occurs when word is not exist
            jsonException.printStackTrace();
            //reaction.showShort("NO RESULT!");
            result=null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //reaction.showShort("NO INTERNET CONNECTION!");
            result=null;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            //reaction.showShort("NO INTERNET CONNECTION!");
            result=null;
        }
        catch (Exception e) {
            e.printStackTrace();
            //reaction.showShort("SOMETHING WENT WRONG!");
            result=null;
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;
    }
}
