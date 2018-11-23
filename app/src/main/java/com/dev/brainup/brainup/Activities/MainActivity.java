package com.dev.brainup.brainup.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dev.brainup.brainup.DBHelper;
import com.dev.brainup.brainup.R;
public class MainActivity extends AppCompatActivity {
    private String TAG = "LOGMainActivity";
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Log.d(TAG,"select exit");
    }
}
