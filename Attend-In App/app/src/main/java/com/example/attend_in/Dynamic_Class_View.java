package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class Dynamic_Class_View extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    String header = "";
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic__class__view);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, header);
        TextView title2  = (TextView) findViewById(R.id.dynamic_title);
        title2.setText(Volatile_DataBase.updateTextView(header));
        TextView title3  = (TextView) findViewById(R.id.message_1);
        title3.setText(Volatile_DataBase.viewMessage());
    }
}
