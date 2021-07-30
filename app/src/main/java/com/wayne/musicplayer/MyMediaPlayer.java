package com.wayne.musicplayer;

import android.media.MediaPlayer;

public class MyMediaPlayer {
    MediaPlayer mediaPlayer;

    public MyMediaPlayer(int resInt) {
        mediaPlayer = MediaPlayer.create(MyApplication.context, resInt);
    }

    public void start(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public void pause(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void stop(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
