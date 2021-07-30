package com.wayne.musicplayer;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    public static List<SongFile> musicFileArrayList = new ArrayList<>();

    public static List<SongFile> getSongList() {
        return musicFileArrayList;
    }

    public static void addMusicFile(SongFile newSongFile) {
        musicFileArrayList.add(newSongFile);
    }
}
