package com.dev.brainup.brainup.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.dev.brainup.brainup.R;
import com.dev.brainup.brainup.ServiceMusic;

public class SelectModeActivity extends AppCompatActivity {
    private String TAG = "LOGSelectmode";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        //stopService(new Intent(this,ServiceMusic.class));
    }

    public void selectType(View view) {
        Intent intentSelect = new Intent(SelectModeActivity.this,GameTypeActivity.class);
        intentSelect.putExtra("mode","select");
        startActivity(intentSelect);
        finish();
        Log.d(TAG, "select mode: select");
    }
    public void writeType(View view) {
        Intent intentWrite = new Intent(SelectModeActivity.this,GameTypeActivity.class);
        intentWrite.putExtra("mode","write");
        startActivity(intentWrite);
        finish();
        Log.d(TAG, "select mode: write");
    }
}
