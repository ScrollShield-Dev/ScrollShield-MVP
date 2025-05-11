package com.example.scrollshield;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShortsAdapter extends RecyclerView.Adapter<ShortsAdapter.ShortsViewHolder> {

    List<ShortsData> shortsDataList;

    public ShortsAdapter(List<ShortsData> shortsDataList) {
        this.shortsDataList = shortsDataList;
    }

    @NonNull
    @Override
    public ShortsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shorts, parent, false);
        return new ShortsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortsViewHolder holder, int position) {
        holder.setShortsListData(shortsDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return shortsDataList.size();
    }

    public class ShortsViewHolder extends RecyclerView.ViewHolder {

        VideoView shortsView;
        TextView shortsUser, shortsTitle;
        ImageView shortsImage;

        public ShortsViewHolder(@NonNull View itemView) {
            super(itemView);

            shortsView = itemView.findViewById(R.id.videoView);
            shortsUser = itemView.findViewById(R.id.shortsName);
            shortsTitle = itemView.findViewById(R.id.shortsTitle);
            shortsImage = itemView.findViewById(R.id.shortsImage);
        }

        public void setShortsListData(ShortsData shortsData) {
            shortsView.setVideoURI(shortsData.getShortsPath());
            shortsImage.setImageResource(shortsData.getShortsImg());
            shortsUser.setText(shortsData.getShortsUser());
            shortsTitle.setText(shortsData.getShortsTitle());

            shortsView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    float shortRatio = mediaPlayer.getVideoWidth() / (float) mediaPlayer.getVideoHeight();
                    float screenRatio = shortsView.getWidth() / (float) shortsView.getHeight();

                    float shortScale = shortRatio / screenRatio;
                    if (shortScale >= 1f) {
                        shortsView.setScaleX(shortScale);
                    } else {
                        shortsView.setScaleY(1f/shortScale);
                    }
                }
            });

            shortsView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }

    }

}
