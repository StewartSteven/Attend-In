package com.example.attend_in;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class sendAPIRequest extends AsyncTask<String, String, String> {
    /*
    This is the class used to send API requests.
    It extends the AsyncTask generic class that is used to send a request in parallel to the main application.
    In order to be properly utilized, during instantiation the parameter to the constructor must be the current application's context
    In order to execute the request the execute method must be called, with the following parameters:
        The Url that is to receive the request, which must include the query string (for now)
    It has one field:
        Context context, which is used to specify the application context during instantiation by calling getApplicationContext()
      */

    private Context context;

    public sendAPIRequest(Context context){
        //In order to properly use this class, you need to pass the current application's context using
        // getApplicationContext() as the parameter to the constructor
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {
        //This method simply sends a http request to the url specified and returns a string to the onPostExecute method
        String urlString = params[0];
        BufferedReader reader = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream stream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "/n");
                Log.d("Response: ", "> " + line);

            }

            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
    public String getResponse(){
        //returns the response from the request
        try {
            return this.get();
        }catch  (InterruptedException e){
            return "";
        }catch (ExecutionException e){
            return "";
        }
    }

}

