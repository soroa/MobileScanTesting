package com.example.andreasoro.mobilescantesting.logging;

/**
 * Created by andisoro on 17/04/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LogDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "mobile_scan_testing";
    private static final int DB_VERSION = 2;

    private static final String C_TABLE = "calls";
    private static final String C_ID = "id";
    private static final String C_CREATION_TIME = "creation_time";
    private static final String C_DURATION = "duration";
    private static final String C_NUMBER = "number";
    private static final String C_CONTACT = "contact";
    private static final String C_TYPE = "type";
    private static final String C_LOCATION = "creation_time";
    private static final String C_LOCATION_OF_NUMBER = "location_of_number";

    private static final String MD_TABLE = "data";
    private static final String MD_ID = "id";
    private static final String MD_CREATION_TIME = "creation_time";
    private static final String MD_WHATSAPP = "whatsappBytes";
    private static final String MD_TOTAL = "totalMB";
    private static final String MD_NON_WHATSAPP = "no_whatsappBytes";
    private static final String MD_LONG = "longitude";
    private static final String MD_LAT = "latitude";


    public LogDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + C_TABLE + " (" +
                C_ID + " INTEGER PRIMARY KEY," +
                C_CREATION_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                C_DURATION + " INTEGER," +
                C_CONTACT + " VARCHAR(50)," +
                C_LOCATION_OF_NUMBER + " VARCHAR(30)," +
                C_LOCATION + " VARCHAR(30)," +
                C_NUMBER + " VARCHAR(20)," +
                C_TYPE + " INTEGER" +
                ");");

        db.execSQL("CREATE TABLE " + MD_TABLE + " (" +
                MD_ID + " INTEGER PRIMARY KEY," +
                MD_CREATION_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                MD_WHATSAPP + " INTEGER," +
                MD_NON_WHATSAPP + " INTEGER," +
                MD_TOTAL + " INTEGER," +
                MD_LAT + " INTEGER," +
                MD_LONG + " INTEGER" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Upgrade schema
        reset(db);
    }

    public void reset() {
        reset(this.getWritableDatabase());
    }

    public long insertCall(Call call) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(C_CONTACT, call.getContactName());
        contentValues.put(C_DURATION, call.getDuration());
        contentValues.put(C_NUMBER, call.getNumber());
        contentValues.put(C_LOCATION_OF_NUMBER, call.getLocationOfOtherNumber());
        contentValues.put(C_TYPE, call.getType());
        contentValues.put(C_LOCATION, call.getLocationOfThisNumber());

        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(C_TABLE, null, contentValues);
        db.close();

        return id;
    }

    public long insertMobileData(MobileData mobData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MD_LAT, mobData.getLatitude());
        contentValues.put(MD_LONG, mobData.getLongitude());
        contentValues.put(MD_NON_WHATSAPP, mobData.getNonWhatsappData());
        contentValues.put(MD_WHATSAPP, mobData.getWhatsappData());
        contentValues.put(MD_TOTAL, mobData.getLatitude());


        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(MD_TABLE, null, contentValues);
        db.close();
        return id;
    }


    private void reset(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + C_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MD_TABLE);
        onCreate(db);
    }
}
