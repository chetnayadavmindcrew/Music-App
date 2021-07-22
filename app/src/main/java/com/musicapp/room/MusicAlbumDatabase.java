package com.musicapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.musicapp.model.MusicAlbumModel;
import com.musicapp.model.MusicSongModel;

@Database(entities = {MusicAlbumModel.class, MusicSongModel.class}, version = 1, exportSchema = false)
public abstract class MusicAlbumDatabase extends RoomDatabase {

    public static MusicAlbumDatabase getDataBase(Context context) {
        return Room.databaseBuilder(context, MusicAlbumDatabase.class, "musicapp-db.db").allowMainThreadQueries().build();
    }

    public abstract MusicAlbumDao musicAlbumDao();
}
