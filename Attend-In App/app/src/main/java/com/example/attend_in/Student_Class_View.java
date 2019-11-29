package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Class_View extends Activity implements OnClickListener {
    private ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<String>();
    TextView lst;
    String title1 = "";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__class__view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, pClassList);
        lv = (ListView)findViewById(R.id.s_listView);
        lv.setAdapter(adapter);
        Volatile_DataBase.addList(0, "CSC 1101");
        Volatile_DataBase.addList(1, "CSC 1102");
        Volatile_DataBase.addList(2, "CSC 1103");
        int x = Volatile_DataBase.listLength();
        int i = 0;
        while (Volatile_DataBase.getClassList(i) != null && i < 10){
            adapter.add(Volatile_DataBase.getClassList(i));
            i++;
        }

        lv.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), Volatile_DataBase.getClassList(position), Toast.LENGTH_SHORT).show();
                TextView title = (TextView) findViewById(R.id.dynamic_title);
                String text = "";
                text = Volatile_DataBase.getClassList(position);
                Volatile_DataBase.storeTitle(text);
                Intent startIntent = new Intent(getApplicationContext(), Dynamic_Class_View.class); // so it can go to the second activity screen once you click on the Attend in button
                startActivity(startIntent);
            }
        });

    }

    public void onClick(View v) {






    }
}
