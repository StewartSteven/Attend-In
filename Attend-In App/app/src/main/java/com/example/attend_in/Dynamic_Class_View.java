package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;


public class Dynamic_Class_View extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    String header = "";
    String message = "";


    public int counter = 15;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic__class__view);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, header);
        TextView title2  = (TextView) findViewById(R.id.dynamic_title);

        textView = (TextView) findViewById(R.id.display_timer) ;

        title2.setText(Volatile_DataBase.updateTextView(header));
        TextView title3  = (TextView) findViewById(R.id.message_1);
        title3.setText(Volatile_DataBase.viewMessage());

        if (Volatile_DataBase.viewValue() == 0) {
            new CountDownTimer(15000, 1000) {
                public void onTick(long millisUntilFinished) {
                    textView.setText(String.valueOf(counter));
                    counter--;
                }

                public void onFinish() {
                    textView.setText("FINISH!!");
                    String x = "Attendance Timer Ended!";
                    Toast.makeText(getApplicationContext(), x, Toast.LENGTH_LONG).show();
                    Dynamic_Class_View.super.onBackPressed();

                    //Intent startIntent = new Intent(getApplicationContext(), Professor_Class_Selection.class);
                    //startActivity(startIntent);
                }
            }.start();
        }



    }


    /*public void onClick(View v) {
        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){
                textView.setText("FINISH!!");
            }
        }.start();

    }; */

}
