package com.staytech.colloquio;

/**
 * Created by andrea on 13/06/16.
 */
public class Punto {

    double latitude, longitude;

    public Punto(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
