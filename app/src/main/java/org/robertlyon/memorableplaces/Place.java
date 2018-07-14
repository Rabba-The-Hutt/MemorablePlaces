package org.robertlyon.memorableplaces;

public class Place {

    private static int totalPlaces = 0;
    private int id;
    private String address;
    private double latitude;
    private double longitude;

    public Place(String address, double aLatitude, double aLongitude)
    {
        id = totalPlaces;
        this.address = address;
        latitude = aLatitude;
        longitude = aLongitude;
        totalPlaces++;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
