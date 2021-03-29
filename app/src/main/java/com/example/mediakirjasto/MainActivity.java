package com.example.mediakirjasto;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}