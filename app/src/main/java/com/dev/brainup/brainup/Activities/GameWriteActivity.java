package com.dev.brainup.brainup.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.brainup.brainup.Constants.Game;
import com.dev.brainup.brainup.DBHelper;
import com.dev.brainup.brainup.R;

import java.util.ArrayList;

public class GameWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgView_game;
    private EditText edt_Result;
    private Button btn_sendResult;
    private String typeGame;
    private String TAG = "LOG GAME WRITE";
    private ArrayList<Game> game_type_list;
    private int countGame;
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
                    Toast.makeText(this, "Sai rồi bạn ơi! Chơi lại từ đầu nhé!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        return super.onKeyDown(keyCode, event);
    }

    private void handle(){
        imgView_game = (ImageView) findViewById(R.id.imgview_game);
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
        int id = getResources().getIdentifier(game_type_list.get(position).getImage(),"mipmap",getPackageName());
        imgView_game.setImageResource(id);
    }
}
