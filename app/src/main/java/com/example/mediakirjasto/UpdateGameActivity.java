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

public class UpdateGameActivity extends AppCompatActivity {

    EditText game_title_input, game_platform_input, game_region_input, game_expansion_input, game_media_type_input, game_copies_input, game_notes_input;
    Button game_update_button, game_delete_button;

    String game_id, game_title, game_platform, game_region, game_expansion, game_media_type, game_copies, game_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_game);

        game_title_input = findViewById(R.id.game_title_input2);
        game_platform_input = findViewById(R.id.game_platform_input2);
        game_region_input = findViewById(R.id.game_region_input2);
        game_expansion_input = findViewById(R.id.game_expansion_input2);
        game_media_type_input = findViewById(R.id.game_media_type_input2);
        game_copies_input = findViewById(R.id.game_copies_input2);
        game_notes_input = findViewById(R.id.game_notes_input2);
        game_update_button = findViewById(R.id.game_update_button);
        game_delete_button = findViewById(R.id.game_delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(game_title);
        };

        game_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(UpdateGameActivity.this);
                game_title = game_title_input.getText().toString().trim();
                game_platform = game_platform_input.getText().toString().trim();
                game_region = game_region_input.getText().toString().trim();
                game_expansion = game_expansion_input.getText().toString().trim();
                game_media_type = game_media_type_input.getText().toString().trim();
                game_copies = game_copies_input.getText().toString().trim();
                game_notes = game_notes_input.getText().toString().trim();
                db.gameUpdateData(game_id, game_title, game_platform, game_region, game_expansion, game_media_type, game_copies, game_notes);
            }
        });
        game_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

            }
        });
    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("platform") && getIntent().hasExtra("region")
        && getIntent().hasExtra("expansion") && getIntent().hasExtra("media type") && getIntent().hasExtra("copies") && getIntent().hasExtra("notes")){
            //getting data
            game_id = getIntent().getStringExtra("id");
            game_title = getIntent().getStringExtra("title");
            game_platform = getIntent().getStringExtra("platform");
            game_region = getIntent().getStringExtra("region");
            game_expansion = getIntent().getStringExtra("expansion");
            game_media_type = getIntent().getStringExtra("media type");
            game_copies = getIntent().getStringExtra("copies");
            game_notes = getIntent().getStringExtra("notes");

            //setting data
            game_title_input.setText(game_title);
            game_platform_input.setText(game_platform);
            game_region_input.setText(game_region);
            game_expansion_input.setText(game_expansion);
            game_media_type_input.setText(game_media_type);
            game_copies_input.setText(game_copies);
            game_notes_input.setText(game_notes);

        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + game_title + " ?");
        builder.setMessage("Are you sure you want to delete " + game_title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler db = new DatabaseHandler(UpdateGameActivity.this);
                db.deleteOneGameRow(game_id);
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