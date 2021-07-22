package com.musicapp.view.album;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.musicapp.R;
import com.musicapp.model.MusicAlbumModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicAlbumAdapter extends RecyclerView.Adapter<MusicAlbumAdapter.MyViewHolder> {
    List<MusicAlbumModel> musicAlbumModelList;
    CallBackMusicAlbum callBackMusicAlbum;
    public MusicAlbumAdapter(List<MusicAlbumModel> musicAlbumModelList, CallBackMusicAlbum callBackMusicAlbum) {
        this.musicAlbumModelList = musicAlbumModelList;
        this.callBackMusicAlbum = callBackMusicAlbum;
    }

    @NonNull
    @Override
    public MusicAlbumAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_album_item, parent, false);
        return new MusicAlbumAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAlbumAdapter.MyViewHolder holder, int position) {
        MusicAlbumModel musicAlbumModel = musicAlbumModelList.get(position);
        holder.album_name.setText(musicAlbumModel.getAlbumName());
        holder.album_price.setText(musicAlbumModel.getAlbumPrice());
        holder.album_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackMusicAlbum.albumClick(musicAlbumModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicAlbumModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_name)
        AppCompatTextView album_name;

        @BindView(R.id.album_price)
        AppCompatTextView album_price;

        @BindView(R.id.album_detail)
        AppCompatImageView album_detail;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
