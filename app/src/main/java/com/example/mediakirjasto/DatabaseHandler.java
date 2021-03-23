package com.example.mediakirjasto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
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

    }
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAME_TABLE = "CREATE TABLE " + TABLE_GAME + "("
            + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_PLATFORM + "TEXT," + KEY_REGION + "TEXT," + KEY_EXPANSION + "TEXT," + KEY_MEDIA_TYPE + "TEXT," + KEY_COPIES + "TEXT," + KEY_NOTES + "TEXT" + ")";
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
    void addGame(Game game){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName());
        values.put(KEY_PLATFORM, game.getPlatform());
        values.put(KEY_REGION, game.getRegion());
        values.put(KEY_EXPANSION, game.getExpansion());
        values.put(KEY_MEDIA_TYPE, game.getMediaType());
        values.put(KEY_COPIES, game.getCopies());
        values.put(KEY_NOTES,game.getNotes());

        //Inserting row
        db.insert(TABLE_GAME, null, values);
        db.close();
    }

    //get single game
    Game getGame(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAME, new String[] { KEY_ID,
                KEY_NAME, KEY_PLATFORM, KEY_REGION, KEY_PLATFORM, KEY_REGION, KEY_EXPANSION, KEY_MEDIA_TYPE, KEY_COPIES, KEY_NOTES }, KEY_ID + "=?",
                new String[] {String.valueOf(id) }, null, null, null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Game game = new Game(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return game;
    }

    //get all games in a list view
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<Game>();
        String selectQuery = "SELECT *FROM" + TABLE_GAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setID(Integer.parseInt(cursor.getString(0)));
                game.setName(cursor.getString(1));
                game.setPlatform(cursor.getString(2));
                game.setRegion(cursor.getString(3));
                game.setPlatform(cursor.getString(4));
                game.setRegion(cursor.getString(5));
                game.setExpansion(cursor.getInt(6));
                game.setMediaType(cursor.getString(7));
                game.setCopies(cursor.getInt(8));
                game.setNotes(cursor.getString(9));
                gameList.add(game);
            } while ( cursor.moveToNext());
        }
        return gameList;
    }
}
