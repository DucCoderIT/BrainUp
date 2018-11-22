package com.dev.brainup.brainup;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hello
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.createDefaultGame();
        Game game = new Game();
        ArrayList<Game> game_list = dbHelper.getAllGames();
        game.arrayList.addAll(game_list);
        Toast.makeText(this, ""+game.arrayList.size(), Toast.LENGTH_SHORT).show();
    }
    public void PlayGame(View view){
        Intent intent= new Intent(MainActivity.this,SelectModeActivity.class);
        startActivity(intent);
    }
    public void ShowScore(View view){
        dialog.show();
    }
    public void ExitGame(View view){
        this.finishAffinity();
    }
}
