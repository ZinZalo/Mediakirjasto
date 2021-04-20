package com.example.mediakirjasto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomBooksAdapter extends RecyclerView.Adapter<CustomBooksAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList book_id, book_title, book_author, book_language, book_pages, book_copies, book_notes;

    CustomBooksAdapter(Activity activity,
                       Context context,
                       ArrayList book_id,
                       ArrayList book_title,
                       ArrayList book_author,
                       ArrayList book_language,
                       ArrayList book_pages,
                       ArrayList book_copies,
                       ArrayList book_notes) {
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_language = book_language;
        this.book_pages = book_pages;
        this.book_copies = book_copies;
        this.book_notes = book_notes;
    }

    @NonNull
    @Override
    public CustomBooksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_row, parent, false);
        return new CustomBooksAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomBooksAdapter.MyViewHolder holder, final int position) {
        //holder.game_id_txt.setText(String.valueOf(game_id.get(position)));
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
        holder.book_language_txt.setText(String.valueOf(book_language.get(position)));
        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));
        holder.book_copies_txt.setText(String.valueOf(book_copies.get(position)));
        holder.book_notes_txt.setText(String.valueOf(book_notes.get(position)));
        holder.bookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateGameActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("title", String.valueOf(book_title.get(position)));
                intent.putExtra("author", String.valueOf(book_author.get(position)));
                intent.putExtra("language", String.valueOf(book_language.get(position)));
                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                intent.putExtra("copies", String.valueOf(book_copies.get(position)));
                intent.putExtra("notes", String.valueOf(book_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_title_txt, book_author_txt, book_language_txt, book_pages_txt, book_copies_txt, book_notes_txt;
        ConstraintLayout bookLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_language_txt = itemView.findViewById(R.id.book_language_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            book_copies_txt = itemView.findViewById(R.id.book_copies_txt);
            book_notes_txt = itemView.findViewById(R.id.book_notes_txt);
            bookLayout = itemView.findViewById(R.id.bookLayout);
        }
    }
}
