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
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_PAGES = "pages";
    private static final String KEY_ARTIST = "artist";
    private static final String KEY_FORMAT = "format";
    private static final String KEY_DIRECTOR = "director";

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

        String CREATE_BOOKS_TABLE =
                " CREATE TABLE " + TABLE_BOOK + "(" +
                        KEY_ID + " INTEGER PRIMARY KEY, " +
                        KEY_NAME + " TEXT, " +
                        KEY_AUTHOR + " TEXT, " +
                        KEY_LANGUAGE + " TEXT, " +
                        KEY_PAGES + " TEXT, " +
                        KEY_COPIES + " TEXT, " +
                        KEY_NOTES + " TEXT " + ")";

        String CREATE_MOVIES_TABLE =
                " CREATE TABLE " + TABLE_MOVIE + "(" +
                        KEY_ID + " INTEGER PRIMARY KEY, " +
                        KEY_NAME + " TEXT, " +
                        KEY_DIRECTOR + " TEXT, " +
                        KEY_FORMAT + " TEXT, " +
                        KEY_COPIES + " TEXT, " +
                        KEY_NOTES + " TEXT " + ")";

        String CREATE_MUSIC_TABLE =
                " CREATE TABLE " + TABLE_MUSIC + "(" +
                        KEY_ID + " INTEGER PRIMARY KEY, " +
                        KEY_NAME + " TEXT, " +
                        KEY_ARTIST + " TEXT, " +
                        KEY_FORMAT + " TEXT, " +
                        KEY_COPIES + " TEXT, " +
                        KEY_NOTES + " TEXT " + ")";

        db.execSQL(CREATE_GAME_TABLE);
        db.execSQL(CREATE_BOOKS_TABLE);
        db.execSQL(CREATE_MOVIES_TABLE);
        db.execSQL(CREATE_MUSIC_TABLE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSIC);

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
    }

    void addBook(String bookTitle, String bookAuthor, String bookLanguage, String bookPages, String bookCopies, String bookNotes){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bookTitle);
        values.put(KEY_AUTHOR, bookAuthor);
        values.put(KEY_LANGUAGE, bookLanguage);
        values.put(KEY_PAGES, bookPages);
        values.put(KEY_COPIES, bookCopies);
        values.put(KEY_NOTES, bookNotes);

        //Inserting row
        long result = db.insert(TABLE_BOOK, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
    }

    void addMovie(String movieTitle, String movieDirector, String movieFormat, String movieCopies, String movieNotes){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, movieTitle);
        values.put(KEY_DIRECTOR, movieDirector);
        values.put(KEY_FORMAT, movieFormat);
        values.put(KEY_COPIES, movieCopies);
        values.put(KEY_NOTES, movieNotes);

        //Inserting row
        long result = db.insert(TABLE_MOVIE, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
    }

    void addMusic(String musicTitle, String musicArtist, String musicFormat, String musicCopies, String musicNotes){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, musicTitle);
        values.put(KEY_ARTIST, musicArtist);
        values.put(KEY_FORMAT, musicFormat);
        values.put(KEY_COPIES, musicCopies);
        values.put(KEY_NOTES, musicNotes);

        //Inserting row
        long result = db.insert(TABLE_MUSIC, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
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

    Cursor bookReadData(){
        String query = "SELECT * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor movieReadData(){
        String query = "SELECT * FROM " + TABLE_MOVIE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor musicReadData(){
        String query = "SELECT * FROM " + TABLE_MUSIC;
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

    void bookUpdateData(String row_id, String book_title, String book_author, String book_language, String book_pages, String book_copies, String book_notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, book_title);
        cv.put(KEY_AUTHOR, book_author);
        cv.put(KEY_LANGUAGE, book_language);
        cv.put(KEY_PAGES, book_pages);
        cv.put(KEY_COPIES, book_copies);
        cv.put(KEY_NOTES, book_notes);

        long result = db.update(TABLE_BOOK, cv, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to update.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void movieUpdateData(String row_id, String movie_title, String movie_director, String movie_format, String movie_copies, String movie_notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, movie_title);
        cv.put(KEY_DIRECTOR, movie_director);
        cv.put(KEY_FORMAT, movie_format);
        cv.put(KEY_COPIES, movie_copies);
        cv.put(KEY_NOTES, movie_notes);

        long result = db.update(TABLE_MOVIE, cv, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to update.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void musicUpdateData(String row_id, String music_title, String music_artist, String music_format, String music_copies, String music_notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, music_title);
        cv.put(KEY_ARTIST, music_artist);
        cv.put(KEY_FORMAT, music_format);
        cv.put(KEY_COPIES, music_copies);
        cv.put(KEY_NOTES, music_notes);

        long result = db.update(TABLE_MUSIC, cv, "id=?", new String[]{row_id});
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

    void deleteOneBookRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_BOOK, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Succesfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneMovieRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_MOVIE, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Succesfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneMusicRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_MUSIC, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Succesfully deleted.", Toast.LENGTH_SHORT).show();
        }
    }

}
