package com.example.mediakirjasto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGames = (Button) findViewById(R.id.button_games);
        buttonGames.setOnClickListener(this);
        Button buttonBooks = (Button) findViewById(R.id.button_books);
        buttonBooks.setOnClickListener(this);
        Button buttonMovies = (Button) findViewById(R.id.button_movies);
        buttonMovies.setOnClickListener(this);
        Button buttonMusic = (Button) findViewById(R.id.button_music);
        buttonMusic.setOnClickListener(this);

        DatabaseHandler db = new DatabaseHandler(this);

        //Inserting games
        //Title, platform, region, expansion, media type, copies, notes
        //Log.d("Insert: ", "Inserting ..");
        //db.addGame(new Game("Tekken", "PSX", "EU", "0", "CD", "1", " "));


        //reading
        Log.d("Reading: ", "Reading all games..");
        List<Game> games = db.getAllGames();

        for (Game gm : games){
            String log = "Id: " + gm.getID() + " ,Name: " + gm.getName();
            Log.d("Name: ", log);
        }
    }
    //user taps button
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_books:
                Intent intent = new Intent(this, ActivityBooks.class);
                startActivity(intent);
                break;
            case R.id.button_games:
                intent = new Intent(this, ActivityGames.class);
                startActivity(intent);
                break;
            case R.id.button_movies:
                intent = new Intent(this, ActivityMovies.class);
                startActivity(intent);
                break;
            case R.id.button_music:
                intent = new Intent(this, ActivityMusic.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }



}