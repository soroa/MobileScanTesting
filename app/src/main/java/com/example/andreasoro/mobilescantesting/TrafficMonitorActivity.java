package com.example.andreasoro.mobilescantesting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TrafficMonitorActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_monitor);
        TextView whatsappUsageView = (TextView) findViewById(R.id.whatsapp_usage_view);
        String log = FileWriteRead.readLogFile(this);
        String[] lines = log.split(System.getProperty("line.separator"));
        whatsappUsageView.setText("Whatsapp Mobile Data Consumption: " + lines[2] + "bytes");
    }


}