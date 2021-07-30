package com.wayne.musicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

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

    ControlMusicStartReceiver controlMusicStartReceiver;

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
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SongSelected");
        controlMusicStartReceiver = new ControlMusicStartReceiver();
        registerReceiver(controlMusicStartReceiver, intentFilter);
    }

    private void initSongs() {
        MainActivityViewModel.addMusicFile(new SongFile("五四特别版——错位时空","排骨",R.raw.example));
        MainActivityViewModel.addMusicFile(new SongFile("老古董","许嵩",R.raw.example2));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(controlMusicStartReceiver);
//        controlCenter.myMediaPlayer.stop();
    }

    class ControlMusicStartReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (controlCenter.getMyMediaPlayer() != null) {
                controlCenter.getMyMediaPlayer().stop();
            }
            int id = intent.getIntExtra("id", -1);
            if (id != -1) {
                controlCenter.setMusic(MainActivityViewModel.getSongList().get(id));
            }
//            Log.d("Wayne", String.valueOf(intent.getIntExtra("id", 1)));
            controlCenter.btnStart.performClick();
        }
    }

}