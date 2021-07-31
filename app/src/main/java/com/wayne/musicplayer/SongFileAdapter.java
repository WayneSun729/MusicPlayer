package com.wayne.musicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wayne.musicplayer.util.BroadcastSenderUtils;

/**
 * @author Wayne
 */
public class SongFileAdapter extends RecyclerView.Adapter<SongFileAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNumber;
        TextView tvSongName;
        TextView tvSingerName;
        int id;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_num);
            tvSongName = itemView.findViewById(R.id.tv_songName);
            tvSingerName = itemView.findViewById(R.id.tv_singer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            BroadcastSenderUtils.create("SongSelected", id).sendBroadCast();
            BroadcastSenderUtils.create("SongStart", id).sendBroadCast();
    }
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.rcly_item_music_file, parent, false);
        ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  SongFileAdapter.ViewHolder holder, int position) {
        SongFile musicFile = MainActivityViewModel.getSongList().get(position);
        holder.tvNumber.setText(String.valueOf(position+1));
        holder.tvSongName.setText(musicFile.getSongName());
        holder.tvSingerName.setText(musicFile.getSingerName());
        holder.id = musicFile.getId();
    }

    @Override
    public int getItemCount() {
        return MainActivityViewModel.getSongList().size();
    }

}
