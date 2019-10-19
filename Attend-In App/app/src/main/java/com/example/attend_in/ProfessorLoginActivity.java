package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class ProfessorLoginActivity extends AppCompatActivity {
    EditText usernameText, passwordText;
    Button login, testTimer;
    sendAPIRequest requestAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_login);
        testTimer = (Button) findViewById(R.id.testTimerButton);
        testTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TeacherTimerActivity.class); // so it can go to the second activity screen once you click on the Attend in button
                startActivity(startIntent);

            }
        });
    }
}
