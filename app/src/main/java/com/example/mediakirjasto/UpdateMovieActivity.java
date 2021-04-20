package com.example.mediakirjasto;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMovieActivity extends AppCompatActivity {

    EditText movie_title_input, movie_director_input, movie_format_input, movie_copies_input, movie_notes_input;
    Button movie_update_button, movie_delete_button;

    String movie_id, movie_title, movie_director, movie_format, movie_copies, movie_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        movie_title_input = findViewById(R.id.movie_title_input2);
        movie_director_input = findViewById(R.id.movie_director_input2);
        movie_format_input = findViewById(R.id.movie_format_input2);
        movie_copies_input = findViewById(R.id.movie_copies_input2);
        movie_notes_input = findViewById(R.id.movie_notes_input2);
        movie_update_button = findViewById(R.id.movie_update_button);
        movie_delete_button = findViewById(R.id.movie_delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(movie_title);
        };

        movie_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(UpdateMovieActivity.this);
                movie_title = movie_title_input.getText().toString().trim();
                movie_director = movie_director_input.getText().toString().trim();
                movie_format = movie_format_input.getText().toString().trim();
                movie_copies = movie_copies_input.getText().toString().trim();
                movie_notes = movie_notes_input.getText().toString().trim();
                db.movieUpdateData(movie_id, movie_title, movie_director, movie_format, movie_copies, movie_notes);
            }
        });
        movie_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

            }
        });
    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("director") && getIntent().hasExtra("format")
                && getIntent().hasExtra("expansion") && getIntent().hasExtra("media type") && getIntent().hasExtra("copies") && getIntent().hasExtra("notes")){
            //getting data
            movie_id = getIntent().getStringExtra("id");
            movie_title = getIntent().getStringExtra("title");
            movie_director = getIntent().getStringExtra("director");
            movie_format = getIntent().getStringExtra("format");
            movie_copies = getIntent().getStringExtra("copies");
            movie_notes = getIntent().getStringExtra("notes");

            //setting data
            movie_title_input.setText(movie_title);
            movie_director_input.setText(movie_director);
            movie_format_input.setText(movie_format);
            movie_copies_input.setText(movie_copies);
            movie_notes_input.setText(movie_notes);

        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + movie_title + " ?");
        builder.setMessage("Are you sure you want to delete " + movie_title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler db = new DatabaseHandler(UpdateMovieActivity.this);
                db.deleteOneMovieRow(movie_id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}