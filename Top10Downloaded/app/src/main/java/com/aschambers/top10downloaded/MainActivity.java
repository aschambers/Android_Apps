package com.aschambers.top10downloaded;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
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
        // runs on a background thread
        @Override
        protected String doInBackground(String... strings) {
//            return null;
            Log.d(TAG, "doInBackground: start with " + strings[0]);
            String rssFeed = downloadXML(strings[0]);
            if(rssFeed == null) {
                Log.e(TAG, "doInBackground: Error downloading");
            }
            return rssFeed;
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
            ParseApplications parseApplications = new ParseApplications();
            // s is the xml the application has sent to this method
            parseApplications.parse(s);
        }
        // end of 4 steps

        // bufferedReader is a typical approach for reading a bunch of data from a URL
        // buffers the data coming in from the stream, so our program can read data from the buffer

        private String downloadXML(String urlPath) {
            // going to be appending characters from the input stream, more efficient than concat
            StringBuilder xmlResult = new StringBuilder();

            // connection may be slow, down, stream be down, etc, would prevent connection from being made
            // wrap of code and try, else catch errors
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d(TAG, "downloadXML: The response code was " + response);
                // if valid response, i.e. 200, read response, use inputStream to create a bufferedReader
//                InputStream inputStream = connection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                // replaces three lines of code above
                // closing bufferedReader will close the inputStreamReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // reads characters, not strings, so set up character array next

                int charsRead;
                // if you are expecting a larger download, increasing 500 would increase performance
                // reads 500 characters at a time
                char[] inputBuffer = new char[500];
                // keep reading until end of inputStream
                while(true) {
                    charsRead = reader.read(inputBuffer);
                    if(charsRead < 0) {
                        break;
                    }
                    // only append result if something is read
                    if(charsRead > 0) {
                        xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
                    }
                }
                // after getting result, close the bufferedReader, will close other IO objects
                reader.close();
                // converts result to string, and returns it
                return xmlResult.toString();
            // order you catch exceptions is important
            } catch(MalformedURLException e) {
                Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
            // extended from MalformedURLException, which is why it needs to be after MalformedURLException catch error method
            } catch(IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data" + e.getMessage());
            } catch(SecurityException e) {
                Log.e(TAG, "downloadXML: Security Exception, needs permission." + e.getMessage());
                // only print stack trace in catch block, to catch actual exception, good for debugging
//                e.printStackTrace();
            }

            return null;
        }
    }
}
