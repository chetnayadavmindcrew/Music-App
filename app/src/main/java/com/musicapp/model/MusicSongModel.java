package com.musicapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_music_song")
public class MusicSongModel implements Serializable {

    @ColumnInfo(name = "album_name")
    private String albumName;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "song_name")
    private String song_name = "1";

    public MusicSongModel(String albumName, String song_name) {
        this.albumName = albumName;
        this.song_name = song_name;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }
}
