package com.example.attend_in;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.util.JsonReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Instant;
import java.io.IOException;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.widget.TextView;

public class TeacherTimerActivity extends AppCompatActivity {
    Button timerButton;
    TextView timerText;
    CountDownTimer countdown;

    int timeRunning = 60000;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_professor_timer);
        verifyStoragePermissions(this);
        timerButton = (Button) findViewById(R.id.triggerTimerButton);
        timerText = (TextView) findViewById(R.id.timerText);
        timerButton.setTag(0);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status =(Integer) v.getTag();
                if(status == 1) {
                    timerButton.setText("Start");
                    v.setTag(0);
                    countdown.cancel();
                    timeRunning = 60000;
                    update();
                    setTimeEnd();
                } else {
                    timerButton.setText("End");
                    v.setTag(1);
                    setTimeStart();
                }
            }
        });
    }

    protected static void setTimeEnd() {
        try {
            Instant timeStart = null;

            String readpath = Environment.getExternalStorageDirectory().toString()+"/download";
            JsonReader jsonParser = new JsonReader(new FileReader(new File(readpath, "time.txt")));
            jsonParser.beginObject();
            while(jsonParser.hasNext()){
                String key = jsonParser.nextName();
                if (key.equals("timeStart")){
                    timeStart = Instant.parse(jsonParser.nextString());
                }
                else{
                    jsonParser.skipValue();
                }
            }
            jsonParser.endObject();
            jsonParser.close();

            JSONObject JSON = makeJSONObject(timeStart, true);

            String path = Environment.getExternalStorageDirectory().toString()+"/download";
            File file = new File(path, "time.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file, false));
            output.write(JSON.toString());
            output.close();
            Log.d("success", "logged in " + path);

        } catch (IOException e) {
            Log.e("Exception", "IOException: " + e.toString());
        }

    }
    protected void setTimeStart() {
        try {
            JSONObject JSON = makeJSONObject(Instant.now(), false);
            String path = Environment.getExternalStorageDirectory().toString()+"/download";
            File file = new File(path, "time.txt");
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file,false));
            output.write(JSON.toString());
            output.close();
            countdown = new CountDownTimer(timeRunning, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeRunning = (int) millisUntilFinished;
                    update();
                }

                @Override
                public void onFinish() {
                    timerButton.setText("Start");
                    setTimeEnd();
                    timeRunning = 60000;
                }
            };
            countdown.start();

            Log.d("success", "logged in " + path);
        }
        catch (IOException e) {
            Log.e("Exception", "IOException: " + e.toString());
        }
    }
    public static JSONObject makeJSONObject (Instant timeStart, boolean timeEnd) {
        JSONObject obj = new JSONObject() ;
        try {
            obj.put("timeStart", timeStart);
            obj.put("timeEnd", timeEnd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private void update() {
        int minutes = (int) (timeRunning / 1000) / 60;
        int seconds = (int) (timeRunning / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timerText.setText(timeLeft);
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}