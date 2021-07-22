package com.musicapp.view.song;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.musicapp.R;
import com.musicapp.room.MusicAlbumDao;
import com.musicapp.room.MusicAlbumDatabase;
import com.musicapp.model.MusicAlbumModel;
import com.musicapp.model.MusicSongModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSongActivity extends AppCompatActivity {

    @BindView(R.id.header_text)
    AppCompatTextView header_text;

    @BindView(R.id.submit_song)
    AppCompatButton submit_song;

    @BindView(R.id.song_name)
    AppCompatEditText song_name;
    MusicAlbumDao musicAlbumDao;
    MusicAlbumModel musicAlbumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        ButterKnife.bind(this);
        header_text.setText(R.string.add_song);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        musicAlbumDao = MusicAlbumDatabase.getDataBase(getApplicationContext()).musicAlbumDao();

        if (getIntent().getExtras() != null)
            musicAlbumModel = (MusicAlbumModel) getIntent().getExtras().get("ALBUM");
    }

    @OnClick(R.id.submit_song)
    public void click() {
        if (TextUtils.isEmpty(song_name.getText().toString()))
            Toast.makeText(this, "Please enter album name", Toast.LENGTH_SHORT).show();
        else {
            musicAlbumDao.insertSong(new MusicSongModel(musicAlbumModel.getAlbumName(), song_name.getText().toString()));
            finish();
        }
    }
}