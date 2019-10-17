package com.example.attend_in;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StudentLoginActivity extends AppCompatActivity {
    EditText usernameText, passwordText;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        login = (Button) findViewById(R.id.studentloginbutton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameText.getText().toString();
                String pass = passwordText.getText().toString();
                String hash = generateMD5(user, pass);
                String url = "http://attend-in.com/test_script.php";
                String apiUrl = url + "?" + "username=" + user +"&" + "password=" + pass;
                sendRequest(apiUrl);





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
    private void sendRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jrequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(getApplicationContext(), response.getString(""), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", "response");

                    }
                }
    );
    }


}

