package com.example.andreasoro.mobilescantesting;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    TextView textView = null;
    MobileTrafficReader tr=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Andrea", "creating Main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview_call);
        getCallDetails();
        FileWriteRead.writeLogFile(" \n" + " \n" + "0", this);
        tr = new MobileTrafficReader();


    }

    private void getCallDetails() {
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        int loc = managedCursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int country = managedCursor.getColumnIndex(CallLog.Calls.COUNTRY_ISO);

        sb.append("Call Log :");
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
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- "
                    + callDayTime + " \nCall duration in sec :--- " + callDuration+ " \nLocation of other number: ---" + locationOfNumber +
                        " \nAccount Name : --- : " + cachedName + " \nMy location: --- "+ countryS );
            sb.append("\n----------------------------------");
        } //managedCursor.close();

        textView.setText(sb);
    }

    public void showInternetTrafficStats(View v){

        Intent i = new Intent(this,TrafficMonitorActivity.class);
        startActivity(i);
    }



}
