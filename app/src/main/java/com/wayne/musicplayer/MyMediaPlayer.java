package com.wayne.musicplayer;

import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * @author Wayne
 */
public class MyMediaPlayer {
    MediaPlayer mediaPlayer;

    public MyMediaPlayer(int resInt) {
        mediaPlayer = MediaPlayer.create(MyApplication.context, resInt);
    }

    public void start(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }else {
            Toast.makeText(MyApplication.context, "当前已经在播放了哦",Toast.LENGTH_SHORT).show();
        }
    }

    public void pause(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {
            Toast.makeText(MyApplication.context, "当前没有在播放，无法暂停哦",Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
