package com.example.andreasoro.mobilescantesting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by andreasoro on 09/04/16.
 */
public class MobileTrafficReader extends BroadcastReceiver {



    private boolean isConnectedToMobileNetwork(Context cntx) {

        ConnectivityManager cm =
                (ConnectivityManager) cntx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        return isConnected && isMobile;
    }


    private boolean isConnectedToWifiNetwork(Context cntx) {

        ConnectivityManager cm =
                (ConnectivityManager) cntx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        return isConnected && isWifi;
    }


    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
        String connType = isConnectedToMobileNetwork(context) ? "mobile " : "wifi";

        Log.d("Andrea", "connectivity changed and connection type is " + connType);
        if (isConnectedToWifiNetwork(context)) {
            long end = getSessionWhatsAppData(context);
            String log = FileWriteRead.readLogFile(context);
            String[] lines = log.split(System.getProperty("line.separator"));
            long start = Long.parseLong(lines[0]);
            long total = Long.parseLong(lines[2]);
            long newTotal = total + (end - start)/2;
            Log.d("Andrea", "start = " + start + "\n" +
                    "end= "+ end +"\n" + "total= " + total);
            FileWriteRead.writeLogFile( Long.toString(start)+"\n" +Long.toString(end)+"\n"+ Long.toString(newTotal), context);


        } else if (isConnectedToMobileNetwork(context)) {
            String log = FileWriteRead.readLogFile(context);
            String[] lines = log.split(System.getProperty("line.separator"));
            long start =getSessionWhatsAppData(context);
            FileWriteRead.writeLogFile(Long.toString(start)+ "\n" + "\n"+ lines[2], context);

        }
    }



    public long getSessionWhatsAppData(Context ctx) {
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

    public long getSessionTotalData(Context ctx) {
        return TrafficStats.getTotalTxBytes() + TrafficStats.getTotalRxBytes();
    }




}
