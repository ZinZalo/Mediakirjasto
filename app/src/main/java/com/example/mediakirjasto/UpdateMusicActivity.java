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

public class UpdateMusicActivity extends AppCompatActivity {

    EditText music_title_input, music_artist_input, music_format_input, music_copies_input, music_notes_input;
    Button music_update_button, music_delete_button;

    String music_id, music_title, music_artist, music_format, music_copies, music_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_music);

        music_title_input = findViewById(R.id.music_title_input2);
        music_artist_input = findViewById(R.id.music_artist_input2);
        music_format_input = findViewById(R.id.music_format_input2);
        music_copies_input = findViewById(R.id.music_copies_input2);
        music_notes_input = findViewById(R.id.music_notes_input2);
        music_update_button = findViewById(R.id.music_update_button);
        music_delete_button = findViewById(R.id.music_delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(music_title);
        };

        music_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(UpdateMusicActivity.this);
                music_title = music_title_input.getText().toString().trim();
                music_artist = music_artist_input.getText().toString().trim();
                music_format = music_format_input.getText().toString().trim();
                music_copies = music_copies_input.getText().toString().trim();
                music_notes = music_notes_input.getText().toString().trim();
                db.musicUpdateData(music_id, music_title, music_artist, music_format, music_copies, music_notes);
            }
        });
        music_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

            }
        });
    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("artist") && getIntent().hasExtra("format")
                && getIntent().hasExtra("expansion") && getIntent().hasExtra("media type") && getIntent().hasExtra("copies") && getIntent().hasExtra("notes")){
            //getting data
            music_id = getIntent().getStringExtra("id");
            music_title = getIntent().getStringExtra("title");
            music_artist = getIntent().getStringExtra("artist");
            music_format = getIntent().getStringExtra("format");
            music_copies = getIntent().getStringExtra("copies");
            music_notes = getIntent().getStringExtra("notes");

            //setting data
            music_title_input.setText(music_title);
            music_artist_input.setText(music_artist);
            music_format_input.setText(music_format);
            music_copies_input.setText(music_copies);
            music_notes_input.setText(music_notes);

        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + music_title + " ?");
        builder.setMessage("Are you sure you want to delete " + music_title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler db = new DatabaseHandler(UpdateMusicActivity.this);
                db.deleteOneMusicRow(music_id);
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