package com.musicapp.view.song;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.musicapp.R;
import com.musicapp.model.MusicSongModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicSongAdapter extends RecyclerView.Adapter<MusicSongAdapter.MyViewHolder> {
    List<MusicSongModel> musicSongModelList;
    CallBackMusicSong callBackMusicSong;
    public MusicSongAdapter(List<MusicSongModel> musicSongModelList, CallBackMusicSong callBackMusicSong) {
        this.musicSongModelList = musicSongModelList;
        this.callBackMusicSong = callBackMusicSong;
    }

    @NonNull
    @Override
    public MusicSongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_song_item, parent, false);
        return new MusicSongAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicSongAdapter.MyViewHolder holder, int position) {
        MusicSongModel musicSongModel = musicSongModelList.get(position);
        holder.song_name.setText(musicSongModel.getSong_name());
        holder.song_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackMusicSong.songClick(musicSongModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicSongModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.song_name)
        AppCompatTextView song_name;

        @BindView(R.id.song_detail)
        AppCompatImageView song_detail;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
