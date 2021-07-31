package com.wayne.musicplayer.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wayne.musicplayer.MainActivity;
import com.wayne.musicplayer.MainActivityViewModel;
import com.wayne.musicplayer.MyApplication;
import com.wayne.musicplayer.R;
import com.wayne.musicplayer.SongFile;
import com.wayne.musicplayer.SongFileAdapter;

public class SongSheetFragment extends Fragment {

    private final int FLAG_SONGSHEET = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_songsheet, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化recyclerview并展示
        MainActivity parentActivity = (MainActivity) getActivity();
        if (parentActivity != null){
            if (MainActivityViewModel.getSongList().size()==0){
                initSongs();
            }
            RecyclerView rclvSongs = parentActivity.findViewById(R.id.rclv_songs);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
            rclvSongs.setLayoutManager(layoutManager);
            SongFileAdapter songFileAdapter = new SongFileAdapter();
            songFileAdapter.setFlag(MainActivityViewModel.FLAG_SONGSHEET);
            rclvSongs.setAdapter(songFileAdapter);
        }

    }

    private void initSongs() {
        MainActivityViewModel.addMusicFile(new SongFile("五四特别版——错位时空","排骨",R.raw.example));
        MainActivityViewModel.addMusicFile(new SongFile("老古董","许嵩",R.raw.example2));
        MainActivityViewModel.addMusicFile(new SongFile("国际歌","唐朝",R.raw.example3));
    }
}
