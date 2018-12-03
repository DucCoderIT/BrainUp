package com.dev.brainup.brainup.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dev.brainup.brainup.DBHelper;
import com.dev.brainup.brainup.R;
import com.dev.brainup.brainup.ServiceMusic;

public class MainActivity extends AppCompatActivity {
    private String TAG = "LOGMainActivity";
    private Dialog dialog;
    private Boolean checkOnOffMusic = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //play music
        startService(new Intent(this,ServiceMusic.class));
        //Hello
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.getAllGames();
    }
    public void PlayGame(View view){
        Intent intent= new Intent(MainActivity.this,SelectModeActivity.class);
        startActivity(intent);
        Log.d(TAG,"select play");
    }
    public void ShowScore(View view){
        dialog.show();
        Log.d(TAG,"select score");
    }
    public void ExitGame(View view){
        this.finishAffinity();
        stopService(new Intent(getApplicationContext(),ServiceMusic.class));
        Log.d(TAG,"select exit");
    }

    public void btnMusic(View view) {
        if (checkOnOffMusic){
            stopService(new Intent(this, ServiceMusic.class));
            checkOnOffMusic = false;
        }else{
            startService(new Intent(this, ServiceMusic.class));
        }
    }
}
