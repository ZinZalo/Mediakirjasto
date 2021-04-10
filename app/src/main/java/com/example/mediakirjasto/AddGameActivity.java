package com.example.mediakirjasto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddGameActivity extends AppCompatActivity {

    EditText game_title_input, game_platform_input, game_region_input, game_expansion_input, game_media_type_input, game_copies_input, game_notes_input;
    Button game_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        game_title_input = findViewById(R.id.game_title_input);
        game_platform_input = findViewById(R.id.game_platform_input);
        game_region_input = findViewById(R.id.game_region_input);
        game_expansion_input = findViewById(R.id.game_expansion_input);
        game_media_type_input = findViewById(R.id.game_media_type_input);
        game_copies_input = findViewById(R.id.game_copies_input);
        game_notes_input = findViewById(R.id.game_notes_input);
        game_add_button = findViewById(R.id.game_add_button);
        game_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(AddGameActivity.this);
                db.addGame(game_title_input.getText().toString().trim(),
                        game_platform_input.getText().toString().trim(),
                        game_region_input.getText().toString().trim(),
                        game_expansion_input.getText().toString().trim(),
                        game_media_type_input.getText().toString().trim(),
                        game_copies_input.getText().toString().trim(),
                        game_notes_input.getText().toString().trim());
            }
        });
    }
}