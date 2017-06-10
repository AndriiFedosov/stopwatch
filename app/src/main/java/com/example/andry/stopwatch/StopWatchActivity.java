package com.example.andry.stopwatch;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StopWatchActivity extends AppCompatActivity {

    private int second =0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if (savedInstanceState!=null){
            second =savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds",second);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    @Override
    protected void onStop(){
        super.onStop();
        wasRunning=running;
        running=false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning){
            running=true;
        }
    }

    public void OnClickStart(View view) {
        running=true;
    }

    public void OnClickStop(View view) {
        running=false;
    }

    public void OnClickReset(View view) {
        running=false;
        second=0;
    }
    public void runTimer(){
         final TextView textView = (TextView)findViewById(R.id.time_view);
         final Handler handler = new Handler();
         handler.post(new Runnable() {
             @Override
             public void run() {
                 int hours = second/3600;
                 int minutes = (second%3600)/60;
                 int seconds = second%60;
                 String time = String.format("%d:%02d:%02d",hours,minutes,seconds);
                 textView.setText(time);

                 if (running){
                     second++;
                 }
                 handler.postDelayed(this,1000);
             }
         });


    }

}
