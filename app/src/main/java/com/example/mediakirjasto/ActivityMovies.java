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

public class ActivityMovies extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView empty_textView;

    DatabaseHandler db;
    ArrayList<String> movie_id, movie_title, movie_director, movie_format, movie_copies, movie_notes;
    CustomMovieAdapter customMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.movie_add_button);
        empty_imageView = findViewById(R.id.empty_imageView);
        empty_textView = findViewById(R.id.empty_textView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMovies.this, AddMovieActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHandler(ActivityMovies.this);
        movie_id  = new ArrayList<>();
        movie_title  = new ArrayList<>();
        movie_director  = new ArrayList<>();
        movie_format  = new ArrayList<>();
        movie_copies  = new ArrayList<>();
        movie_notes  = new ArrayList<>();

        movieDisplayData();

        customMovieAdapter = new CustomMovieAdapter(ActivityMovies.this, this, movie_id, movie_title, movie_director, movie_format , movie_copies, movie_notes);
        recyclerView.setAdapter(customMovieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityMovies.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void movieDisplayData(){
        Cursor cursor = db.movieReadData();
        if (cursor.getCount() == 0){
            empty_imageView.setVisibility(View.VISIBLE);
            empty_textView.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                movie_id.add(cursor.getString(0));
                movie_title.add(cursor.getString(1));
                movie_director.add(cursor.getString(2));
                movie_format.add(cursor.getString(3));
                movie_copies.add(cursor.getString(4));
                movie_notes.add(cursor.getString(5));
            }
            empty_imageView.setVisibility(View.GONE);
            empty_textView.setVisibility(View.GONE);
        }
    }
}