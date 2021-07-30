package com.wayne.musicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicFileAdapter extends RecyclerView.Adapter<MusicFileAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_number;
        TextView tv_songName;
        TextView tv_singerName;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tv_number = itemView.findViewById(R.id.tv_num);
            tv_songName = itemView.findViewById(R.id.tv_songName);
            tv_singerName = itemView.findViewById(R.id.tv_singer);
        }

        @Override
    public void onClick(View v) {

    }
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcly_item_music_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MusicFileAdapter.ViewHolder holder, int position) {
        SongFile musicFile = MainActivityViewModel.getSongList().get(position);
        holder.tv_number.setText(position);
        holder.tv_songName.setText(musicFile.getSongName());
        holder.tv_singerName.setText(musicFile.getSingerName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
