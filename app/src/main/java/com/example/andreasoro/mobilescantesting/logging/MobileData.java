package com.example.andreasoro.mobilescantesting.logging;

/**
 * Created by andisoro on 17/04/16.
 */
public class MobileData {

    long whatsappData;
    long nonWhatsappData;
    long totalData;
    double longitude;
    double latitude;

    public MobileData(long whatsappData, long nonWhatsappData, long totalData, double longitude, double latitude) {
        this.whatsappData = whatsappData;
        this.nonWhatsappData = nonWhatsappData;
        this.totalData = totalData;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getWhatsappData() {
        return whatsappData;
    }

    public long getNonWhatsappData() {
        return nonWhatsappData;
    }

    public long getTotalData() {
        return totalData;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
