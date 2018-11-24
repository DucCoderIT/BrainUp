package com.dev.brainup.brainup.Activities;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dev.brainup.brainup.Constants.Game;
import com.dev.brainup.brainup.DBHelper;
import com.dev.brainup.brainup.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameSelectActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgView_game;
    private RadioButton rdBtn_result,rdBtn_result1,rdBtn_result2,rdBtn_result3;
    private RadioGroup radioGroup;
    private Button btn_sendResult;
    private String typeGame;
    private String TAG = "LOG GAME SELECT";
    private String png = ".png";
    private ArrayList<Game> game_type_list;
    private int countGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        handle();
        getInfoGame();
        if (game_type_list.size() > 0){
            countGame = 0;
            setInfoGame(countGame);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendresult:
                int selectedID = radioGroup.getCheckedRadioButtonId();
                rdBtn_result = (RadioButton) findViewById(selectedID);
                String value = String.valueOf(rdBtn_result.getText());
                String result = game_type_list.get(countGame).getResulttrue();
                if (value.equalsIgnoreCase(result)){
                    Toast.makeText(this, "Chúc mừng, bạn đã trả lời chính xác", Toast.LENGTH_SHORT).show();
                    if (countGame < game_type_list.size() -1) {
                        countGame +=1;
                        setInfoGame(countGame);
                    }else Toast.makeText(this, "Bạn quá giỏi! Hết game của tui rồi!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Sai rồi bạn ơi! Chơi lại từ đầu nhé!", Toast.LENGTH_SHORT).show();
                    countGame =0;
                    setInfoGame(countGame);
                }
                break;
        }
    }

    //override button back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        return super.onKeyDown(keyCode, event);
    }

    private void handle(){
        imgView_game = (ImageView) findViewById(R.id.imgview_game);
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
        int id = getResources().getIdentifier(game_type_list.get(position).getImage(),"mipmap",getPackageName());
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
    }

    //set text for radio button
    private void setRdbtn(RadioButton rdbtn,String Result){
        rdbtn.setText(Result);
    }
}
