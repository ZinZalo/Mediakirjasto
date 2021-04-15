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

public class CustomGamesAdapter extends RecyclerView.Adapter<CustomGamesAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList game_id, game_title, game_platform, game_region, game_expansion, game_media_type, game_copies, game_notes;

    CustomGamesAdapter(Activity activity,
                       Context context,
                       ArrayList game_id,
                       ArrayList game_title,
                       ArrayList game_platform,
                       ArrayList game_region,
                       ArrayList game_expansion,
                       ArrayList game_media_type,
                       ArrayList game_copies,
                       ArrayList game_notes){
        this.activity = activity;
        this.context = context;
        this.game_id = game_id;
        this.game_title = game_title;
        this.game_platform = game_platform;
        this.game_region = game_region;
        this.game_expansion = game_expansion;
        this.game_media_type = game_media_type;
        this.game_copies = game_copies;
        this.game_notes = game_notes;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.game_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //holder.game_id_txt.setText(String.valueOf(game_id.get(position)));
        holder.game_title_txt.setText(String.valueOf(game_title.get(position)));
        holder.game_platform_txt.setText(String.valueOf(game_platform.get(position)));
        holder.game_region_txt.setText(String.valueOf(game_region.get(position)));
        holder.game_expansion_txt.setText(String.valueOf(game_expansion.get(position)));
        holder.game_media_type_txt.setText(String.valueOf(game_media_type.get(position)));
        holder.game_copies_txt.setText(String.valueOf(game_copies.get(position)));
        holder.game_notes_txt.setText(String.valueOf(game_notes.get(position)));
        holder.gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateGameActivity.class);
                intent.putExtra("id", String.valueOf(game_id.get(position)));
                intent.putExtra("title", String.valueOf(game_title.get(position)));
                intent.putExtra("platform", String.valueOf(game_platform.get(position)));
                intent.putExtra("region", String.valueOf(game_region.get(position)));
                intent.putExtra("expansion", String.valueOf(game_expansion.get(position)));
                intent.putExtra("media type", String.valueOf(game_media_type.get(position)));
                intent.putExtra("copies", String.valueOf(game_copies.get(position)));
                intent.putExtra("notes", String.valueOf(game_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return game_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView /*game_id_txt,*/ game_title_txt, game_platform_txt, game_region_txt, game_expansion_txt, game_media_type_txt, game_copies_txt, game_notes_txt;
        ConstraintLayout gameLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //game_id_txt = itemView.findViewById(R.id.game_id_txt);
            game_title_txt = itemView.findViewById(R.id.game_title_txt);
            game_platform_txt = itemView.findViewById(R.id.game_platform_txt);
            game_region_txt = itemView.findViewById(R.id.game_region_txt);
            game_expansion_txt = itemView.findViewById(R.id.game_expansion_txt);
            game_media_type_txt = itemView.findViewById(R.id.game_media_type_txt);
            game_copies_txt = itemView.findViewById(R.id.game_copies_txt);
            game_notes_txt = itemView.findViewById(R.id.game_notes_txt);
            gameLayout = itemView.findViewById(R.id.gameLayout);
        }
    }
}
