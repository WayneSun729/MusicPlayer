package com.wayne.musicplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author Wayne
 */
public class ControlCenter extends LinearLayout implements View.OnClickListener {
    MyMediaPlayer myMediaPlayer;

    public ControlCenter(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.buttons_control_center, this);

        Button btnPreSong = findViewById(R.id.btn_preSong);
        btnPreSong.setOnClickListener(this);
        Button btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        Button btnPause = findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);
        Button btnNextSong = findViewById(R.id.btn_nextSong);
        btnNextSong.setOnClickListener(this);

    }

    public void setMusic(int resId){
        myMediaPlayer = new MyMediaPlayer(resId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_preSong:
                Toast.makeText(MyApplication.context, "上一首",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_start:
                if (myMediaPlayer!=null) {
                    myMediaPlayer.start();
                }
                break;
            case R.id.btn_pause:
                if (myMediaPlayer!=null) {
                    myMediaPlayer.pause();
                }
                break;
            case R.id.btn_nextSong:
                Toast.makeText(MyApplication.context, "下一首",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
