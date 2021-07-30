package com.wayne.musicplayer;

/**
 * @author Wayne
 */
public class SongFile {
    String musicName;
    String singerName;

    public SongFile(String musicName, String singerName) {
        this.musicName = musicName;
        this.singerName = singerName;
    }

    public String getSongName() {
        return musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSongName(String songName) {
        this.musicName = musicName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
