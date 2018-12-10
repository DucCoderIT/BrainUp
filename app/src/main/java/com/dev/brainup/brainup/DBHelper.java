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
                    "(28,\"animals\",10,\"animals_octopus\",\"\uFEFFjellyfish\",\"squid\",\"octopus\"),"+
                    "(29,\"animals\",10,\"animals_starfish\",\"\uFEFFstingray\",\"pufferfish\",\"starfish\"),"+
                    "(30,\"animals\",10,\"animals_panda\",\"\flying squirrel\",\"platypush\",\"panda\"),"+
                    "(31,\"vegetables\",1,\"vegetables_tomato\",\"\uFEFFonion\",\"carrot\",\"tomato\"),"+
                    "(32,\"vegetables\",1,\"vegetables_cauliflower\",\"\uFEFFcucumber\",\"bell pepper\",\"cauliflower\"),"+
                    "(33,\"vegetables\",1,\"vegetables_coriander\",\"carrot\",\"cabbage\",\"coriander\"),"+
                    "(34,\"vegetables\",2,\"vegetables_eggplant\",\"tomato\",\"onion\",\"eggplant\"),"+
                    "(35,\"vegetables\",2,\"vegetables_horseradish\",\"corn\",\"lettuce\",\"horseradish\"),"+
                    "(36,\"vegetables\",2,\"vegetables_beetroot\",\"mushroom\",\"squash\",\"beetroot\"),"+
                    "(37,\"vegetables\",3,\"vegetables_lotus root\",\"colza\",\"string bean\",\"lotus root\"),"+
                    "(38,\"vegetables\",3,\"vegetables_avocado\",\"pineapple\",\"apple\",\"avocado\"),"+
                    "(39,\"vegetables\",3,\"vegetables_Cocunut\",\"melon\",\"watermelon\",\"Cocunut\"),"+
                    "(40,\"vegetables\",4,\"vegetables_star_apple\",\"granadilla\",\"ambarella\",\"star apple\"),"+
                    "(41,\"vegetables\",4,\"vegetables_Passion_fruit\",\"strawberry\",\"dragon fruit\",\"Passion fruit\"),"+
                    "(42,\"vegetables\",4,\"vegetables_longan\",\"pomegranate\",\"rambutan\",\"longan\"),"+
                    "(43,\"vegetables\",5,\"vegetables_peach\",\"Papaya\",\"lemon\",\"peach\"),"+
                    "(44,\"vegetables\",5,\"vegetables_kumquat\",\"Grape\",\"Banana\",\"kumquat\"),"+
                    "(45,\"vegetables\",5,\"vegetables_durian\",\"mango\",\"pomelo\",\"durian\"),"+
                    "(46,\"vegetables\",6,\"vegetables_strawberry\",\"watermelon\",\"granadilla\",\"strawberry\"),"+
                    "(47,\"vegetables\",6,\"vegetables_Banana\",\"rambutan\",\"pomegranate\",\"Banana\"),"+
                    "(48,\"vegetables\",6,\"vegetables_ambarella\",\"Cocunut\",\"avocado\",\"ambarella\"),"+
                    "(49,\"vegetables\",7,\"vegetables_colza\",\"beetroot\",\"mushroom\",\"colza\"),"+
                    "(50,\"vegetables\",7,\"vegetables_watermelon\",\"squash\",\"corn\",\"watermelon\"),"+
                    "(51,\"vegetables\",7,\"vegetables_mushroom\",\"star apple\",\"Grape\",\"mushroom\"),"+
                    "(52,\"vegetables\",8,\"vegetables_lettuce\",\"string bean\",\"melon\",\"lettuce\"),"+
                    "(53,\"vegetables\",8,\"vegetables_dragon_fruit\",\"lemon\",\"kumquat\",\"dragon fruit\"),"+
                    "(54,\"vegetables\",8,\"vegetables_Papaya\",\"Cocunut\",\"mango\",\"dragon papaya\"),"+
                    "(55,\"vegetables\",9,\"vegetables_horseradish\",\"corn\",\"lettuce\",\"horseradish\"),"+
                    "(56,\"vegetables\",9,\"vegetables_beetroot\",\"mushroom\",\"squash\",\"beetroot\"),"+
                    "(57,\"vegetables\",9,\"vegetables_lotus root\",\"colza\",\"string bean\",\"lotus root\"),"+
                    "(58,\"vegetables\",10,\"vegetables_avocado\",\"pineapple\",\"apple\",\"avocado\"),"+
                    "(59,\"vegetables\",10,\"vegetables_Cocunut\",\"melon\",\"watermelon\",\"Cocunut\"),"+
                    "(60,\"vegetables\",10,\"vegetables_pomelo\",\"banana\",\"ambarella\",\"pomelo\");";
            db.execSQL(addGame);
        String addGame2 = "INSERT INTO "+ GAME_TABLE_NAME+"("+GAME_COLUMN_ID+","+GAME_COLUMN_TYPE+","
                +GAME_COLUMN_LEVEL+","+GAME_COLUMN_IMAGE+","+GAME_COLUMN_RESULTFALSE1+","+GAME_COLUMN_RESULTFALSE2+","+GAME_COLUMN_RESULTTRUE
                +") VALUES (61,\"sport\",1,\"sport_cycling\",\"gymnastics\",\"tennis\",\"cycling\")," +
                "(62,\"sport\",1,\"sport_running\",\"swimming\",\"riding\",\"running\")," +
                "(63,\"sport\",1,\"sport_volleyball\",\"football\",\"basketball\",\"volleyball\")," +
                "(64,\"sport\",2,\"sport_table_tennis\",\"tennis\",\"cycling\",\"table tennis\")," +
                "(65,\"sport\",2,\"sport_skateboarding\",\"scuba_diving\",\"windsurfing\",\"skateboarding\")," +
                "(66,\"sport\",2,\"sport_badminton\",\"hockey\",\"ice skating\",\"badminton\")," +
                "(67,\"sport\",3,\"sport_archery\",\"fishing\",\"bowls\",\"archery\")," +
                "(68,\"sport\",3,\"sport_climbing\",\"darts\",\"cricket\",\"climbing\")," +
                "(69,\"sport\",3,\"sport_weightlifting\",\"water skiing\",\"walking\",\"weightlifting\")," +
                "(70,\"sport\",4,\"sport_sailing\",\"squash\",\"shooting\",\"sailing\")," +
                "(71,\"sport\",4,\"sport_surfing\",\"bowls\",\"snooker\",\"surfing\"),"+
                "(72,\"sport\",4,\"sport_cricket\",\"darts\",\"walking\",\"cricket\"),"+
                "(73,\"sport\",5,\"sport_windsurfing\",\"scuba diving\",\"fishing\",\"windsurfing\"),"+
                "(74,\"sport\",5,\"sport_tennis\",\"volleyball\",\"ice skating\",\"tennis\"),"+
                "(75,\"sport\",5,\"sport_hockey\",\"riding\",\"cycling\",\"hockey\"),"+
                "(76,\"sport\",6,\"sport_darts\",\"running\",\"football\",\"darts\"),"+
                "(77,\"sport\",6,\"sport_swimming\",\"shooting\",\"cricket\",\"swimming\"),"+
                "(78,\"sport\",6,\"sport_riding\",\"basketball\",\"bowls\",\"riding\"),"+
                "(79,\"sport\",7,\"sport_walking\",\"skateboarding\",\"climbing\",\"walking\"),"+
                "(80,\"sport\",7,\"sport_weightlifting\",\"water skiing\",\"walking\",\"weightlifting\")," +
                "(81,\"sport\",7,\"sport_sailing\",\"squash\",\"shooting\",\"sailing\")," +
                "(82,\"sport\",8,\"sport_surfing\",\"bowls\",\"snooker\",\"surfing\"),"+
                "(83,\"sport\",8,\"sport_cricket\",\"darts\",\"walking\",\"cricket\"),"+
                "(84,\"sport\",8,\"sport_windsurfing\",\"scuba diving\",\"fishing\",\"windsurfing\"),"+
                "(85,\"sport\",9,\"sport_tennis\",\"volleyball\",\"ice skating\",\"tennis\"),"+
                "(86,\"sport\",9,\"sport_hockey\",\"riding\",\"cycling\",\"hockey\"),"+
                "(87,\"sport\",9,\"sport_darts\",\"running\",\"football\",\"darts\"),"+
                "(88,\"sport\",10,\"sport_swimming\",\"shooting\",\"cricket\",\"swimming\"),"+
                "(89,\"sport\",10,\"sport_riding\",\"basketball\",\"bowls\",\"riding\"),"+
                "(90,\"sport\",10,\"sport_walking\",\"skateboarding\",\"climbing\",\"walking\"),"+
                "(91,\"school\",1,\"school_principal\",\"canteen\",\"hall\",\"principal\"),"+
                "(92,\"school\",1,\"school_classroom\",\"library\",\"student\",\"classroom\"),"+
                "(93,\"school\",1,\"school_janitor\",\"SUBJECTS\",\"English\",\"janitor\"),"+
                "(94,\"school\",2,\"school_Mathematics\",\"blackboard\",\"fan\",\"Mathematics\"),"+
                "(95,\"school\",2,\"school_computer\",\"projector\",\"campus\",\"computer\"),"+
                "(96,\"school\",3,\"school_locker\",\"laboratory\",\"playing_field\",\"locker\"),"+
                "(97,\"school\",3,\"school_infirmary\",\"gym\",\"staffroom\",\"infirmary\"),"+
                "(98,\"school\",3,\"school_schoolyard\",\"timetable\",\"yearbook\",\"schoolyard\"),"+
                "(99,\"school\",4,\"school_staffroom\",\"textbook\",\"uniform\",\"staffroom\"),"+
                "(100,\"school\",4,\"school_bus\",\"homework\",\"bell\",\"bus\"),"+
                "(101,\"school\",4,\"school_ruler\",\"scissors\",\"crayon\",\"ruler\"),"+
                "(102,\"school\",5,\"school_mark\",\"watercolour\",\"dictionary\",\"mark\"),"+
                "(103,\"school\",5,\"school_desk\",\"chalk\",\"pen\",\"desk\"),"+
                "(104,\"school\",5,\"school_pencil\",\"lesson\",\"exercise_book\",\"pencil\"),"+
                "(105,\"school\",6,\"school_lecture_hall\",\"laboratory\",\"degree\",\"lecture hall\"),"+
                "(106,\"school\",6,\"school_calculator\",\"textbook\",\"projector\",\"calculator\"),"+
                "(107,\"school\",6,\"school_campus\",\"subject\",\"course\",\"campus\"),"+
                "(108,\"school\",7,\"school_pen\",\"bus\",\"staffroom\",\"pen\"),"+
                "(109,\"school\",7,\"school_blackboard\",\"gym\",\"chalk\",\"blackboard\"),"+
                "(110,\"school\",7,\"school_laboratory\",\"playing field\",\"bell\",\"laboratory\"),"+
                "(111,\"school\",8,\"school_chalk\",\"grade\",\"yearbook\",\"chalk\"),"+
                "(112,\"school\",8,\"school_projector\",\"homework\",\"timetable\",\"projector\"),"+
                "(113,\"school\",8,\"school_dictionary\",\"ruler\",\"scissors\",\"dictionary\"),"+
                "(114,\"school\",9,\"school_fan\",\"watercolour\",\"textbook\",\"fan\"),"+
                "(115,\"school\",9,\"school_uniform\",\"lesson\",\"subject\",\"uniform\"),"+
                "(116,\"school\",9,\"school_crayon\",\"course\",\"laboratory\",\"crayon\"),"+
                "(117,\"school\",10,\"school_lecture_hall\",\"laboratory\",\"degree\",\"lecture hall\"),"+
                "(118,\"school\",10,\"school_calculator\",\"textbook\",\"projector\",\"calculator\"),"+
                "(119,\"school\",10,\"school_campus\",\"subject\",\"course\",\"campus\"),"+
                "(120,\"school\",10,\"school_library\",\"schoolyard\",\"exercise book\",\"library\");";
        db.execSQL(addGame2);
        String addGame3 = "INSERT INTO "+ GAME_TABLE_NAME+"("+GAME_COLUMN_ID+","+GAME_COLUMN_TYPE+","
                +GAME_COLUMN_LEVEL+","+GAME_COLUMN_IMAGE+","+GAME_COLUMN_RESULTFALSE1+","+GAME_COLUMN_RESULTFALSE2+","+GAME_COLUMN_RESULTTRUE
                +") VALUES (121,\"vehicle\",1,\"vehicle_car\",\"truck\",\"bus\",\"car\"),"+
                "(122,\"vehicle\",1,\"vehicle_bicycle\",\"scooter\",\"motorbike\",\"bicycle\"),"+
                "(123,\"vehicle\",1,\"vehicle_train\",\"subway\",\"jet\",\"train\"),"+
                "(124,\"vehicle\",2,\"vehicle_horse\",\"cruise ship\",\"cargo ship\",\"horse\"),"+
                "(125,\"vehicle\",2,\"vehicle_helicopter\",\"Ferry\",\"sailboat\",\"helicopter\"),"+
                "(126,\"vehicle\",2,\"vehicle_propeller_plane\",\"hot air balloon\",\"Van\",\"propeller plane\"),"+
                "(127,\"vehicle\",3,\"vehicle_Taxi\",\"Coach\",\"Boat\",\"Taxi\"),"+
                "(128,\"vehicle\",3,\"vehicle_Speedboat\",\"Airplane\",\"Glider\",\"Speedboat\"),"+
                "(129,\"vehicle\",3,\"vehicle_Traffic_light\",\"hot air balloon\",\"helicopter\",\"Traffic light\"),"+
                "(130,\"vehicle\",4,\"vehicle_truck\",\"car\",\"bicycle\",\"truck\"),"+
                "(131,\"vehicle\",4,\"vehicle_scooter\",\"bus\",\"train\",\"scooter\"),"+
                "(132,\"vehicle\",4,\"vehicle_Glider\",\"jet\",\"Ferry\",\"Glider\"),"+
                "(133,\"vehicle\",5,\"vehicle_Van\",\"Taxi\",\"subway\",\"Van\"),"+
                "(134,\"vehicle\",5,\"vehicle_motorbike\",\"Boat\",\"Traffic light\",\"motorbike\"),"+
                "(135,\"vehicle\",5,\"vehicle_bicycle\",\"scooter\",\"motorbike\",\"bicycle\"),"+
                "(136,\"vehicle\",6,\"vehicle_train\",\"subway\",\"jet\",\"train\"),"+
                "(137,\"vehicle\",6,\"vehicle_horse\",\"cruise ship\",\"cargo ship\",\"horse\"),"+
                "(138,\"vehicle\",6,\"vehicle_helicopter\",\"Ferry\",\"sailboat\",\"helicopter\"),"+
                "(139,\"vehicle\",7,\"vehicle_propeller_plane\",\"hot air balloon\",\"Van\",\"propeller plane\"),"+
                "(140,\"vehicle\",7,\"vehicle_Taxi\",\"Coach\",\"Boat\",\"Taxi\"),"+
                "(141,\"vehicle\",7,\"vehicle_Speedboat\",\"Airplane\",\"Glider\",\"Speedboat\"),"+
                "(142,\"vehicle\",8,\"vehicle_Traffic_light\",\"hot air balloon\",\"helicopter\",\"Traffic light\"),"+
                "(143,\"vehicle\",8,\"vehicle_truck\",\"car\",\"bicycle\",\"truck\"),"+
                "(144,\"vehicle\",8,\"vehicle_scooter\",\"bus\",\"train\",\"scooter\"),"+
                "(145,\"vehicle\",9,\"vehicle_Glider\",\"jet\",\"Ferry\",\"Glider\"),"+
                "(146,\"vehicle\",9,\"vehicle_Van\",\"Taxi\",\"subway\",\"Van\"),"+
                "(147,\"vehicle\",9,\"vehicle_motorbike\",\"Boat\",\"Traffic light\",\"motorbike\"),"+
                "(148,\"vehicle\",10,\"vehicle_helicopter\",\"Ferry\",\"sailboat\",\"helicopter\"),"+
                "(149,\"vehicle\",10,\"vehicle_propeller_plane\",\"hot air balloon\",\"Van\",\"propeller plane\"),"+
                "(150,\"vehicle\",10,\"vehicle_bicycle\",\"scooter\",\"motorbike\",\"bicycle\"),"+
                "(151,\"vehicle\",10,\"vehicle_bus\",\"Airplane\",\"Speedboat\",\"bus\"),"+
                "(152,\"house\",4,\"house_fence\",\"front door\",\"balcony\",\"fence\"),"+
                "(153,\"house\",4,\"house_attic\",\"roof\",\"chimney\",\"attic\"),"+
                "(154,\"house\",4,\"house_Living_room\",\"Bed_room\",\"Bath room\",\"Living room\"),"+
                "(155,\"house\",4,\"house_Armchair\",\"Bed\",\"Chair\",\"Armchair\"),"+
                "(156,\"house\",4,\"house_Cupboard\",\"Mirror\",\"Sofa\",\"Cupboard\"),"+
                "(157,\"house\",4,\"house_Wardrobe\",\"Dishwasher\",\"Iron\",\"Wardrobe\"),"+
                "(158,\"house\",4,\"house_Washing_machine\",\"Blanket\",\"Curtains\",\"Washing machine\"),"+
                "(159,\"house\",4,\"house_Cushion\",\"Duvet\",\"Mattress\",\"Cushion\"),"+
                "(160,\"house\",4,\"house_Pillow\",\"Tablecloth\",\"Rug\",\"Pillow\"),"+
                "(161,\"house\",4,\"house_Tap\",\"Poster\",\"Sponge\",\"Tap\"),"+
                "(162,\"house\",4,\"house_front_door\",\"propeller plane\",\"Bath room\",\"front door\"),"+
                "(163,\"house\",4,\"house_chimney\",\"Cupboard\",\"Mirror\",\"chimney\"),"+
                "(164,\"house\",4,\"house_roof\",\"horse\",\"Sofa\",\"roof\"),"+
                "(165,\"house\",4,\"house_Tablecloth\",\"Pillow\",\"Rug\",\"Tablecloth\"),"+
                "(166,\"house\",4,\"house_Blanket\",\"Wardrobe\",\"Iron\",\"Blanket\"),"+
                "(167,\"house\",4,\"house_Curtains\",\"Bath room\",\"Sponge\",\"Curtains\"),"+
                "(168,\"house\",4,\"house_Mattress\",\"Houseplant\",\"house front door\",\"Mattress\"),"+
                "(169,\"house\",4,\"house_Duvet\",\"Tap\",\"Poster\",\"Duvet\"),"+
                "(170,\"house\",4,\"house_Dishwasher\",\"Pillow\",\"roof\",\"Dishwasher\"),"+
                "(171,\"house\",4,\"house_Sofa\",\"Rug\",\"horse\",\"Sofa\"),"+
                "(172,\"house\",4,\"house_Sponge\",\"Mattress\",\"Houseplant\",\"Sponge\"),"+
                "(173,\"house\",4,\"house_Bath_room\",\"Tablecloth\",\"Sofa\",\"Bath_room\"),"+
                "(174,\"house\",4,\"house_Bed_room\",\"Blanket\",\"Wardrobe\",\"Bed_room\"),"+
                "(175,\"house\",4,\"house_Chair\",\"Pillow\",\"Cupboard\",\"Chair\"),"+
                "(176,\"house\",4,\"house_Pillow\",\"Tablecloth\",\"Rug\",\"Pillow\"),"+
                "(177,\"house\",4,\"house_Wardrobe\",\"Dishwasher\",\"Iron\",\"Wardrobe\"),"+
                "(178,\"house\",4,\"house_Washing_machine\",\"Blanket\",\"Curtains\",\"Washing machine\"),"+
                "(179,\"house\",4,\"house_Cushion\",\"Duvet\",\"Mattress\",\"Cushion\"),"+
                "(180,\"house\",4,\"house_Pillow\",\"Tablecloth\",\"Rug\",\"Pillow\");";
        db.execSQL(addGame3);
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
