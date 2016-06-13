package com.staytech.colloquio;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MappaActivity extends AppCompatActivity {

   private String id;
    private static final String API_URL = "http://www.iakta.it:3000";
    public static List<Punto> punti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappa);

        id = getIntent().getExtras().getString("id");
        punti = new ArrayList<>();

        new RequestMapTravel().execute();
    }

    private class RequestMapTravel extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... params) {
            StringBuilder stringBuilder = null;
            try {
                URL url = new URL(API_URL+"/points/"+id);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                try {
                    stringBuilder = new StringBuilder();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    reader.close();

                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);

            try{
                JSONObject jsonObject = new JSONObject(r);
                JSONArray jsonarray = jsonObject.getJSONArray("points");
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject object = jsonarray.getJSONObject(i);
                    String latitude = object.getString("latitude");
                    String longitude = object.getString("longitude");
                    Double lat = Double.valueOf(latitude);
                    Double lon = Double.valueOf(longitude);
                    Punto p = new Punto(lat,lon);
                    punti.add(p);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(MappaActivity.this,MapsActivity.class));
        }

    }
}
