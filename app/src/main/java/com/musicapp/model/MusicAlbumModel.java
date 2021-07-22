package com.musicapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_music_album")
public class MusicAlbumModel implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "album_name")
    private String albumName = "1";

    @ColumnInfo(name = "album_price")
    private String albumPrice;

    public MusicAlbumModel(String albumName, String albumPrice) {
        this.albumName = albumName;
        this.albumPrice = albumPrice;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(String albumPrice) {
        this.albumPrice = albumPrice;
    }
}
