package com.dev.brainup.brainup.Activities;

import android.app.Dialog;
import android.content.Intent;
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

public class GameWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgView_game;
    private TextView tv_countdowntime,tvTitleEnd;
    private EditText edt_Result;
    private Button btn_sendResult, Resume,Menu,Exit,GoBackMenu,RePlay;
    private String typeGame;
    private String TAG = "LOG GAME WRITE";
    private ArrayList<Game> game_type_list;
    private int countGame;
    private Dialog menuDialog;
    private boolean countWrongtime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_write);
        handle();
        getInfoGame();
        if (game_type_list.size() > 0){
            countGame = 0;
            setInfoGame(countGame);
        }
        //count down time in game
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_countdowntime.setText("Thời gian đếm ngược: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                showEndDialog("TIME'S UP");
            }
        }.start();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_sendresult:
                String value = edt_Result.getText().toString();
                String result = game_type_list.get(countGame).getResulttrue();
                if (value.equalsIgnoreCase(result)){
                    Toast.makeText(this, "Chúc mừng, bạn đã trả lời chính xác!", Toast.LENGTH_SHORT).show();
                    countGame += 1;
                    setInfoGame(countGame);
                    edt_Result.setText("");
                    edt_Result.isClickable();
                }
                else {
                    if (countWrongtime){
                        showWrongDialog();
                        countWrongtime = false;
                    }else{
                        showEndDialog("");
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        showMenuDialog();
    }

    private void handle(){
        imgView_game = (ImageView) findViewById(R.id.imgview_game);
        tv_countdowntime = (TextView) findViewById(R.id.tv_countdowntime_W);
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
    }

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
                menuDialog.dismiss();
            }
        });
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                stopService(new Intent(getApplicationContext(),ServiceMusic.class));
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        GoBackMenu = (Button) menuDialog.findViewById(R.id.btnBackMenu);
        tvTitleEnd = (TextView) menuDialog.findViewById(R.id.tvTitleEnd);

        if (tv.equals("TIME'S UP")){
            tvTitleEnd.setText(tv);
        }

        RePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        GoBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                stopService(new Intent(getApplicationContext(),ServiceMusic.class));
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
}
