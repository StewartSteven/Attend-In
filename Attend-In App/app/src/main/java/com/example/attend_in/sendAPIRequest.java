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
    It has two fields:
        Context context, which is used to specify the application context during instantiation by calling getApplicationContext()
        String requestResult, which is used to hold and return the response from the request
      */

    private Context context;

    public sendAPIRequest(Context context){
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {
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
        try {
            return this.get();
        }catch  (InterruptedException e){
            return "";
        }catch (ExecutionException e){
            return "";
        }
    }

}

