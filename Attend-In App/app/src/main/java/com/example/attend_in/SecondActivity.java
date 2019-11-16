package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button thirdActivityBtn = (Button) findViewById(R.id.professorLoginScreenBtn); //creates the button called thirdActivityBtn and gets the refrence I.D. for the professor login button button
        Button forthActivityBtn = (Button) findViewById(R.id.studentLoginScreenBtn);    //same as above
        thirdActivityBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ProfessorLoginActivity.class); // so it can go to the third activity screen once you click on the Attend in button
                startActivity(startIntent);

            }
        });
        forthActivityBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), StudentLoginActivity.class); // so it can go to the forth activity screen once you click on the Attend in button
                startActivity(startIntent);
            }
        });
    }
}
