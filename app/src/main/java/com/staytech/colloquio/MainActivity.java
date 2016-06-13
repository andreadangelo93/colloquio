package com.staytech.colloquio;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    private ProgressBar load;
    private ListView response;
    private static final String API_URL = "http://www.iakta.it:3000";
    private List<Viaggio> viaggi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = (ProgressBar) findViewById(R.id.load);
        response = (ListView) findViewById(R.id.viaggi);

        viaggi = new ArrayList<>();


        new RequestTravel().execute();
    }

    private class RequestTravel extends AsyncTask<Void,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            load.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(Void... params) {
            StringBuilder stringBuilder = null;
            try {
                URL url = new URL(API_URL+"/travelog/1");
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
            load.setVisibility(View.GONE);

            try{
                JSONObject jsonObject = new JSONObject(r);
                JSONArray jsonarray = jsonObject.getJSONArray("travelogs");
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject object = jsonarray.getJSONObject(i);
                    String id = object.getString("id");
                    String name = object.getString("name");
                    String starting_date = object.getString("starting_date");
                    String ending_date = object.getString("ending_date");
                    Viaggio v = new Viaggio(id,name,starting_date,ending_date);
                    viaggi.add(v);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            continueOnCreate();
        }

    }

    private void continueOnCreate() {
        response.setAdapter(new MyAdapter(MainActivity.this,viaggi));
        response.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

    }
    private void selectItem(int position) {
        switch (position){
            case 0:
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                PostFragment f = new PostFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id",viaggi.get(position).getId());
                f.setArguments(bundle);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 1:
                FragmentManager fm1 = getFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                PostFragment f1 = new PostFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("id",viaggi.get(position).getId());
                f1.setArguments(bundle1);
                ft1.replace(R.id.fragment_container, f1);
                ft1.addToBackStack(null);
                ft1.commit();
                break;
            case 2:
                FragmentManager fm2 = getFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                PostFragment f2 = new PostFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("id",viaggi.get(position).getId());
                f2.setArguments(bundle2);
                ft2.replace(R.id.fragment_container, f2);
                ft2.addToBackStack(null);
                ft2.commit();
                break;
            case 3:
                FragmentManager fm3 = getFragmentManager();
                FragmentTransaction ft3 = fm3.beginTransaction();
                PostFragment f3 = new PostFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("id",viaggi.get(position).getId());
                f3.setArguments(bundle3);
                ft3.replace(R.id.fragment_container, f3);
                ft3.addToBackStack(null);
                ft3.commit();
                break;
            case 4:
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                PostFragment f4 = new PostFragment();
                Bundle bundle4 = new Bundle();
                bundle4.putString("id",viaggi.get(position).getId());
                f4.setArguments(bundle4);
                ft4.replace(R.id.fragment_container, f4);
                ft4.addToBackStack(null);
                ft4.commit();
                break;
        }
    }
}
