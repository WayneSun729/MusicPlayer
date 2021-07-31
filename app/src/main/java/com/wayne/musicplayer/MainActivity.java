package com.wayne.musicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wayne.musicplayer.widget.ControlCenter;
import com.wayne.musicplayer.widget.HistoryFragment;
import com.wayne.musicplayer.widget.SongSheetFragment;

/**
 * @author Wayne
 */
public class MainActivity extends AppCompatActivity {

    private ControlCenter controlCenter;

    MainActivityViewModel mainActivityViewModel;

    ControlMusicStartReceiver controlMusicStartReceiver;
    ShowMusicStartReceiver showMusicStartReceiver;
    TextView tvNowSong;
    Fragment fragNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNowSong = findViewById(R.id.tv_nowSong);
        tvNowSong.setSelected(true);
        controlCenter = findViewById(R.id.controlCenter);
        Button btnSongList = findViewById(R.id.btn_songList);
        Button btnHistory = findViewById(R.id.btn_history);
        SongSheetFragment fragSongSheet = new SongSheetFragment();
        HistoryFragment fragHistory = new HistoryFragment();
        btnSongList.setOnClickListener(v -> replaceFragment(fragSongSheet));
        btnHistory.setOnClickListener(v -> replaceFragment(fragHistory));
        replaceFragment(fragSongSheet);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(controlMusicStartReceiver);
//        controlCenter.myMediaPlayer.stop();
    }

    private void replaceFragment(Fragment fragment){
        if (fragNow == null || fragNow != fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frag_rcly, fragment);
            transaction.commit();
            fragNow = fragment;
        }
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
            tvNowSong.setText(strNowSong);
        }
    }

}