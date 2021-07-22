package com.musicapp.view.song;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.musicapp.R;
import com.musicapp.room.MusicAlbumDao;
import com.musicapp.room.MusicAlbumDatabase;
import com.musicapp.model.MusicAlbumModel;
import com.musicapp.model.MusicSongModel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SongActivity extends AppCompatActivity implements CallBackMusicSong {

    @BindView(R.id.header_text)
    AppCompatTextView header_text;

    @BindView(R.id.add)
    AppCompatTextView add;

    List<MusicSongModel> musicSongModelList;
    MusicAlbumDao musicAlbumDao;

    @BindView(R.id.song_rv)
    RecyclerView song_rv;

    MusicAlbumModel musicAlbumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        ButterKnife.bind(this);
        add.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        musicAlbumDao = MusicAlbumDatabase.getDataBase(getApplicationContext()).musicAlbumDao();
        song_rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        if (getIntent().getExtras() != null)
            musicAlbumModel = (MusicAlbumModel) getIntent().getExtras().get("ALBUM");

        header_text.setText(musicAlbumModel.getAlbumName());
    }

    @OnClick(R.id.add)
    public void click() {
        Intent intent = new Intent(this, AddSongActivity.class);
        intent.putExtra("ALBUM", (Serializable) musicAlbumModel);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetMusicSong().execute();
    }

    @Override
    public void songClick(MusicSongModel musicSongModel) {
        
    }

    @SuppressLint("StaticFieldLeak")
    private class GetMusicSong extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                musicSongModelList = musicAlbumDao.getMusicSong(musicAlbumModel.getAlbumName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (musicSongModelList != null && musicSongModelList.size() > 0) {
                MusicSongAdapter musicSongAdapters = new MusicSongAdapter(musicSongModelList, SongActivity.this);
                song_rv.setAdapter(musicSongAdapters);
            }
        }
    }
}