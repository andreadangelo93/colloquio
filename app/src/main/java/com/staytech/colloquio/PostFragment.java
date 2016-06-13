package com.staytech.colloquio;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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

/**
 * Created by andrea on 13/06/16.
 */
public class PostFragment extends Fragment {
    String id;
    private ListView response;
    private static final String API_URL = "http://www.iakta.it:3000";
    private List<Post> post;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment, container, false);

        response = (ListView) v.findViewById(R.id.post);
        id = getArguments().getString("id");

        post = new ArrayList<>();

        new RequestPost().execute();


        return v;
    }

    private class RequestPost extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... params) {
            StringBuilder stringBuilder = null;
            try {
                URL url = new URL(API_URL+"/posts/"+id);
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

            try {
                JSONObject jsonObject = new JSONObject(r);
                JSONArray jsonarray = jsonObject.getJSONArray("posts");
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject object = jsonarray.getJSONObject(i);
                    String id = object.getString("id");
                    String timestamp = object.getString("timestamp");
                    String latitude = object.getString("latitude");
                    String longitude = object.getString("longitude");
                    String title = object.getString("title");
                    String text = object.getString("text");
                    String base64image = object.getString("thumb_data_b64");
                    if(base64image != ""){
                        byte[] imageDataBytes = Base64.decode(base64image,Base64.DEFAULT);
                        Bitmap bmp = BitmapFactory.decodeByteArray(imageDataBytes, 0, imageDataBytes.length);
                        Post p = new Post(id, timestamp, latitude, longitude,title,text,bmp);
                        post.add(p);
                    }else{
                        Post p = new Post(id, timestamp, latitude, longitude,title,text);
                        post.add(p);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            continueOnCreate();

        }
    }

    private void continueOnCreate() {
        response.setAdapter(new MyAdapterFragment(getActivity(), post));
    }
}
