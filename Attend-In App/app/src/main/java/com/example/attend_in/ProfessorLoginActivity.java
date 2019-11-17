package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ProfessorLoginActivity extends AppCompatActivity {

    EditText usernameText, passwordText;
    Button login;
    private static final String prefs_file = "LoginInfo";
    private static final String userName = "Username";
    private static final String password = "Password";
    private final String ipStackKey = "e5000ae47a9b292155c2db262da51162";
    SharedPreferences loginPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_login);
        usernameText = (EditText) findViewById(R.id.professorUserName);
        passwordText = (EditText) findViewById(R.id.professorPassword);
        login = (Button) findViewById(R.id.professorLoginButton);
        loginPrefs = getSharedPreferences(prefs_file, Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {   //Takes user name and password and toasted it at botton of screen after you click login
                                     @Override
                                     public void onClick(View v) {
                                         String user = usernameText.getText().toString();
                                         String pass = passwordText.getText().toString();
                                         SharedPreferences.Editor editor = loginPrefs.edit();
                                         editor.putString(userName, user);
                                         editor.putString(password, pass);
                                         editor.apply();
                                         String u = loginPrefs.getString("Username", null).toString();
                                         String p = loginPrefs.getString("Password", null).toString();
                                         //Toast.makeText(getApplicationContext(), u + " " + p, Toast.LENGTH_LONG).show();
                                         Intent startIntent = new Intent(getApplicationContext(), Professor_Class_Selection.class); // so it can go to the second activity screen once you click on the Attend in button
                                         startActivity(startIntent);



                                     }
                                 }
        );
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