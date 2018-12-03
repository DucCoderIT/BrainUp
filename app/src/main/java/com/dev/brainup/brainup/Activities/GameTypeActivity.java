package com.dev.brainup.brainup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dev.brainup.brainup.R;

public class GameTypeActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG= "LOG GAME TYPE";
    private String mode;
    private ImageButton imgBtn_animals,imgBtn_house,imgBtn_school,imgBtn_vehicle,imgBtn_sport,imgBtn_vegetables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
        mode = getIntent().getExtras().getString("mode");
        Toast.makeText(this, mode, Toast.LENGTH_SHORT).show();
        handle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_animals:
                Log.d(TAG, "onClick: animals");
                gotoGAME(mode,"animals");
                break;
            case R.id.imgbtn_house:
                Log.d(TAG, "onClick: house");
                gotoGAME(mode,"house");
                break;
            case R.id.imgbtn_school:
                Log.d(TAG, "onClick: school");
                gotoGAME(mode,"school");
                break;
            case R.id.imgbtn_vehicle:
                Log.d(TAG, "onClick: vehicle");
                gotoGAME(mode,"vehicle");
                break;
            case R.id.imgbtn_sport:
                Log.d(TAG, "onClick: sport");
                gotoGAME(mode,"sport");
                break;
            case R.id.imgbtn_vegetables:
                Log.d(TAG, "onClick: vegetables");
                gotoGAME(mode,"vegetables");
                break;
        }
    }
    private void handle(){
        imgBtn_animals = (ImageButton) findViewById(R.id.imgbtn_animals);
        imgBtn_house = (ImageButton) findViewById(R.id.imgbtn_house);
        imgBtn_school = (ImageButton) findViewById(R.id.imgbtn_school);
        imgBtn_sport = (ImageButton) findViewById(R.id.imgbtn_sport);
        imgBtn_vehicle = (ImageButton) findViewById(R.id.imgbtn_vehicle);
        imgBtn_vegetables = (ImageButton) findViewById(R.id.imgbtn_vegetables);
        imgBtn_animals.setOnClickListener(this);
        imgBtn_house.setOnClickListener(this);
        imgBtn_vehicle.setOnClickListener(this);
        imgBtn_school.setOnClickListener(this);
        imgBtn_sport.setOnClickListener(this);
        imgBtn_vegetables.setOnClickListener(this);
    }
    private void gotoGAME(String mode,String type){
        switch (mode){
            case "select":
                Log.d(TAG, "gotoGAME: select");
                Intent intentSelect = new Intent(GameTypeActivity.this,GameSelectActivity.class);
                intentSelect.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intentSelect.putExtra("type",type);
                startActivity(intentSelect);
                break;
            case "write":
                Log.d(TAG, "gotoGAME: write");
                Intent intentWrite = new Intent(GameTypeActivity.this,GameWriteActivity.class);
                intentWrite.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intentWrite.putExtra("type",type);
                startActivity(intentWrite);
                break;
        }
    }
}
