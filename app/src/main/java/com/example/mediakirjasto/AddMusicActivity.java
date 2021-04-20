package com.example.mediakirjasto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMusicActivity extends AppCompatActivity {

    EditText music_title_input, music_artist_input, music_format_input, music_copies_input, music_notes_input;
    Button music_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);

        music_title_input = findViewById(R.id.music_title_input);
        music_artist_input = findViewById(R.id.music_artist_input);
        music_format_input = findViewById(R.id.music_format_input);
        music_copies_input = findViewById(R.id.music_copies_input);
        music_notes_input = findViewById(R.id.music_notes_input);
        music_add_button = findViewById(R.id.music_add_button);
        music_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(AddMusicActivity.this);
                db.addMusic(music_title_input.getText().toString().trim(),
                        music_artist_input.getText().toString().trim(),
                        music_format_input.getText().toString().trim(),
                        music_copies_input.getText().toString().trim(),
                        music_notes_input.getText().toString().trim());
            }
        });
    }
}