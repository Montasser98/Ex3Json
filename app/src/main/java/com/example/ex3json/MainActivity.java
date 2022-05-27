package com.example.ex3json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = loadJsonFromRaw(R.raw.entreprise);

        try {
            JSONObject obj = new JSONObject(json);

            String rs = obj.getString("raisonSociale");
            double cap = obj.getDouble("capital");
            String adrs = obj.getString("adresse");
            String deps = "";
            JSONArray arr = obj.getJSONArray("departement");
            for(int i=0;i<arr.length();i++)
                deps += arr.getString(i);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJsonFromRaw(int resId) {
        String json = "";
        try {
            InputStream input = getResources().openRawResource(resId);
            int taille = 0;
            taille = input.available();
            byte[] content = new byte[taille];
            input.read(content);
            input.close();
            json = new String(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}