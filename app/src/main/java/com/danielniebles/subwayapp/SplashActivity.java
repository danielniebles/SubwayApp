package com.danielniebles.subwayapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private int Logged;

    private static final long SPLASH_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        Logged = prefs.getInt("Logged",-1);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(Logged == 1){
                    Intent i = new Intent().setClass(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent().setClass(SplashActivity.this, LogginActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        };

        Timer timer = new Timer();
        timer.schedule(task,SPLASH_DELAY);

    }
}
