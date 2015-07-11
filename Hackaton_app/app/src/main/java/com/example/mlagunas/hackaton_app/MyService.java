package com.example.mlagunas.hackaton_app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


/**
 * Created by mlagunas on 11/07/15.
 */
public class MyService extends Service {
    public MyService() {
    }

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }





    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            try{
                for (String url : urls) {
                    DataGetter dg = new DataGetter(url, 285);
                    response =  dg.GetData("Select * from result;");

                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                ArrayList<String> data = getList(result);
                final Intent send = new Intent("webpage");
                send.putExtra("txt", data);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(send);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        private ArrayList<String>  getList(String JSONtxt) throws JSONException{
            JSONArray array = new JSONArray(JSONtxt);
            ArrayList<String> data = new ArrayList<String>();
            for (int i = 0; i<array.length(); i++){
                String name = array.getJSONObject(i).getString("name");
                data.add(name);
            }
            Log.d("final", data.toString());
            return data;

        }
    }


    public void download() {
        //textView = (TextView) findViewById(R.id.TextView1);

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[]{"http://www.iescitie.com:8080/IESCities/api"});


    }

}
