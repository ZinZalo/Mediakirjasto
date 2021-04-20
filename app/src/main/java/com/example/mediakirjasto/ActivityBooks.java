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

public class ActivityBooks extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView empty_textView;

    DatabaseHandler db;
    ArrayList<String> book_id, book_title, book_author, book_language, book_pages, book_copies, book_notes;
    CustomBooksAdapter customBooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.book_add_button);
        empty_imageView = findViewById(R.id.empty_imageView);
        empty_textView = findViewById(R.id.empty_textView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityBooks.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHandler(ActivityBooks.this);
        book_id  = new ArrayList<>();
        book_title  = new ArrayList<>();
        book_author  = new ArrayList<>();
        book_language  = new ArrayList<>();
        book_pages  = new ArrayList<>();
        book_copies  = new ArrayList<>();
        book_notes  = new ArrayList<>();

        bookDisplayData();

        customBooksAdapter = new CustomBooksAdapter(ActivityBooks.this, this, book_id, book_title, book_author, book_language, book_pages ,book_copies, book_notes);
        recyclerView.setAdapter(customBooksAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityBooks.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void bookDisplayData(){
        Cursor cursor = db.bookReadData();
        if (cursor.getCount() == 0){
            empty_imageView.setVisibility(View.VISIBLE);
            empty_textView.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_language.add(cursor.getString(3));
                book_pages.add(cursor.getString(4));
                book_copies.add(cursor.getString(5));
                book_notes.add(cursor.getString(6));
            }
            empty_imageView.setVisibility(View.GONE);
            empty_textView.setVisibility(View.GONE);
        }
    }
}