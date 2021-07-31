package com.wayne.musicplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wayne.musicplayer.MyMediaPlayer;
import com.wayne.musicplayer.R;
import com.wayne.musicplayer.SongFile;

/**
 * @author Wayne
 */
public class ControlCenter extends LinearLayout implements View.OnClickListener {
    private MyMediaPlayer myMediaPlayer;
    private Button btnPreSong;
    private Button btnStart;
    private Button btnPause;
    private Button btnNextSong;

    public ControlCenter(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.buttons_control_center, this);

        btnPreSong = findViewById(R.id.btn_preSong);
        btnPreSong.setOnClickListener(this);
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        btnPause = findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);
        btnNextSong = findViewById(R.id.btn_nextSong);
        btnNextSong.setOnClickListener(this);
    }

    public void setMusic(SongFile songFile){
        myMediaPlayer = new MyMediaPlayer(songFile);
    }

    public MyMediaPlayer getMyMediaPlayer() {
        return myMediaPlayer;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_preSong:
                myMediaPlayer.pre();
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
                myMediaPlayer.next();
                break;
            default:
                break;
        }
    }

    public Button getBtnStart() {
        return btnStart;
    }
}
