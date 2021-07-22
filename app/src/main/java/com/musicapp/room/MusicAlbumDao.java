package com.musicapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.musicapp.model.MusicAlbumModel;
import com.musicapp.model.MusicSongModel;

import java.util.List;

@Dao
public interface MusicAlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbum(MusicAlbumModel data);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSong(MusicSongModel data);

    @Query("SELECT * FROM tb_music_album")
    List<MusicAlbumModel> getMusicAlbum();

    @Query("SELECT * FROM tb_music_song WHERE album_name = :album_name")
    List<MusicSongModel> getMusicSong(String album_name);
}
