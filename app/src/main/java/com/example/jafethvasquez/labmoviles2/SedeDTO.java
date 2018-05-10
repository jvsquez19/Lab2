package com.example.jafethvasquez.labmoviles2;

import com.google.android.gms.maps.model.LatLng;

public class SedeDTO {
    String name ="";
    double latitude = 0;
    double longitude;
    String Description;

    public SedeDTO() {
        
    }

    public SedeDTO(String name, double latitude, double longitude, String description) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
