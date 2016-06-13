package com.staytech.colloquio;

import android.graphics.Bitmap;

/**
 * Created by andrea on 13/06/16.
 */
public class Post {

    String id,timestamp,latitude,longitude,title,text;
    Bitmap bmp;

    public Post(String id, String timestamp, String latitude, String longitude, String title, String text) {
        this.id = id;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.text = text;
    }

    public Post(String id, String timestamp, String latitude, String longitude, String title, String text, Bitmap bmp) {
        this.id = id;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.text = text;
        this.bmp = bmp;

    }

    public String getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
    public Bitmap getBmp() {
        return bmp;
    }
}
