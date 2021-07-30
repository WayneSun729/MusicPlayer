package com.wayne.musicplayer;

/**
 * @author Wayne
 */
public class SongFile {
    String musicName;
    String singerName;
    int id;
    int rid;

    public SongFile(String musicName, String singerName, int rid) {
        this.musicName = musicName;
        this.singerName = singerName;
        this.rid = rid;
        id = MainActivityViewModel.getSongList().size();
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

    public int getId() {
        return id;
    }

    public int getRid() {
        return rid;
    }
}
