package com.wayne.musicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wayne.musicplayer.widget.ControlCenter;

/**
 * @author Wayne
 */
public class MainActivity extends AppCompatActivity {

    public static ControlCenter controlCenter;

    MainActivityViewModel mainActivityViewModel;

    ControlMusicStartReceiver controlMusicStartReceiver;
    ShowMusicStartReceiver showMusicStartReceiver;
    TextView tv_nowSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_nowSong = findViewById(R.id.tv_nowSong);
        tv_nowSong.setSelected(true);
        controlCenter = findViewById(R.id.controlCenter);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        //初始化recyclerview并展示
        initSongs();
        RecyclerView rclvSongs = findViewById(R.id.rclv_songs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rclvSongs.setLayoutManager(layoutManager);
        SongFileAdapter songFileAdapter = new SongFileAdapter();
        rclvSongs.setAdapter(songFileAdapter);
        //注册监听点击引起音乐播放的广播
        IntentFilter intentFilterSelect = new IntentFilter();
        intentFilterSelect.addAction("SongSelected");
        controlMusicStartReceiver = new ControlMusicStartReceiver();
        registerReceiver(controlMusicStartReceiver, intentFilterSelect);
        //注册播放音乐后显示歌曲信息的广播
        IntentFilter intentFilterStart = new IntentFilter();
        intentFilterStart.addAction("SongStart");
        showMusicStartReceiver = new ShowMusicStartReceiver();
        registerReceiver(showMusicStartReceiver, intentFilterStart);
    }

    private void initSongs() {
        MainActivityViewModel.addMusicFile(new SongFile("五四特别版——错位时空","排骨",R.raw.example));
        MainActivityViewModel.addMusicFile(new SongFile("老古董","许嵩",R.raw.example2));
        MainActivityViewModel.addMusicFile(new SongFile("国际歌","唐朝",R.raw.example3));
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
            controlCenter.getBtnStart().performClick();
        }
    }
    
    class ShowMusicStartReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra("id", -1);
            SongFile nowSong = MainActivityViewModel.getSongList().get(id);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 200; i++) {
                if (i == 50 || i == 150) {
                    sb.append(nowSong.getSongName()).append("--").append(nowSong.getSingerName());
                }else {
                    sb.append(" ");
                }
            }
            String strNowSong = sb.toString();
            tv_nowSong.setText(strNowSong);
        }
    }

}