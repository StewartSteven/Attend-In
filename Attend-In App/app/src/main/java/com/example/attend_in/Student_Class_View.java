package com.example.attend_in;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.view.View.OnClickListener;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.EditText;

public class Student_Class_View extends Activity implements OnClickListener {
    private EditText et;
    private ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<String>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__class__view);

        // tried to grab classes created form professor create class list

        /*et = (EditText) findViewById(R.id.editText);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        lv = (ListView) findViewById(R.id.pClassList);
        lv.setAdapter(adapter);*/

    }

    public void onClick(View v) {
        String input = et.getText().toString();
        if (input.length() > 0) {
            // add string to the adapter, not the listview
            adapter.add(input);
            // no need to call adapter.notifyDataSetChanged(); as it is done by the adapter.add() method
        }
    }
}
