package com.example.attend_in;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

            }
        });


    }

    protected static String generateMD5(String uname, String pword) {
        String hash = "";
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return hash;
    }


}

