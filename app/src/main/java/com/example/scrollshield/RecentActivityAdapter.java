package com.example.scrollshield;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.RecentActivityViewHolder> {

    List<RecentActivityData> recentActivityDataList;

    public RecentActivityAdapter(List<RecentActivityData> recentActivityDataList) {
        this.recentActivityDataList = recentActivityDataList;
    }

    @NonNull
    @Override
    public RecentActivityAdapter.RecentActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_activity, parent, false);
        return new RecentActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentActivityAdapter.RecentActivityViewHolder holder, int position) {
        holder.setRecentActivityData(recentActivityDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return recentActivityDataList.size();
    }

    public class RecentActivityViewHolder extends RecyclerView.ViewHolder {

        ImageView recentActivityIcon;
        TextView recentActivityName, recentActivityTime;

        public RecentActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            recentActivityIcon = itemView.findViewById(R.id.recent_activity_icon);
            recentActivityName = itemView.findViewById(R.id.recent_activity_name);
            recentActivityTime = itemView.findViewById(R.id.recent_activity_time);
        }

        public void setRecentActivityData(RecentActivityData recentActivityData) {

            recentActivityIcon.setImageResource(recentActivityData.getIconSrc());
            recentActivityName.setText(recentActivityData.getRecentActivityName());
            recentActivityTime.setText(recentActivityData.getRecentTimestamp());

        }

    }

}
