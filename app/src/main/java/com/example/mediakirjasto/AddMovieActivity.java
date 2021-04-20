package com.example.mediakirjasto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMovieActivity extends AppCompatActivity {

    EditText movie_title_input, movie_director_input, movie_format_input, movie_copies_input, movie_notes_input;
    Button movie_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        movie_title_input = findViewById(R.id.movie_title_input);
        movie_director_input = findViewById(R.id.movie_director_input);
        movie_format_input = findViewById(R.id.movie_format_input);
        movie_copies_input = findViewById(R.id.movie_copies_input);
        movie_notes_input = findViewById(R.id.movie_notes_input);
        movie_add_button = findViewById(R.id.movie_add_button);
        movie_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(AddMovieActivity.this);
                db.addMovie(movie_title_input.getText().toString().trim(),
                        movie_director_input.getText().toString().trim(),
                        movie_format_input.getText().toString().trim(),
                        movie_copies_input.getText().toString().trim(),
                        movie_notes_input.getText().toString().trim());
            }
        });
    }
}