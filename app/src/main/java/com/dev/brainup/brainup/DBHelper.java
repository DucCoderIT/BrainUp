package com.dev.brainup.brainup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dev.brainup.brainup.Constants.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG ="SQLite";
    private static final String DATABASE_NAME = "MyDBName.db";
    private static final String GAME_TABLE_NAME = "game";
    private static final String GAME_COLUMN_ID = "gameID";
    private static final String GAME_COLUMN_TYPE = "gametype";
    private static final String GAME_COLUMN_LEVEL = "level";
    private static final String GAME_COLUMN_IMAGE = "image";
    private static final String GAME_COLUMN_RESULTFALSE1 = "resultfalse1";
    private static final String GAME_COLUMN_RESULTFALSE2 = "resultfalse2";
    private static final String GAME_COLUMN_RESULTTRUE = "resulttrure";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + GAME_TABLE_NAME + "("
                + GAME_COLUMN_ID + " INTEGER PRIMARY KEY," + GAME_COLUMN_TYPE + " TEXT,"
                + GAME_COLUMN_LEVEL + " TEXT," + GAME_COLUMN_IMAGE + " TEXT,"
                + GAME_COLUMN_RESULTFALSE1+" TEXT,"+ GAME_COLUMN_RESULTFALSE2 +" TEXT," +GAME_COLUMN_RESULTTRUE+" TEXT"+ ")";
        // Execute Script.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + GAME_TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public void createDefaultGame()  {
        int count = this.getGamesCount();
        if(count ==0 ) {
            Game game1 = new Game(0,"animals",1,"animalsdog","dog","cat","mickey");
            Game game2 = new Game(1,"animals",1,"animalscat","bufferlow","fly","cat");
            this.addGame(game1);
            this.addGame(game2);
        }
    }

    public int getGamesCount() {
        Log.i(TAG, "MyDatabaseHelper.getGamesCount ... " );

        String countQuery = "SELECT  * FROM " + GAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void addGame(Game game) {
        Log.i(TAG, "MyDatabaseHelper.addGame ... " + game.getGameID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GAME_COLUMN_ID, game.getGameID());
        values.put(GAME_COLUMN_TYPE, game.getGametype());
        values.put(GAME_COLUMN_LEVEL, game.getLevel());
        values.put(GAME_COLUMN_IMAGE, game.getImage());
        values.put(GAME_COLUMN_RESULTFALSE1, game.getResultfalse1());
        values.put(GAME_COLUMN_RESULTFALSE2, game.getResultfalse2());
        values.put(GAME_COLUMN_RESULTTRUE, game.getResulttrue());
        // Inserting Row
        db.insert(GAME_TABLE_NAME, null, values);
        // Closing database connection
        db.close();
    }


    public ArrayList<Game> getAllGames() {
        ArrayList<Game> game_list = new ArrayList<Game>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from "+GAME_TABLE_NAME, null );

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setGameID(Integer.parseInt(cursor.getString(0)));
                game.setGametype(cursor.getString(1));
                game.setLevel(Integer.parseInt(cursor.getString(2)));
                game.setImage(cursor.getString(3));
                game.setResultfalse1(cursor.getString(4));
                game.setResultfalse2(cursor.getString(5));
                game.setResulttrue(cursor.getString(6));
                // Adding note to list
                game_list.add(game);
            } while (cursor.moveToNext());
        }
        return game_list;
    }
}
