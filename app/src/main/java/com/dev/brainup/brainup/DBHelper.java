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
        Log.d(TAG, "onCreate: create database completed");
            String addGame = "INSERT INTO "+ GAME_TABLE_NAME+"("+GAME_COLUMN_ID+","+GAME_COLUMN_TYPE+","
                    +GAME_COLUMN_LEVEL+","+GAME_COLUMN_IMAGE+","+GAME_COLUMN_RESULTFALSE1+","+GAME_COLUMN_RESULTFALSE2+","+GAME_COLUMN_RESULTTRUE
                    +") VALUES (0,\"animals\",1,\"animals_dog\",\"scorpion\",\"cobra\",\"dog\")," +
                    "(1,\"animals\",1,\"animals_cat\",\"buffalo\",\"bat\",\"cat\")," +
                    "(2,\"animals\",1,\"animals_butterfly\",\"wolf\",\"fly\",\"butterfly\")," +
                    "(3,\"animals\",2,\"animals_lion\",\"beaver\",\"chipmunk\",\"lion\")," +
                    "(4,\"animals\",2,\"animals_elephant\",\"deer\",\"cow\",\"elephant\")," +
                    "(5,\"animals\",2,\"animals_fish\",\"alligator\",\"dolphin\",\"fish\")," +
                    "(6,\"animals\",3,\"animals_frog\",\"whale\",\"swordfish\",\"frog\")," +
                    "(7,\"animals\",3,\"animals_chicken\",\"walrus\",\"shark\",\"chicken\")," +
                    "(8,\"animals\",3,\"animals_horse\",\"penguin\",\"scorpion\",\"horse\")," +
                    "(9,\"animals\",4,\"animals_fox\",\"turtle\",\"antelope\",\"fox\")," +
                    "(10,\"animals\",4,\"animals_buffalo\",\"cow\",\"penguin\",\"buffalo\"),"+
                    "(11,\"animals\",4,\"animals_bear\",\"bufferlow\",\"fly\",\"bear\"),"+
                    "(12,\"animals\",5,\"animals_chimpanzee\",\"cat\",\"porcupinen\",\"chimpanzee\"),"+
                    "(13,\"animals\",5,\"animals_elephant\",\"giraffe\",\"bear\",\"elephant\"),"+
                    "(14,\"animals\",5,\"animals_porcupine\",\"rhinoceros\",\"lion\",\"porcupine\"\n),"+
                    "(15,\"animals\",6,\"animals_rhinoceros\",\"giraffe\",\"chimpanzee\",\"rhinoceros\"),"+
                    "(16,\"animals\",6,\"animals_hippopotamus\",\"squirrel\",\"raccoon\",\"hippopotamus\"),"+
                    "(17,\"animals\",6,\"animals_squirrel\",\"fox\",\"hippopotamus\",\"squirrel\"),"+
                    "(18,\"animals\",7,\"animals_alligator\",\"bat\",\"wolf\",\"alligator\"),"+
                    "(19,\"animals\",7,\"animals_turtle\",\"penguin\",\"deer\",\"turtle\"),"+
                    "(20,\"animals\",7,\"animals_beaver\",\"eel\",\"turtl\",\"beaver\"),"+
                    "(21,\"animals\",8,\"animals_penguin\",\"chipmunk\",\"hermit crab\",\"penguin\"),"+
                    "(22,\"animals\",8,\"animals_dolphin\",\"shark\",\"bat\",\"dolphin\"),"+
                    "(23,\"animals\",8,\"animals_shark\",\"dolphin\",\"deer\",\"shark\"),"+
                    "(24,\"animals\",9,\"animals_whale\",\"fox\",\"shark\",\"whale\"),"+
                    "(25,\"animals\",9,\"animals_cobra\",\"scorpion\",\"camel\",\"cobra\"),"+
                    "(26,\"animals\",9,\"animals_zebra\",\"donkey\",\"hedgehog\",\"zebra\"),"+
                    "(27,\"animals\",10,\"animals_bison\",\"porcupinen\",\"squirrel\",\"bison\"),"+
                    "(28,\"animals\",10,\"animals_octopus\",\"\uFEFFjellyfish\",\"squid\",\"octopus\"\n),"+
                    "(29,\"animals\",10,\"animals_starfish\",\"\uFEFFstingray\",\"pufferfish\",\"starfish\"),"+
                    "(30,\"animals\",10,\"animals_panda\",\"\uFEFFflying squirrel\",\"platypush\",\"panda\");";
            db.execSQL(addGame);
        Log.d(TAG, "onCreate: insert data completed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + GAME_TABLE_NAME);
        // Create tables again
        onCreate(db);
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

    public ArrayList<Game> getAllGameType(String gametype){
        ArrayList<Game> game_type_arr = new ArrayList<Game>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+GAME_TABLE_NAME+" where gametype = \""+gametype+"\"",null );

        if (cursor.moveToFirst()){
            do{
                Game game = new Game();
                game.setGameID(Integer.parseInt(cursor.getString(0)));
                game.setGametype(cursor.getString(1));
                game.setLevel(Integer.parseInt(cursor.getString(2)));
                game.setImage(cursor.getString(3));
                game.setResultfalse1(cursor.getString(4));
                game.setResultfalse2(cursor.getString(5));
                game.setResulttrue(cursor.getString(6));
                // Adding note to list
                game_type_arr.add(game);
            } while (cursor.moveToNext());
        }
        return game_type_arr;
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
