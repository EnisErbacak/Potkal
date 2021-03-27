package com.example.worddef_fragment.tdk;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.worddef_fragment.fragment.fragmentWordDef.dialog.dialog_fragments.CustomDialogFragment;
import com.example.worddef_fragment.cloud_service.gDrive.task.task_super.CustomTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TaskTdkFetcher extends CustomTask {
    private String word;
    private JSONArray jArray;
    private final Context context;
    private final Button btnDsply;
    private final CustomDialogFragment customDialogFragment;
    private TextView txtViewDef;
    private ProgressBar pbTdk;

    private final String URL_BASE="https://sozluk.gov.tr/gts?ara=";

    public TaskTdkFetcher(CustomDialogFragment customDialogFragment, String taskName, String word, Button btnDsply, ProgressBar pbTdk, TextView txtViewDef) {
        super( ((AppCompatDialogFragment) customDialogFragment).getContext(), taskName);
        this.word = word;
        this.context = ((AppCompatDialogFragment) customDialogFragment).getContext();
        this.customDialogFragment =customDialogFragment;
        this.btnDsply=btnDsply;
        this.txtViewDef=txtViewDef;
        this.pbTdk=pbTdk;
    }

    @Override
    public Object call() throws Exception {
        jArray = fetchWord(URL_BASE+word);
        return super.call();
    }

    @Override
    public void setDataAfterLoading(Object result) {
        super.setDataAfterLoading(result);

        if(jArray!=null) {
            uiSuccess();
            parseResult(jArray);
        }
        else {
            uiFailure();
            customDialogFragment.setDefList(null);
        }
    }

    private ArrayList parseResult(JSONArray jArray) {
        TdkObject tdkObject= customDialogFragment.getTdkObject();
        TdkResult objAnlam;
        String lisan;
        ArrayList<String> defs = new ArrayList<>();
        ArrayList<JSONArray> anlamlarListe = new ArrayList<JSONArray>();
        JSONArray ozelliklerListe, orneklerListe;

        try {
            lisan= ((JSONObject) jArray.get(0)).getString("lisan");
            tdkObject.setLisan(lisan);
            
            for(int i=0;i<jArray.length();i++) {
                anlamlarListe.add( ((JSONArray) ((JSONObject) jArray.get(i)).get("anlamlarListe")) );
            }

            for (int i = 0; i < anlamlarListe.size(); i++) {
                for (int j = 0; j < anlamlarListe.get(i).length(); j++) { // Fetches all the meanings of the word.

                    String anlam1, kelimeTuru1 = "", ornek1 = "",yazar = "";
                    objAnlam=new TdkResult();

                    if(( anlamlarListe.get(i).getJSONObject(j).has("ozelliklerListe"))) {
                        ozelliklerListe = ((JSONArray) anlamlarListe.get(i).getJSONObject(j).get("ozelliklerListe"));
                        for (int k = 0; k < ozelliklerListe.length(); k++) {
                            if (ozelliklerListe.length() > 1)
                                kelimeTuru1 = kelimeTuru1 + ((JSONObject) ozelliklerListe.get(k)).getString("tam_adi") + ","; // Returns tur, sıfat,isim vb.
                            else
                                kelimeTuru1 = ((JSONObject) ozelliklerListe.get(k)).getString("tam_adi");
                        }
                    }

                    if( anlamlarListe.get(i).getJSONObject(j).has("orneklerListe")) {
                        orneklerListe=((JSONArray) anlamlarListe.get(i).getJSONObject(j).get("orneklerListe"));
                        for(int a=0;a<orneklerListe.length();a++) {
                            ornek1=((JSONObject)orneklerListe.get(a)).getString("ornek");

                            if(((JSONObject) ((JSONArray) orneklerListe).get(a)).has("yazar"))
                                yazar=((JSONObject) ((JSONArray) ((JSONObject) ((JSONArray) orneklerListe).get(a)).get("yazar")).get(0)).getString("tam_adi");
                        }
                    }

                    defs.add(anlamlarListe.get(i).getJSONObject(j).getString("anlam"));
                    anlam1=anlamlarListe.get(i).getJSONObject(j).getString("anlam");

                    tdkObject.setKelime(word);
                    objAnlam.setAnlam(anlam1);
                    objAnlam.setKelimeTuru(kelimeTuru1);
                    objAnlam.setOrnek(ornek1);
                    objAnlam.setYazar(yazar);

                    tdkObject.getAnlamlar().add(objAnlam);
                    System.out.println("");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            showBckGrnd("ERROR ON PARSING!");
            defs = null;
        }

        customDialogFragment.setTdkObject(tdkObject);
        customDialogFragment.setDefList(defs); // Set the list for  TDK word and definition fragment
        return defs;
    }


    private JSONArray fetchWord(String tdkUrl) {
        JSONArray result;
        String str = tdkUrl;
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
            result=null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //showBckGrnd("INVALID URL!");
            System.out.println("NO INTERNET CONNECTION");
            result=null;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            showBckGrnd("NO INTERNET CONNECTION!");
            System.out.println("NO INTERNET CONNECTION");
            uiFailure();
            result=null;
        }
        catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG");
            result=null;
        }
            finally
         {
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

    public  void showBckGrnd(final String msg) {
        if (context != null && msg != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void uiSuccess() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                btnDsply.setTextColor(Color.BLACK);
                btnDsply.setTypeface(Typeface.DEFAULT_BOLD);
                btnDsply.setClickable(true);
                btnDsply.setEnabled(true);
                pbTdk.setAlpha(0);
            }
        });
    }

    private void uiFailure() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                btnDsply.setClickable(false);
                btnDsply.setEnabled(false);
                btnDsply.setTypeface(Typeface.DEFAULT);
                btnDsply.setTextColor(Color.parseColor("#5E6975"));
                pbTdk.setAlpha(0);
            }
        });
    }
}

