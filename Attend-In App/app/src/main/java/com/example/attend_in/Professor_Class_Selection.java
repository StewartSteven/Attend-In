package com.example.attend_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;



public class Professor_Class_Selection extends Activity implements OnClickListener {
    private Button btnAdd;
    private EditText et;
    private ListView lv1;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private SQLiteDatabase db;
    String[] pClassList = new String[10];
    int x = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor__class__selection);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        btnAdd = (Button) findViewById(R.id.pAddClassBtn);
        btnAdd.setOnClickListener(this);
        lv1 = (ListView)findViewById(R.id.pClassList);
        lv1.setAdapter(adapter);
        et = (EditText) findViewById(R.id.editText);

        lv1.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), Volatile_DataBase.getClassList(position), Toast.LENGTH_SHORT).show();
                TextView title = (TextView) findViewById(R.id.dynamic_title);
                int num = position + 3;
                String text = Volatile_DataBase.getClassList(num);
                Volatile_DataBase.storeTitle(text);
                Intent startIntent = new Intent(getApplicationContext(), Dynamic_Class_View.class); // so it can go to the second activity screen once you click on the Attend in button
                startActivity(startIntent);
            }
        });

    }

    public void onClick(View v) {
        String input = et.getText().toString();
        String message1 = "Please enter class name.";
        String message2 = "You have met the max number of classes.";

        if (input.length() > 0 && x < pClassList.length){
            adapter.add(input);
            Volatile_DataBase.addList(x, input);
            x++;
        }
        if(input.length() <= 0) {
            Toast.makeText(getApplicationContext(), message1, Toast.LENGTH_LONG).show();
        }
        if(x >= pClassList.length){
        Toast.makeText(getApplicationContext(), message2, Toast.LENGTH_LONG).show();
        }



    }
}