package com.musicapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.musicapp.R;
import com.musicapp.view.album.AlbumActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
            finish();
        },1000);
    }
    
    public void toast(){
    Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }
}
