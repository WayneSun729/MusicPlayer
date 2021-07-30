package com.wayne.musicplayer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Wayne
 */
public class MainActivity extends AppCompatActivity {
    public static ControlCenter controlCenter;
    MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlCenter = findViewById(R.id.controlCenter);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        initSongs();

        RecyclerView rclvSongs = findViewById(R.id.rclv_songs);
        rclvSongs.setLayoutManager(layoutManager);
        SongFileAdapter songFileAdapter = new SongFileAdapter();
        rclvSongs.setAdapter(songFileAdapter);
    }

    private void initSongs() {
        MainActivityViewModel.getSongList().add(new SongFile("五四特别版——错位时空","排骨"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        controlCenter.myMediaPlayer.stop();
    }


}