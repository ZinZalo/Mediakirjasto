package com.example.mediakirjasto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    EditText book_title_input, book_author_input, book_language_input, book_pages_input, book_copies_input, book_notes_input;
    Button book_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        book_title_input = findViewById(R.id.book_title_input);
        book_author_input = findViewById(R.id.book_author_input);
        book_language_input = findViewById(R.id.book_language_input);
        book_pages_input = findViewById(R.id.book_pages_input);
        book_copies_input = findViewById(R.id.book_copies_input);
        book_notes_input = findViewById(R.id.book_notes_input);
        book_add_button = findViewById(R.id.book_add_button);
        book_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(AddBookActivity.this);
                db.addBook(book_title_input.getText().toString().trim(),
                        book_author_input.getText().toString().trim(),
                        book_language_input.getText().toString().trim(),
                        book_pages_input.getText().toString().trim(),
                        book_copies_input.getText().toString().trim(),
                        book_notes_input.getText().toString().trim());
            }
        });
    }
}