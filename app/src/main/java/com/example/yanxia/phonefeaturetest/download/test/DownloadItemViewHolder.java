package com.example.yanxia.phonefeaturetest.download.test;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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

    public void setDownloadStart() {
        progressBar.setProgress(0);
        button.setText("开始下载");
    }

    public void setDownloading(float progress) {
        progressBar.setProgress((int) progress);
        button.setText("正在下载");
    }

    public void setDownloadSuccess() {
        progressBar.setProgress(100);
        button.setText("下载成功");
    }

    public void setDownloadFail() {
        progressBar.setProgress(0);
        button.setText("下载失败");
    }
}
