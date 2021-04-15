package com.example.mediakirjasto;

import android.annotation.SuppressLint;
import android.app.Activity;
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

    DatabaseHandler(Context context) {
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

    void gameUpdateData(String row_id, String game_title, String game_platform, String game_region, String game_expansion, String game_media_type, String game_copies, String game_notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, game_title);
        cv.put(KEY_PLATFORM, game_platform);
        cv.put(KEY_REGION, game_region);
        cv.put(KEY_EXPANSION, game_expansion);
        cv.put(KEY_MEDIA_TYPE, game_media_type);
        cv.put(KEY_COPIES, game_copies);
        cv.put(KEY_NOTES, game_notes);

        long result = db.update(TABLE_GAME, cv, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to update.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneGameRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_GAME, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Succesfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }

}
