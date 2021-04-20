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

public class CustomMovieAdapter extends RecyclerView.Adapter<CustomMovieAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList movie_id, movie_title, movie_director, movie_format, movie_copies, movie_notes;

    CustomMovieAdapter(Activity activity,
                       Context context,
                       ArrayList movie_id,
                       ArrayList movie_title,
                       ArrayList movie_director,
                       ArrayList movie_format,
                       ArrayList movie_copies,
                       ArrayList movie_notes){
        this.activity = activity;
        this.context = context;
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.movie_director = movie_director;
        this.movie_format = movie_format;
        this.movie_copies = movie_copies;
        this.movie_notes = movie_notes;

    }

    @NonNull
    @Override
    public CustomMovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_row, parent, false);
        return new CustomMovieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMovieAdapter.MyViewHolder holder, final int position) {
        //holder.movie_id_txt.setText(String.valueOf(movie_id.get(position)));
        holder.movie_title_txt.setText(String.valueOf(movie_title.get(position)));
        holder.movie_director_txt.setText(String.valueOf(movie_director.get(position)));
        holder.movie_format_txt.setText(String.valueOf(movie_format.get(position)));
        holder.movie_copies_txt.setText(String.valueOf(movie_copies.get(position)));
        holder.movie_notes_txt.setText(String.valueOf(movie_notes.get(position)));
        holder.movieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateMovieActivity.class);
                intent.putExtra("id", String.valueOf(movie_id.get(position)));
                intent.putExtra("title", String.valueOf(movie_title.get(position)));
                intent.putExtra("director", String.valueOf(movie_director.get(position)));
                intent.putExtra("format", String.valueOf(movie_format.get(position)));
                intent.putExtra("copies", String.valueOf(movie_copies.get(position)));
                intent.putExtra("notes", String.valueOf(movie_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView /*movie_id_txt,*/ movie_title_txt, movie_director_txt, movie_format_txt, movie_expansion_txt, movie_media_type_txt, movie_copies_txt, movie_notes_txt;
        ConstraintLayout movieLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //movie_id_txt = itemView.findViewById(R.id.movie_id_txt);
            movie_title_txt = itemView.findViewById(R.id.movie_title_txt);
            movie_director_txt = itemView.findViewById(R.id.movie_director_txt);
            movie_format_txt = itemView.findViewById(R.id.movie_format_txt);
            movie_copies_txt = itemView.findViewById(R.id.movie_copies_txt);
            movie_notes_txt = itemView.findViewById(R.id.movie_notes_txt);
            movieLayout = itemView.findViewById(R.id.movieLayout);
        }
    }
    
}
