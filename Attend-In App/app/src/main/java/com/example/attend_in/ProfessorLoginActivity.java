package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ProfessorLoginActivity extends AppCompatActivity {
    EditText usernameText, passwordText;
    Button login;
    private static final String prefs_file = "LoginInfo";
    private static final String userName = "uname";
    private static final String password = "pword";
    SharedPreferences loginPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameText = (EditText)findViewById(R.id.professorUserName);
        passwordText = (EditText)findViewById(R.id.professorPassword);
        login = (Button)findViewById(R.id.loginbutton);
        setContentView(R.layout.activity_professor_login);
    }
}
