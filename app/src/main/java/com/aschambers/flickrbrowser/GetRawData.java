package com.aschambers.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

enum DownloadStatus { IDLE, PROCESSING, NOT_INITIALIZED, FAILED_OR_EMPTY, OK }

/**
 * Created by metabou on 1/25/18.
 */

class GetRawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";

    // m stands for member variable in mDownloadStatus
    private DownloadStatus mDownloadStatus;

    public GetRawData() {
        // this is supposed to remove ambiguity, but mDownloadStatus has to only refer to field
        // since no parameters passed to this constructor method, this not needed
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: parameter = " + s);
        // super.onPostExecute(s); -> not needed, doesn't do anything for us
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        if(strings == null) {
            mDownloadStatus = DownloadStatus.NOT_INITIALIZED;
            return null;
        }

        // log e logs a message in logcat (shouldn't get these messages ever, unless we did something wrong)
        // log d compiles at runtime, and won't be displayed in app on app store
        try {
            mDownloadStatus = DownloadStatus.PROCESSING;
            // attempt to create a URL from the string parameter, should only be one passed
            // so we get the first one which is array of 0, and then open a connection
            URL url = new URL(strings[0]);

            // use an http get request to get the JSON data from a url
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            // log response code to see if error connecting the URL was found
            Log.d(TAG, "doInBackground: The response code was " + response);

            StringBuilder result = new StringBuilder();

            // bufferedReader reads data from the inputStream and keeps reading until no more data to read
            // adds the data to the string builder
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // reads one line at a time
            String line;

            // reason we are putting null first, is so the code is forced to pause and check line for null
            while(null != (line = reader.readLine())) {
                // new line characters are stripped off the end of each line so we have to append the new
                // line characters back in, since we are reading text data, reading one line at a time makes sense
                result.append(line).append("\n");
            }

            // if no exception throw, we set status to OK and return the string that has been built up
            mDownloadStatus = DownloadStatus.OK;
            return result.toString();

        // check for any problems with URL, not valid JSON?, etc.
        } catch(MalformedURLException e) {
            Log.e(TAG, "doInBackground: Invalid URL " + e.getMessage());
        } catch(IOException e) {
            Log.e(TAG, "doInBackground: IO Exception reading data: " + e.getMessage());
        } catch(SecurityException e) {
            Log.e(TAG, "doInBackground: Security Exception. Needs permission? " + e.getMessage());
        // executed just before method returns, and always executed but the code
        // will go back to the return statement if there is one outstanding
        // if there is an exception it will set the download status to FAILED_OR_EMPTY
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch(IOException e) {
                    Log.e(TAG, "doInBackground: Error closing stream" + e.getMessage());
                }
            }
        }

        // if we get this far down, there is a problem with the code
        mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY
        return null;
    }
}
