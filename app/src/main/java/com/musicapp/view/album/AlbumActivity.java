package com.musicapp.view.album;

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
import com.musicapp.view.song.SongActivity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumActivity extends AppCompatActivity implements CallBackMusicAlbum {

    @BindView(R.id.header_text)
    AppCompatTextView header_text;

    @BindView(R.id.add)
    AppCompatTextView add;

    @BindView(R.id.album_rv)
    RecyclerView album_rv;

    List<MusicAlbumModel> musicAlbumModelList;
    MusicAlbumDao musicAlbumDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);
        header_text.setText(R.string.music_album);
        add.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        musicAlbumDao = MusicAlbumDatabase.getDataBase(getApplicationContext()).musicAlbumDao();
        album_rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @OnClick(R.id.add)
    public void click() {
        startActivity(new Intent(this, AddAlbumActivity.class));
    }

    @Override
    public void albumClick(MusicAlbumModel musicAlbumModel) {
        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra("ALBUM", (Serializable) musicAlbumModel);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetMusicAlbum().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class GetMusicAlbum extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                musicAlbumModelList = musicAlbumDao.getMusicAlbum();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (musicAlbumModelList != null && musicAlbumModelList.size() > 0) {
                MusicAlbumAdapter musicAlbumAdapter = new MusicAlbumAdapter(musicAlbumModelList, AlbumActivity.this);
                album_rv.setAdapter(musicAlbumAdapter);
            }
        }
    }
}