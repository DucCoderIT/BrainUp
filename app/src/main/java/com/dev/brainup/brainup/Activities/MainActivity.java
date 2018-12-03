package com.dev.brainup.brainup.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.brainup.brainup.DBHelper;
import com.dev.brainup.brainup.R;
import com.dev.brainup.brainup.ServiceMusic;

public class MainActivity extends AppCompatActivity {
    private String TAG = "LOGMainActivity";
    private Dialog menuDialog;
    private Boolean checkOnOffMusic;
    private TextView tvCongra,tvLetnextgame;
    private Button btnNextgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkOnOffMusic = true;
        //play music
        startService(new Intent(this,ServiceMusic.class));
        //Hello
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.getAllGames();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this,ServiceMusic.class));
    }

    public void PlayGame(View view){
        Intent intent= new Intent(MainActivity.this,SelectModeActivity.class);
        startActivity(intent);
        Log.d(TAG,"select play");
    }
    public void ShowScore(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("MyScore",Context.MODE_PRIVATE);
        int highscore = sharedPreferences.getInt("score",0);
        showHighestScoreDialog("Your highest score: "+highscore,"");
        Log.d(TAG,"select score");
    }
    public void ExitGame(View view){
        this.finishAffinity();
        stopService(new Intent(getApplicationContext(),ServiceMusic.class));
        Log.d(TAG,"select exit");
    }

    public void btnMusic(View view) {
        if (checkOnOffMusic == true){
            checkOnOffMusic = false;
            stopService(new Intent(this,ServiceMusic.class));
        }else{
            startService(new Intent(this,ServiceMusic.class));
            checkOnOffMusic = true;
        }
    }
    private void showHighestScoreDialog(String tvabove,String tvbelow){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.congra_dialog);
        menuDialog.setCanceledOnTouchOutside(false);
        menuDialog.setCancelable(false);
        tvCongra = (TextView) menuDialog.findViewById(R.id.tvCongratulation);
        tvLetnextgame = (TextView) menuDialog.findViewById(R.id.tvletsnextgame);
        btnNextgame = (Button) menuDialog.findViewById(R.id.btnNextgame);
        btnNextgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.dismiss();
            }
        });
        tvCongra.setText(tvabove);
        tvLetnextgame.setText(tvbelow);

        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        menuDialog.show();
    }
}
