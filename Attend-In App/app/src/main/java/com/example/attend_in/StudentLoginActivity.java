package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLoginActivity extends AppCompatActivity {
    EditText usernameText, passwordText;
    Button login;
    private static final String prefs_file = "LoginInfo";
    private static final String userName = "Username";
    private static final String password = "Password";
    SharedPreferences loginPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        usernameText = (EditText)findViewById(R.id.studentUserName);
        passwordText = (EditText)findViewById(R.id.studentPassword);
        login = (Button)findViewById(R.id.studentloginbutton);
        loginPrefs = getSharedPreferences(prefs_file, Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String user = usernameText.getText().toString();
                String pass = passwordText.getText().toString();
                SharedPreferences.Editor editor = loginPrefs.edit();
                editor.putString(userName, user);
                editor.putString(password, pass);
                editor.commit();
                String u = loginPrefs.getString("Username", null).toString();
                String p = loginPrefs.getString("Password", null).toString();
                Toast.makeText(getApplicationContext(), u +" "+p, Toast.LENGTH_LONG).show();
            }
        });


    }
}
