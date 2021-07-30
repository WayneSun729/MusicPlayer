package com.wayne.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * @author Wayne
 */
public class MainActivity extends AppCompatActivity {
    ControlCenter controlCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlCenter = findViewById(R.id.controlCenter);
        controlCenter.setMusic(R.raw.example);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        controlCenter.myMediaPlayer.stop();
    }
}