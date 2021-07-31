package com.wayne.musicplayer;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wayne
 */
public class MainActivityViewModel extends ViewModel {

    public static final int FLAG_HISTORY = 1;

    public static final int FLAG_SONGSHEET = 0;

    private static List<SongFile> musicFileArrayList = new ArrayList<>();

    public static List<SongFile> getSongList() {
        return musicFileArrayList;
    }

    public static void addMusicFile(SongFile newSongFile) {
        musicFileArrayList.add(newSongFile);
    }

    private static List<SongFile> historyArrayList = new ArrayList<>();

    public static List<SongFile> getHistoryArrayList() {
        return historyArrayList;
    }

    public static void addMusicFileToHistory(SongFile newSongFile) {
        historyArrayList.add(newSongFile);
    }

//    public static ControlCenter controlCenter = ;

//    public static ControlCenter getControlCenter() {
//        return controlCenter;
//    }
}
