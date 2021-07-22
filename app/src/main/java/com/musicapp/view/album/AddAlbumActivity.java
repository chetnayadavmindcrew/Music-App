package com.musicapp.view.album;

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

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAlbumActivity extends AppCompatActivity {

    @BindView(R.id.header_text)
    AppCompatTextView header_text;

    @BindView(R.id.submit_album)
    AppCompatButton submit_album;

    @BindView(R.id.album_name)
    AppCompatEditText album_name;

    @BindView(R.id.album_price)
    AppCompatEditText album_price;

    MusicAlbumDao musicAlbumDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album);
        ButterKnife.bind(this);
        header_text.setText(R.string.add_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        musicAlbumDao = MusicAlbumDatabase.getDataBase(getApplicationContext()).musicAlbumDao();
    }

    @OnClick(R.id.submit_album)
    public void click() {
        if (TextUtils.isEmpty(album_name.getText().toString()))
            Toast.makeText(this, "Please enter album name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(album_price.getText().toString()))
            Toast.makeText(this, "Please enter album price", Toast.LENGTH_SHORT).show();
        else {
            musicAlbumDao.insertAlbum(new MusicAlbumModel(album_name.getText().toString(), album_price.getText().toString()));
            finish();
        }

    }
}