package com.example.andreasoro.mobilescantesting.logging;

import java.util.Date;

/**
 * Created by andisoro on 16/04/16.
 */


public class Call {
    //constants
    public short OUTGOING_CALL = 2;
    public short INCOMING_CALL = 1;
    int type;
    int duration;
    Date dateAndTime;
    String locationOfOtherNumber;
    String locationOfThisNumber;
    String contactName;
    String number;

    public int getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public String getLocationOfOtherNumber() {
        return locationOfOtherNumber;
    }

    public String getLocationOfThisNumber() {
        return locationOfThisNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public String getNumber() {
        return number;
    }

    public Call(){

    }

    public Call(int type,int duration, Date dateAndTime, String locationOfOtherNumber, String locationOfThisNumber, String accountName, String number) {
        this.type = type;
        this.duration = duration;
        this.dateAndTime = dateAndTime;
        this.locationOfOtherNumber = locationOfOtherNumber;
        this.locationOfThisNumber = locationOfThisNumber;
        this.contactName = accountName;
        this.number = number;
    }
}
