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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.brainup.brainup.Constants.Game;
import com.dev.brainup.brainup.DBHelper;
import com.dev.brainup.brainup.R;
import com.dev.brainup.brainup.ServiceMusic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameSelectActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgView_game;
    private TextView tv_countdowntime,tvTitleEnd,tvScore;
    private RadioButton rdBtn_result,rdBtn_result1,rdBtn_result2,rdBtn_result3;
    private RadioGroup radioGroup;
    private Button btn_sendResult, Resume,Menu,Exit,GoBackMenu,RePlay,btnNextgame;
    private String typeGame;
    private String TAG = "LOG GAME SELECT";
    private ArrayList<Game> game_type_list;
    private int countGame,countScore,level;
    private int countWrongtime = 0;
    private CountDownTimer countDownTimer;
    private Dialog menuDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startService(new Intent(this,ServiceMusic.class));
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tv_countdowntime.setText("Time: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                showEndDialog("TIME'S UP");
            }
        };
        countScore = 0;
        level = 2;
        handle();
        getInfoGame();
        if (game_type_list.size() > 0){
            RandomGame();
            setInfoGame(countGame);
        }
        //count down time in game
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendresult:
                if(rdBtn_result1.isChecked()|rdBtn_result2.isChecked()|rdBtn_result3.isChecked()) {
                    int selectedID = radioGroup.getCheckedRadioButtonId();

                    rdBtn_result = (RadioButton) findViewById(selectedID);
                    String value = String.valueOf(rdBtn_result.getText());
                    String result = game_type_list.get(countGame).getResulttrue();
                    if (value.equalsIgnoreCase(result)) {
                        if (countGame < game_type_list.size() - 1) {
                            // Show dialog congratulation
                            showCongraDialog();
                            radioGroup.clearCheck(); // clear check radio button
                            countDownTimer.cancel(); // stop countdown time
                        }
                    } else {
                        if (countWrongtime < 1) {
                            countWrongtime += 1; // count wrong time
                            showWrongDialog(); // show wrong dialog
                            radioGroup.clearCheck();
                        } else {
                            countDownTimer.cancel(); //stop count down time
                            countWrongtime = 0; // count wrong time
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
                }else Toast.makeText(this, "You must choose one result!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //override button back


    @Override
    public void onBackPressed() {
        showMenuDialog();
    }

    private void handle(){
        imgView_game = (ImageView) findViewById(R.id.imgview_game);
        tv_countdowntime = (TextView) findViewById(R.id.tv_countdowntime_S);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rdBtn_result1 = (RadioButton) findViewById(R.id.rdbtn_result1);
        rdBtn_result2 = (RadioButton) findViewById(R.id.rdbtn_result2);
        rdBtn_result3 = (RadioButton) findViewById(R.id.rdbtn_result3);
        btn_sendResult = (Button) findViewById(R.id.btn_sendresult);
        btn_sendResult.setOnClickListener(this);
        //get value from gametype activity
        typeGame = getIntent().getExtras().getString("type");
        Log.d(TAG, "type: "+ typeGame);
    }

    //get all game with type
    private void getInfoGame(){
        DBHelper dbHelper = new DBHelper(this);
        Game game = new Game();
        game_type_list = dbHelper.getAllGameType(typeGame);
        Log.d(TAG, "getInfoGame: size list: "+game_type_list.size());
    }
    //set data for game
    private void setInfoGame(int position){
        //set id image source ( @mipmap / getimage() )
        int id = getResources().getIdentifier(game_type_list.get(position).getImage(),"raw",getPackageName());
        imgView_game.setImageResource(id);
        // get value result of game
        String value1 = game_type_list.get(position).getResulttrue();
        String value2 = game_type_list.get(position).getResultfalse1();
        String value3 = game_type_list.get(position).getResultfalse2();
        //add to list
        List listValue = new ArrayList();
        listValue.add(value1);
        listValue.add(value2);
        listValue.add(value3);
        //set random list number
        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 0 ; i <= 2 ; ++i) number.add(i);
        Collections.shuffle(number);
        Log.d(TAG, "setInfoGame: random list:"+ number);
        //use void setRdbtn to set result for radiobutton
        setRdbtn(rdBtn_result1,listValue.get(number.get(0)).toString());
        setRdbtn(rdBtn_result2,listValue.get(number.get(1)).toString());
        setRdbtn(rdBtn_result3,listValue.get(number.get(2)).toString());
        countDownTimer.start();
    }
    //set text for radio button
    private void setRdbtn(RadioButton rdbtn,String Result){
        rdbtn.setText(Result);
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

        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (!this.isFinishing()){
            menuDialog.show();
        }

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
                finish();
            }
        });

    }
    private void showEndDialog(String tv){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.end_dialog);
        RePlay = (Button) menuDialog.findViewById(R.id.btnRePlay);
        GoBackMenu = (Button) menuDialog.findViewById(R.id.btnBackMenu);
        tvTitleEnd = (TextView) menuDialog.findViewById(R.id.tvTitleEnd);
        tvScore = (TextView) menuDialog.findViewById(R.id.tvScore);
        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        menuDialog.setCancelable(false);
        if (!this.isFinishing()){
            menuDialog.show();
        }
        if (!(tv.equals(""))){
            tvTitleEnd.setText(tv);
        }
        tvScore.setText("Your score: "+countScore);
        RePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countScore = 0;
                level = 2;
                RandomGame();
                setInfoGame(countGame);
                radioGroup.clearCheck();
                menuDialog.cancel();
            }
        });
        GoBackMenu.setOnClickListener(new View.OnClickListener() {
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


    }
    private void showWrongDialog(){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.wrong_dialog);
        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (!this.isFinishing()){
            menuDialog.show();
        }
    }
    private void showCongraDialog(){
        menuDialog = new Dialog(this);
        menuDialog.setContentView(R.layout.congra_dialog);
        menuDialog.setCanceledOnTouchOutside(false);
        menuDialog.setCancelable(false);
        btnNextgame = (Button) menuDialog.findViewById(R.id.btnNextgame);
        menuDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (!this.isFinishing()){
            menuDialog.show();
        }
        btnNextgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog.cancel();
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


    }
}
