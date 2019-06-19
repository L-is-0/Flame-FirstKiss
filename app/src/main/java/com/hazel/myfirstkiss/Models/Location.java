package com.hazel.myfirstkiss.Models;

public class Location {
    private int locationId;
    private double locationLat;
    private double locationLong;
    private int recordingId;

    public void setLocationId(int id){
        this.locationId = id;
    }

    public void setLocationLat(double lat){
        this.locationLat = lat;
    }

    public void setLocationLong(double longtitude){
        this.locationLong = longtitude;
    }

    public int getLocationId(){
        return this.locationId;
    }

    public double getLocationLat(){
        return this.locationLat;
    }

    public double getLocationLong(){
        return this.locationLong;
    }

    public void setRecordingId(int reId){
        this.recordingId = reId;
    }

    public int getRecordingId(){
        return this.recordingId;
    }
}
