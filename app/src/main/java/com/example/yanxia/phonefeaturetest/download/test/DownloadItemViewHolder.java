package com.example.yanxia.phonefeaturetest.download.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yanxia.phonefeaturetest.R;

public class DownloadItemViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ProgressBar progressBar;
    public Button button;

    public DownloadItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.download_item_name_tv);
        progressBar = itemView.findViewById(R.id.download_item_progress_bar);
        button = itemView.findViewById(R.id.download_item_button);
    }
}
