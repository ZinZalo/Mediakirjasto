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

public class CustomMusicAdapter extends RecyclerView.Adapter<CustomMusicAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList music_id, music_title, music_artist, music_format, music_copies, music_notes;

    CustomMusicAdapter(Activity activity,
                       Context context,
                       ArrayList music_id,
                       ArrayList music_title,
                       ArrayList music_artist,
                       ArrayList music_format,
                       ArrayList music_copies,
                       ArrayList music_notes){
        this.activity = activity;
        this.context = context;
        this.music_id = music_id;
        this.music_title = music_title;
        this.music_artist = music_artist;
        this.music_format = music_format;
        this.music_copies = music_copies;
        this.music_notes = music_notes;

    }

    @NonNull
    @Override
    public CustomMusicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.music_row, parent, false);
        return new CustomMusicAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMusicAdapter.MyViewHolder holder, final int position) {
        //holder.music_id_txt.setText(String.valueOf(music_id.get(position)));
        holder.music_title_txt.setText(String.valueOf(music_title.get(position)));
        holder.music_artist_txt.setText(String.valueOf(music_artist.get(position)));
        holder.music_format_txt.setText(String.valueOf(music_format.get(position)));
        holder.music_copies_txt.setText(String.valueOf(music_copies.get(position)));
        holder.music_notes_txt.setText(String.valueOf(music_notes.get(position)));
        holder.musicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateMusicActivity.class);
                intent.putExtra("id", String.valueOf(music_id.get(position)));
                intent.putExtra("title", String.valueOf(music_title.get(position)));
                intent.putExtra("artist", String.valueOf(music_artist.get(position)));
                intent.putExtra("format", String.valueOf(music_format.get(position)));
                intent.putExtra("copies", String.valueOf(music_copies.get(position)));
                intent.putExtra("notes", String.valueOf(music_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return music_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView /*music_id_txt,*/ music_title_txt, music_artist_txt, music_format_txt, music_copies_txt, music_notes_txt;
        ConstraintLayout musicLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //music_id_txt = itemView.findViewById(R.id.music_id_txt);
            music_title_txt = itemView.findViewById(R.id.music_title_txt);
            music_artist_txt = itemView.findViewById(R.id.music_artist_txt);
            music_format_txt = itemView.findViewById(R.id.music_format_txt);
            music_copies_txt = itemView.findViewById(R.id.music_copies_txt);
            music_notes_txt = itemView.findViewById(R.id.music_notes_txt);
            musicLayout = itemView.findViewById(R.id.musicLayout);
        }
    }
}
