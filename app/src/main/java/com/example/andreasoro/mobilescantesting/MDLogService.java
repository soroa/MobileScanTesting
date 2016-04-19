package com.example.andreasoro.mobilescantesting;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.andreasoro.mobilescantesting.logging.LogDatabase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by andisoro on 17/04/16.
 */
public class MDLogService extends Service implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    public static final String TAG = MDLogService.class.getSimpleName();
    public static boolean sStarted = false;
    private LogDatabase myLogDatabase;
    private MobileTrafficReader mMobileTrafficReader;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;



    @Override
    public void onCreate() {
        Log.d(TAG, "Creating Service");
        myLogDatabase = new LogDatabase(getApplicationContext());
        Log.d(TAG, "DB Created");
        mMobileTrafficReader = new MobileTrafficReader(this, getApplicationContext());

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//        if (!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.connect();
//        }

    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId){

        sStarted = true;


    // Start sticky
    return START_STICKY;
    }

    protected void addMobileDataSessionToDatabase(long whatsappEndValue, long totalMDEndValue){
        // TODO: 17/04/16 finish


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }



    @Override
    public void onConnected(Bundle connectionHint) {
        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(30 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,(LocationListener)mMobileTrafficReader);
    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // TODO: May not be a NOP
    }


    @Override
    public void onDestroy() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not implemented");
    }


}
