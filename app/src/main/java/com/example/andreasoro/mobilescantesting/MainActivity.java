package com.example.andreasoro.mobilescantesting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_READ_CALL_LOG=0;
    public static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION=1;
    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION=2;
    public static final String TAG =MainActivity.class.getSimpleName();
    TextView textView = null;
    MDLogService mMDLogService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "creating Main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview_call);
        Log.d(TAG, " Main created");
        requestAllPermissions();
        mMDLogService = new MDLogService();

//        getCallDetails();
//        File f = new File("dataLog");
//        if(!f.exists()){
//            FileWriteRead.writeLogFile("0\n" + "0\n" + "0", this);
//        }
//
//        tr = new MobileTrafficReader();


    }


    // TODO: 19/04/16 Since v23 gotta ask for permission to display log excplicitly
    private void getCallDetails() {
//        StringBuffer sb = new StringBuffer();
//       // Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null); deprecated
//        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
//        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
//        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
//        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
//        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
//        int loc = managedCursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION);
//        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
//        int country = managedCursor.getColumnIndex(CallLog.Calls.COUNTRY_ISO);
//
//        sb.append("Call Log :");
//        while (managedCursor.moveToNext()) {
//
//
//            String phNumber = managedCursor.getString(number);
//            String callType = managedCursor.getString(type);
//            String callDate = managedCursor.getString(date);
//            Date callDayTime = new Date(Long.valueOf(callDate));
//            String callDuration = managedCursor.getString(duration);
//            String locationOfNumber = managedCursor.getString(loc);
//            String cachedName = managedCursor.getString(name);
//            String countryS = managedCursor.getString(country);
//            String dir = null;
//            int dircode = Integer.parseInt(callType);
//            switch (dircode) {
//                case CallLog.Calls.OUTGOING_TYPE:
//                    dir = "OUTGOING";
//                    break;
//                case CallLog.Calls.INCOMING_TYPE:
//                    dir = "INCOMING";
//                    break;
//                case CallLog.Calls.MISSED_TYPE:
//                    dir = "MISSED";
//                    break;
//            }
//            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- "
//                    + callDayTime + " \nCall duration in sec :--- " + callDuration+ " \nLocation of other number: ---" + locationOfNumber +
//                        " \nAccount Name : --- : " + cachedName + " \nMy location: --- "+ countryS );
//            sb.append("\n----------------------------------");
//        } //managedCursor.close();
//
//        textView.setText(sb);
    }

    public void showInternetTrafficStats(View v){

        Intent i = new Intent(this,TrafficMonitorActivity.class);
        startActivity(i);
    }



    public void requestAllPermissions(){

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALL_LOG},
                        MY_PERMISSIONS_REQUEST_READ_CALL_LOG);

                     Log.d(TAG, "reqeuestig pemission call log");

        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
            Log.d(TAG, "reqeuestig pemission coarse");

        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            Log.d(TAG, "reqeuestig pemission fine");
        }
        Log.d(TAG, "permissions already granted");
    }


    // TODO: 19/04/16 Not doing anything for now - in case needed implement. Assumption: user will grant permissions 
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALL_LOG: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, " call log granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_COARSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "coarse granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "fine granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
