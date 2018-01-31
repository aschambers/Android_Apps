package com.aschambers.top10downloaded;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    // onCreate doesn't wait for the background AsyncTask method to finish
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: starting AsyncTask");
        // create an instance of DownloadData which extends AsyncTask
        DownloadData downloadData = new DownloadData();
        // use AsyncTask to pull URL data
        downloadData.execute("URL goes here");
        Log.d(TAG, "onCreate: done");
    }

    // taking advantage of asynchronous processing
    // 3 types: <Params, Progress, Result>, and 4 steps: onPreExecute, doInBackground, onProgressUpdate, onPostExecute
    // 1. We are providing a URL as the String (Params)
    // 2. We are using void since don't need a progressBar unless larges amount of data (progress), marked as unused (Void)
    // 3. String we want to get back (result)
    // must be loaded on the UI thread, done automatically in Jelly Bean
    // The task instance must be created on the UI thread.
    // execute(Params...) must be invoked on the UI thread.
    // The task can be executed only once (an exception will be thrown if a second execution is attempted.)
    // Android Framework is doing multithreading, handling all the work for us
    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";

        // goes through 4 steps when executed
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // invoked immediately after onPreExecute, used to perform background computation that may take a while
        @Override
        protected String doInBackground(String... strings) {
//            return null;
            Log.d(TAG, "doInBackground: start with " + strings[0]);
            return "doInBackground completed.";
        }

        // used to display any form of progress, we aren't using this since progress is void
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        // result of background computation passed as a paramater to this step
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: paramater is" + s);
        }
        // end of 4 steps
    }
}
