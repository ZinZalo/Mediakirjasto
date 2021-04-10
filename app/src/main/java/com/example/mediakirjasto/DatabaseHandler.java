package com.example.mediakirjasto;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME =  "Kollektio";
    private static final String TABLE_GAME = "games";
    private static final String TABLE_MUSIC = "music";
    private static final String TABLE_MOVIE = "movies";
    private static final String TABLE_BOOK = "books";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PLATFORM = "platform";
    private static final String KEY_REGION = "region";
    private static final String KEY_EXPANSION = "expansion";
    private static final String KEY_MEDIA_TYPE = "mediaType";
    private static final String KEY_COPIES = "copies";
    private static final String KEY_NOTES = "notes";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAME_TABLE =
                " CREATE TABLE " + TABLE_GAME + "("
                        + KEY_ID + " INTEGER PRIMARY KEY, " +
                        KEY_NAME + " TEXT, " +
                        KEY_PLATFORM + " TEXT, " +
                        KEY_REGION + " TEXT, " +
                        KEY_EXPANSION + " TEXT, " +
                        KEY_MEDIA_TYPE + " TEXT, " +
                        KEY_COPIES + " TEXT, " +
                        KEY_NOTES + " TEXT " + ")";
        db.execSQL(CREATE_GAME_TABLE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);

        // Create tables again
        onCreate(db);
    }

    //add new game
    void addGame(String gameTitle, String gamePlatform, String gameRegion, String gameExpansion, String gameMediaType, String gameCopies, String gameNotes){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, gameTitle);
        values.put(KEY_PLATFORM, gamePlatform);
        values.put(KEY_REGION, gameRegion);
        values.put(KEY_EXPANSION, gameExpansion);
        values.put(KEY_MEDIA_TYPE, gameMediaType);
        values.put(KEY_COPIES, gameCopies);
        values.put(KEY_NOTES, gameNotes);

        //Inserting row
        long result = db.insert(TABLE_GAME, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
        //db.close();
    }

    Cursor gameReadData(){
        String query = "SELECT * FROM " + TABLE_GAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }



    /*
    //get single game
    Game getGame(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAME, new String[] { KEY_ID,
                KEY_NAME, KEY_PLATFORM, KEY_REGION, KEY_PLATFORM, KEY_REGION, KEY_EXPANSION, KEY_MEDIA_TYPE, KEY_COPIES, KEY_NOTES }, KEY_ID + "=?",
                new String[] {String.valueOf(id) }, null, null, null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Game game = new Game(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
        return game;
    }





    //get all games in a list view
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<Game>();
        String selectQuery = "SELECT *FROM " + TABLE_GAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setID(Integer.parseInt(cursor.getString(0)));
                game.setName(cursor.getString(1));
                game.setPlatform(cursor.getString(2));
                game.setRegion(cursor.getString(3));
                game.setRegion(cursor.getString(4));
                game.setExpansion(cursor.getString(5));
                game.setMediaType(cursor.getString(6));
                game.setCopies(cursor.getString(7));
                //game.setNotes(cursor.getString(8));
                gameList.add(game);
            } while ( cursor.moveToNext());
        }
        return gameList;
    }

    //update single game
    public int updateGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName());
        values.put(KEY_PLATFORM, game.getPlatform());
        //todo loput

        //updating row
        return db.update(TABLE_GAME, values, KEY_ID + " = ?", new String[] {String.valueOf(game.getID())});
    }

    //deleting single game
    public void deleteGame(Game game){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAME, KEY_ID + " = ?", new String[]{String.valueOf(game.getID())});
        db.close();
    }

    //getting game count
    public int getGameCount(){
        String countQuery = "SELECT *FROM " + TABLE_GAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

     */
}
