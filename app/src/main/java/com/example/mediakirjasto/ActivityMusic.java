package com.example.mediakirjasto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityMusic extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView empty_textView;

    DatabaseHandler db;
    ArrayList<String> music_id, music_title, music_artist, music_format, music_copies, music_notes;
    CustomMusicAdapter customMusicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.music_add_button);
        empty_imageView = findViewById(R.id.empty_imageView);
        empty_textView = findViewById(R.id.empty_textView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMusic.this, AddMusicActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHandler(ActivityMusic.this);
        music_id  = new ArrayList<>();
        music_title  = new ArrayList<>();
        music_artist  = new ArrayList<>();
        music_format  = new ArrayList<>();
        music_copies  = new ArrayList<>();
        music_notes  = new ArrayList<>();

        musicDisplayData();

        customMusicAdapter = new CustomMusicAdapter(ActivityMusic.this,this, music_id, music_title, music_artist, music_format, music_copies, music_notes);
        recyclerView.setAdapter(customMusicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityMusic.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void musicDisplayData(){
        Cursor cursor = db.musicReadData();
        if (cursor.getCount() == 0){
            empty_imageView.setVisibility(View.VISIBLE);
            empty_textView.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                music_id.add(cursor.getString(0));
                music_title.add(cursor.getString(1));
                music_artist.add(cursor.getString(2));
                music_format.add(cursor.getString(3));
                music_copies.add(cursor.getString(4));
                music_notes.add(cursor.getString(5));
            }
            empty_imageView.setVisibility(View.GONE);
            empty_textView.setVisibility(View.GONE);
        }
    }
}