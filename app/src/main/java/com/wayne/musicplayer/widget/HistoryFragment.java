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

public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化recyclerview并展示
        MainActivity parentActivity = (MainActivity) getActivity();
        if (parentActivity != null){
            if (MainActivityViewModel.getHistoryArrayList().size()==0) {
                initSongs();
            }
            RecyclerView rclvHistory = parentActivity.findViewById(R.id.rclv_history);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
            rclvHistory.setLayoutManager(layoutManager);
            SongFileAdapter songFileAdapter = new SongFileAdapter();
            songFileAdapter.setFlag(MainActivityViewModel.FLAG_HISTORY);
            rclvHistory.setAdapter(songFileAdapter);
        }

    }

    private void initSongs() {
        MainActivityViewModel.addMusicFileToHistory(new SongFile("五四特别版——错位时空","排骨",R.raw.example));
        MainActivityViewModel.addMusicFileToHistory(new SongFile("老古董","许嵩",R.raw.example2));
    }
}
