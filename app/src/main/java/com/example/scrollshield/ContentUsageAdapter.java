package com.example.scrollshield;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContentUsageAdapter extends RecyclerView.Adapter<ContentUsageAdapter.ContentUsageViewHolder> {

    List<ContentUsageData> contentUsageDataList;

    public ContentUsageAdapter(List<ContentUsageData> contentUsageDataList) {
        this.contentUsageDataList = contentUsageDataList;
    }

    @NonNull
    @Override
    public ContentUsageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_usage, parent, false);
        return new ContentUsageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentUsageViewHolder holder, int position) {
        holder.setContentUsageData(contentUsageDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return contentUsageDataList.size();
    }

    public class ContentUsageViewHolder extends  RecyclerView.ViewHolder {

        ImageView contentUsageIcon;
        TextView contentUsageCategory, contentUsageTime;

        public ContentUsageViewHolder(@NonNull View itemView) {
            super(itemView);

            contentUsageIcon = itemView.findViewById(R.id.content_usage_icon);
            contentUsageCategory = itemView.findViewById(R.id.content_usage_category);
            contentUsageTime = itemView.findViewById(R.id.content_usage_time);
        }

        public void setContentUsageData(ContentUsageData contentUsageData) {
            contentUsageIcon.setImageResource(contentUsageData.getIconSrc());
            contentUsageCategory.setText(contentUsageData.getSubjectName());
            contentUsageTime.setText(contentUsageData.getDuration());
        }
    }

}
