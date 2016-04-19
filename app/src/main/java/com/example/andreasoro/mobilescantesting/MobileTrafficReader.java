package com.example.andreasoro.mobilescantesting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Bundle;
import android.util.Log;

import com.example.andreasoro.mobilescantesting.logging.MobileData;

/**
 * Created by andreasoro on 09/04/16.
 */
public class MobileTrafficReader extends BroadcastReceiver implements LocationListener {

    public static final String TAG = MDLogService.class.getSimpleName();
    private Location lastLocation;
    private MDLogService mMBLogService;
    private long lastwhatsAppStartValue;
    private long lastTotalMDStartValue;
    private boolean mobileDataIsOn;

    public MobileTrafficReader() {

    }

    public MobileTrafficReader(MDLogService mMBLogService, Context ctx) {
        this.mMBLogService = mMBLogService;
        if (isConnectedToMobileNetwork(ctx)) {
            this.mobileDataIsOn = true;
            this.lastwhatsAppStartValue = getWhatsappData(ctx);
            this.lastTotalMDStartValue = getTotalMobileData(ctx);
        } else {
            this.mobileDataIsOn = false;
        }

    }

    private boolean isConnectedToMobileNetwork(Context ctx) {

        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        return isConnected && isMobile;
    }


    private boolean isConnectedToWifiNetwork(Context ctx) {

        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        return isConnected && isWifi;
    }


    private boolean isNotConnectedToNetwork(Context ctx) {
        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return !isConnected;


    }


    @Override
    public void onReceive(Context context, Intent intent) {


        if (isConnectedToWifiNetwork(context) || isNotConnectedToNetwork(context)) {
            if (this.mobileDataIsOn) {
                this.mobileDataIsOn = false;
                processDataSession();


            }


        } else if (isConnectedToMobileNetwork(context)) {
            if (!this.mobileDataIsOn) {
                this.mobileDataIsOn = true;

                this.lastwhatsAppStartValue = getWhatsappData(context);
                this.lastTotalMDStartValue = getTotalMobileData(context);
            }
        }
    }

    private MobileData processDataSession() {

        //long w_end = getWhatsappData(context);
        //long total_end = getTotalMobileData(context);

//// TODO: 17/04/16 Creaate MobileData Object
        // mMBLogService.addMobileDataSessionToDatabase(w_end, total_end);

        return new MobileData(0,0,0,0,0);
    }


    public long getWhatsappData(Context ctx) {
        int whatsappUID = -1;
        for (ApplicationInfo app :
                ctx.getPackageManager().getInstalledApplications(0)) {
            if (app.packageName.equals("com.whatsapp")) {
                whatsappUID = app.uid;
                Log.d("Andrea", " whatsapp app package found");

            }

        }
        return TrafficStats.getUidTxBytes(whatsappUID) + TrafficStats.getUidRxBytes(whatsappUID);

    }

    public long getTotalMobileData(Context ctx) {
        return TrafficStats.getTotalTxBytes() + TrafficStats.getTotalRxBytes();
    }


    @Override
    public void onLocationChanged(final Location loc) {


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
