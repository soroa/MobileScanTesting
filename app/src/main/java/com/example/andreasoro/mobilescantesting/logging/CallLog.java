package com.example.andreasoro.mobilescantesting.logging;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andisoro on 16/04/16.
 */
public class CallLog {


    private List<Call> logAllCalls(Context context){
        ArrayList<Call> allCallsLog = new ArrayList<Call>();
        Cursor managedCursor = context.getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(android.provider.CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(android.provider.CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(android.provider.CallLog.Calls.DURATION);
        int loc = managedCursor.getColumnIndex(android.provider.CallLog.Calls.GEOCODED_LOCATION);
        int name = managedCursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME);
        int country = managedCursor.getColumnIndex(android.provider.CallLog.Calls.COUNTRY_ISO);

        while (managedCursor.moveToNext()) {


            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String locationOfNumber = managedCursor.getString(loc);
            String cachedName = managedCursor.getString(name);
            String countryS = managedCursor.getString(country);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case android.provider.CallLog.Calls.OUTGOING_TYPE:
                    allCallsLog.add(new Call(dircode,Integer.parseInt(callDuration),callDayTime,locationOfNumber,countryS,cachedName, phNumber ));

                    break;
                case android.provider.CallLog.Calls.INCOMING_TYPE:
                    allCallsLog.add(new Call(dircode,Integer.parseInt(callDuration),callDayTime,locationOfNumber,countryS,cachedName, phNumber ));
                    break;
                case android.provider.CallLog.Calls.MISSED_TYPE:
                    continue;
            }

        }
        return allCallsLog;
    }

}
