package com.wayne.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Wayne
 */
public class MyMediaPlayer {
    MediaPlayer mediaPlayer;
    SongFile nowSongFile;

    public MyMediaPlayer(SongFile songFile) {
        nowSongFile = songFile;
        mediaPlayer = MediaPlayer.create(MyApplication.context, nowSongFile.getRid());
        mediaPlayer.setOnCompletionListener(new CompletionListener());
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

    public void next(){
        try {
            SongFile nextSongFile = MainActivityViewModel.getSongList().get(nowSongFile.getId()+1);
            mediaPlayer.stop();
            mediaPlayer.release();
            Log.d("Wayne", "下一首的id为"+ nextSongFile.getId());
            mediaPlayer = MediaPlayer.create(MyApplication.context, nextSongFile.getRid());
            mediaPlayer.start();
            nowSongFile = nextSongFile;
            //发生广播，显示歌曲信息
            BroadCastSender.create("SongStart",nowSongFile.getId()).sendBroadCast();
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(MyApplication.context, "没有下一首了哦",Toast.LENGTH_SHORT).show();
        }
    }

    public void pre(){
        try {
            SongFile preSongFile = MainActivityViewModel.getSongList().get(nowSongFile.getId() - 1);
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(MyApplication.context, preSongFile.getRid());
            Log.d("Wayne", "上一首的id为" + (nowSongFile.getId() - 1));
            mediaPlayer.start();
            nowSongFile = preSongFile;
            //发生广播，显示歌曲信息
            BroadCastSender.create("SongStart",nowSongFile.getId()).sendBroadCast();
        }
        catch (IndexOutOfBoundsException e){
            Toast.makeText(MyApplication.context, "没有上一首了哦",Toast.LENGTH_SHORT).show();
        }
    }

    private class CompletionListener implements MediaPlayer.OnCompletionListener {
        //在播放完成时调用，用以实现自动播放下一首的功能
        @Override
        public void onCompletion(MediaPlayer mp) {
            next();
        }

    }
}
