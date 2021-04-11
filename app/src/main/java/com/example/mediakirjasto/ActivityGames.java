package com.example.mediakirjasto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityGames extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseHandler db;
    ArrayList<String>  game_id, game_title, game_platform, game_region, game_expansion, game_media_type, game_copies, game_notes;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.game_add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityGames.this, AddGameActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHandler(ActivityGames.this);
        game_id  = new ArrayList<>();
        game_title  = new ArrayList<>();
        game_platform  = new ArrayList<>();
        game_region  = new ArrayList<>();
        game_expansion  = new ArrayList<>();
        game_media_type  = new ArrayList<>();
        game_copies  = new ArrayList<>();
        game_notes  = new ArrayList<>();

        gameDisplayData();

        customAdapter = new CustomAdapter(ActivityGames.this,this, game_id, game_title, game_platform ,game_region, game_expansion, game_media_type, game_copies, game_notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityGames.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void gameDisplayData(){
        Cursor cursor = db.gameReadData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                game_id.add(cursor.getString(0));
                game_title.add(cursor.getString(1));
                game_platform.add(cursor.getString(2));
                game_region.add(cursor.getString(3));
                game_expansion.add(cursor.getString(4));
                game_media_type.add(cursor.getString(5));
                game_copies.add(cursor.getString(6));
                game_notes.add(cursor.getString(7));
            }
        }
    }

}