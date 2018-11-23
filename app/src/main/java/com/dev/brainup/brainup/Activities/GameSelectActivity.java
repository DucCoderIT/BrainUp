package com.dev.brainup.brainup.Activities;


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

import com.dev.brainup.brainup.R;

public class GameSelectActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgView_game;
    private RadioButton rdBtn_result,rdBtn_result1,rdBtn_result2,rdBtn_result3;
    private RadioGroup radioGroup;
    private Button btn_sendResult;
    private String typeGame;
    private String TAG = "LOG GAME SELECT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        handle();
    }
    private void handle(){
        imgView_game = (ImageView) findViewById(R.id.imgview_game);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rdBtn_result1 = (RadioButton) findViewById(R.id.rdbtn_result1);
        rdBtn_result2 = (RadioButton) findViewById(R.id.rdbtn_result2);
        rdBtn_result3 = (RadioButton) findViewById(R.id.rdbtn_result3);
        btn_sendResult = (Button) findViewById(R.id.btn_sendresult);
        btn_sendResult.setOnClickListener(this);
        typeGame = getIntent().getExtras().getString("type");
        Log.d(TAG, "type: "+ typeGame);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendresult:
                int selectedID = radioGroup.getCheckedRadioButtonId();
                rdBtn_result = (RadioButton) findViewById(selectedID);
                String value = String.valueOf(rdBtn_result.getText());
                if (value.equalsIgnoreCase("SPORT")){
                    Toast.makeText(this, "Chúc mừng, bạn đã trả lời chính xác", Toast.LENGTH_SHORT).show();
                }else{
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
}
