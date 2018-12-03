package com.dev.brainup.brainup.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.brainup.brainup.Constants.Game;
import com.dev.brainup.brainup.DBHelper;
import com.dev.brainup.brainup.R;
import com.dev.brainup.brainup.ServiceMusic;

import java.util.ArrayList;
import java.util.Collections;

public class GameWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgView_game;
    private TextView tv_countdowntime,tvTitleEnd,tvScore;
    private EditText edt_Result;
    private Button btn_sendResult, Resume,Menu,Exit,GoBackMenu,RePlay,btnNextgame;
    private String typeGame;
    private String TAG = "LOG GAME WRITE";
    private ArrayList<Game> game_type_list;
    private Dialog menuDialog;
    private int countGame,countScore,level;
    private int countWrongtime = 0;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_write);
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tv_countdowntime.setText("Time: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                showEndDialog("TIME'S UP");
            }
        };
        handle(); //
        getInfoGame(); //
        countScore = 0; //
        level = 2; //
        if (game_type_list.size() > 0){ // check empty arr
            RandomGame();
            setInfoGame(countGame);
        }
        //count down time in game

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_sendresult:
                String value = edt_Result.getText().toString();
                String result = game_type_list.get(countGame).getResulttrue();
                if (!(value.equals(""))){
                    if (value.equalsIgnoreCase(result)) {
                        if (countGame < game_type_list.size() - 1) {
                            showCongraDialog(); // Show dialog congratulation
                            edt_Result.setText(""); // set null for text view
                            countDownTimer.cancel(); // stop countdown time
                        }
                    } else {
                        if (countWrongtime < 1) {
                            countWrongtime += 1; // count wrong time
                            edt_Result.setText(""); // set null for text view
                            showWrongDialog(); // show wrong dialog
                        } else {
                            countDownTimer.cancel(); //stop count down time
                            countWrongtime = 0; // count wrong time
                            edt_Result.setText(""); // set null for text view
                            showEndDialog(""); // show wrong dialog
                            SharedPreferences sharedPref = getSharedPreferences("MyScore",Context.MODE_PRIVATE);
                            int highscore = sharedPref.getInt("score",0);
                            if (countScore > highscore){
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt("score", countScore);
                                editor.commit();
                            }
                        }
                    }
                }
                else Toast.makeText(this, "You must write somethings!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        showMenuDialog();
    }

    private void handle(){
        imgView_game = (ImageView) findViewById(R.id.imgview_game);
        tv_countdowntime = (TextView) findViewById(R.id.tv_countdowntime_write);
        edt_Result = (EditText) findViewById(R.id.edt_result);
        btn_sendResult = (Button) findViewById(R.id.btn_sendresult);
        btn_sendResult.setOnClickListener(this);
        typeGame = getIntent().getExtras().getString("type");
        Log.d(TAG, "type: "+ typeGame);
    }

    private void getInfoGame(){
        DBHelper dbHelper = new DBHelper(this);
        Game game = new Game();
        game_type_list = dbHelper.getAllGameType(typeGame);
        Log.d(TAG, "getInfoGame: "+game_type_list.size());
    }

    private void setInfoGame(int position){
        int id = getResources().getIdentifier(game_type_list.get(position).getImage(),"raw",getPackageName());
        imgView_game.setImageResource(id);
        countDownTimer.start();
    }

    //
    private void RandomGame(){
        ArrayList<Integer> numberRandom = new ArrayList<Integer>();
        for (int i = level - 2 ; i <= level ; ++i) numberRandom.add(i);
        Collections.shuffle(numberRandom);
        countGame = numberRandom.get(0);
    }
    //
    private void showMenuDialog(){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.menu_dialog);
        menuDialog.setCancelable(false);
        Resume = (Button) menuDialog.findViewById(R.id.Resume);
        Menu = (Button) menuDialog.findViewById(R.id.Menu);
        Exit = (Button) menuDialog.findViewById(R.id.Exit);

        Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.cancel();
            }
        });
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.cancel();
                stopService(new Intent(getApplicationContext(),ServiceMusic.class));
                Intent intentToMain = new Intent(getApplicationContext(),MainActivity.class);
                intentToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentToMain);
                finish();
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.cancel();
                stopService(new Intent(getApplicationContext(),ServiceMusic.class));
                finishAffinity();
            }
        });

        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        menuDialog.show();
    }
    private void showEndDialog(String tv){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.end_dialog);
        RePlay = (Button) menuDialog.findViewById(R.id.btnRePlay);
        GoBackMenu = (Button) menuDialog.findViewById(R.id.btnBackMenu);
        tvTitleEnd = (TextView) menuDialog.findViewById(R.id.tvTitleEnd);
        tvScore = (TextView) menuDialog.findViewById(R.id.tvScore);

        if (!(tv.equals(""))){
            tvTitleEnd.setText(tv);
        }
        tvScore.setText("Your score: "+countScore);
        RePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countScore = 0;
                level = 2;
                edt_Result.setText(""); // set null for text view
                RandomGame(); //random number game
                setInfoGame(countGame); // set info for game
                menuDialog.dismiss(); //dismiss dialog
            }
        });
        GoBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.dismiss();
                stopService(new Intent(getApplicationContext(),ServiceMusic.class));
                Intent intentToMain = new Intent(getApplicationContext(),MainActivity.class);
                intentToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentToMain);
                finish();
            }
        });

        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        menuDialog.setCancelable(false);
        menuDialog.show();
    }
    private void showWrongDialog(){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.wrong_dialog);
        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        menuDialog.show();
    }
    private void showCongraDialog(){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.congra_dialog);
        menuDialog.setCanceledOnTouchOutside(false);
        menuDialog.setCancelable(false);
        btnNextgame = (Button) menuDialog.findViewById(R.id.btnNextgame);
        btnNextgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.dismiss();
                level += 3; // count game up
                RandomGame();
                countScore += 1; // score up
                if (countScore < 10) {
                    setInfoGame(countGame); // next game
                } else {
                    showEndDialog("YOU WIN GAME! THANK YOU");
                }
            }
        });

        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        menuDialog.show();
    }
}
