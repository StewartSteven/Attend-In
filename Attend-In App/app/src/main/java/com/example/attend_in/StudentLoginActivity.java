package com.example.attend_in;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.out;

public class StudentLoginActivity extends AppCompatActivity {
    EditText usernameText, passwordText;
    Button login, testApi;
    final String ipStackKey = "e5000ae47a9b292155c2db262da51162";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        usernameText = (EditText) findViewById(R.id.studentUserName);
        passwordText = (EditText) findViewById(R.id.studentPassword);
        testApi = (Button) findViewById(R.id.testApiButton);
        login = (Button) findViewById(R.id.studentloginbutton);

        testApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameText.getText().toString();
                String pass = passwordText.getText().toString();
                String hash = generateMD5(user, pass);
                String testUrl = "http://attend-in.com/test_script.php";
                String ipStackUrl = "http://api.ipstack.com/check?access_key=" + ipStackKey;
                String testApiUrl = testUrl + "?" + "username=" + user +"&" + "password=" + pass;

                //getApiRequest(testApiUrl);
                getApiRequest(ipStackUrl);






            }
        });


    }

    protected static String generateMD5(String uname, String pword) {
        //Concatenates the username and password together; Using a colon to separate them
        String combo = uname + ":" + pword;
        String hash = "";
        MessageDigest md = null;

        //Creates the hash, surrounding it with a Try - Catch to check for a
        //NoSuchAlgorithm Exception
        try{md = MessageDigest.getInstance("MD5");
            //Uses update to hash the string (in bytes)
            md.update(combo.getBytes());
            //Uses digest to perform final calculations
            //Returns a byte array
            byte msgDigest[] = md.digest();

            //Creates a Hex String
            StringBuffer hexHash = new StringBuffer();
            for(int i = 0; i < msgDigest.length; i++){
               // hexHash.append(Integer.toHexString((msgDigest[i] & 0xFF) | 0x100).substring(1,3));
                hexHash.append(msgDigest[i]);
            }
            hash = hexHash.toString();
            return hash;
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return "";
        }



    }
        protected void getApiRequest(String request) {
            class CallApi extends AsyncTask<String, String, String> {
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
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                }
            }

            new CallApi().execute(request);
        }



}

