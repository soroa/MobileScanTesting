package com.example.andreasoro.mobilescantesting;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by andisoro on 12/04/16.
 */
public class FileWriteRead {

    // write text to file
    public static void writeLogFile(String s, Context ctx) {
        String dataLogFileName = "dataLog";
        try {

            FileOutputStream fOut = ctx.openFileOutput(dataLogFileName,ctx.MODE_PRIVATE);
            fOut.write(s.getBytes());
            fOut.close();
            Log.d("Andrea", "File Written correctly. String written -" + s + "-");

        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // read text to file
    public static String readLogFile( Context ctx) {
        String dataLogFileName = "dataLog";
        String ret="";
        try {
            InputStream inputStream = ctx.openFileInput(dataLogFileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString + "\n");
                }

                inputStream.close();
                ret = stringBuilder.toString();
                Log.d("Andrea", "File Read correctly.String read: -" + ret+ "-");
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Andrea", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Andrea", "Can not read file: " + e.toString());
        }

        return ret;
    }

}
