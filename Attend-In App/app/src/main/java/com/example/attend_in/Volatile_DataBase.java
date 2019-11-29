package com.example.attend_in;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class Volatile_DataBase extends Activity {

    public static String[] list = new String[10];
    public static String title1 = "";


    public static void addList(int x, String y) {

        list[x] = y;
    }

    public static String getClassList(int x){

        String message = "Loop Error";
        if (x <= 10){
            return list[x];
        }
        else{
            return valueOf(x);
        }
    }

    public static void storeTitle(String x){
         title1 = x;
    }

    public static int listLength(){
        return list.length;
    }

    public static String updateTextView(String x){
        return title1;

    }
}
