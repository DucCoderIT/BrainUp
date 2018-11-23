package com.dev.brainup.brainup.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dev.brainup.brainup.R;

public class SelectModeActivity extends AppCompatActivity {
    private String TAG = "LOGSelectmode";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
    }

    public void selectType(View view) {
        Intent intentSelect = new Intent(SelectModeActivity.this,GameTypeActivity.class);
        intentSelect.putExtra("mode","select");
        startActivity(intentSelect);
        Log.d(TAG, "select mode: select");
    }
    public void writeType(View view) {
        Intent intentWrite = new Intent(SelectModeActivity.this,GameTypeActivity.class);
        intentWrite.putExtra("mode","write");
        startActivity(intentWrite);
        Log.d(TAG, "select mode: write");
    }
}
