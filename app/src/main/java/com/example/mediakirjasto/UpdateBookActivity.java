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

public class UpdateBookActivity extends AppCompatActivity {

    EditText book_title_input, book_author_input, book_language_input, book_pages_input, book_copies_input, book_notes_input;
    Button book_update_button, book_delete_button;

    String book_id, book_title, book_author, book_language, book_pages, book_copies, book_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        book_title_input = findViewById(R.id.book_title_input2);
        book_author_input = findViewById(R.id.book_author_input2);
        book_language_input = findViewById(R.id.book_language_input2);
        book_pages_input = findViewById(R.id.book_pages_input2);
        book_copies_input = findViewById(R.id.book_copies_input2);
        book_notes_input = findViewById(R.id.book_notes_input2);
        book_update_button = findViewById(R.id.book_update_button);
        book_delete_button = findViewById(R.id.book_delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(book_title);
        };

        book_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(UpdateBookActivity.this);
                book_title = book_title_input.getText().toString().trim();
                book_author = book_author_input.getText().toString().trim();
                book_language = book_language_input.getText().toString().trim();
                book_pages = book_pages_input.getText().toString().trim();
                book_copies = book_copies_input.getText().toString().trim();
                book_notes = book_notes_input.getText().toString().trim();
                db.bookUpdateData(book_id, book_title, book_author, book_language, book_pages, book_copies, book_notes);
            }
        });
        book_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

            }
        });
    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("language")
                && getIntent().hasExtra("pages") && getIntent().hasExtra("media type") && getIntent().hasExtra("copies") && getIntent().hasExtra("notes")){
            //getting data
            book_id = getIntent().getStringExtra("id");
            book_title = getIntent().getStringExtra("title");
            book_author = getIntent().getStringExtra("author");
            book_language = getIntent().getStringExtra("language");
            book_pages = getIntent().getStringExtra("pages");
            book_copies = getIntent().getStringExtra("copies");
            book_notes = getIntent().getStringExtra("notes");

            //setting data
            book_title_input.setText(book_title);
            book_author_input.setText(book_author);
            book_language_input.setText(book_language);
            book_pages_input.setText(book_pages);
            book_copies_input.setText(book_copies);
            book_notes_input.setText(book_notes);

        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + book_title + " ?");
        builder.setMessage("Are you sure you want to delete " + book_title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler db = new DatabaseHandler(UpdateBookActivity.this);
                db.deleteOneBookRow(book_id);
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