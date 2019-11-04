package com.example.attend_in;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class StudentLoginActivity extends AppCompatActivity {
    EditText usernameText, passwordText;
    Button login, testApi;
    sendAPIRequest requestAPI;
    private static final String prefs_file = "LoginInfo";
    private static final String userName = "Username";
    private static final String password = "Password";
    private final String ipStackKey = "e5000ae47a9b292155c2db262da51162";
    SharedPreferences loginPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        usernameText = (EditText) findViewById(R.id.studentUserName);
        passwordText = (EditText) findViewById(R.id.studentPassword);
        testApi = (Button) findViewById(R.id.testApiButton);
        login = (Button) findViewById(R.id.studentloginbutton);
        loginPrefs = getSharedPreferences(prefs_file, Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {   //Takes user name and password and toasted it at botton of screen after you click login
            @Override
            public void onClick(View v){
                String user = usernameText.getText().toString();
                String pass = passwordText.getText().toString();
                SharedPreferences.Editor editor = loginPrefs.edit();
                editor.putString(userName, user);
                editor.putString(password, pass);
                editor.commit();
                String u = loginPrefs.getString("Username", null);
                String p = loginPrefs.getString("Password", null);
                Toast.makeText(getApplicationContext(), u + " " +p, Toast.LENGTH_LONG).show();

            }
        }
        );


        testApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameText.getText().toString();
                String pass = passwordText.getText().toString();
                String hash = generateMD5(user, pass);
                String testUrl = "http://attend-in.com/test_script.php";
                String ipStackUrl = "http://api.ipstack.com/check?access_key=" + ipStackKey;
                String testApiUrl = testUrl + "?username=" + user +"&password=" + pass + "&hash=" + hash;
                String test;
                requestAPI = new sendAPIRequest(getApplicationContext());
                requestAPI.execute(ipStackUrl);
                test = requestAPI.getResponse();
                Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();
               /* requestAPI = new sendAPIRequest(getApplicationContext());
                requestAPI.execute(testApiUrl);
                test = requestAPI.getResponse();
                Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();*/
                

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




}

