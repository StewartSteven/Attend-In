package com.example.attend_in;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class Volatile_DataBase extends Activity {

    public static String[] list = new String[10];
    public static String title1 = "";
    public static String s_name ="";
    public static String p_name = "";
    public static String message = "";
    public static int view = 0;

    public static void studentName(String x){
        s_name = x;
    }

    public static void professorName(String x){
        p_name = x;
    }


    public static void storeValue(int x){
        view = x;
    }

    public static String viewMessage(){
        if (view == 1){
            message = s_name + ", you are marked present.";
            return message;
        }
        if (view == 0){
            message = p_name + ", students attending this class has 10 mins to attend. \nAfter that they will be considered late.";
            return message;
        }
        else{
            message = "Error";
            return message;
        }

    }

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
