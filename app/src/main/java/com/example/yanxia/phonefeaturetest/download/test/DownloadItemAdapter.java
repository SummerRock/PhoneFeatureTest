package com.example.yanxia.phonefeaturetest.download.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanxia.phonefeaturetest.R;

import java.util.List;

/**
 * @author yanxia
 * @date 2017/2/10
 */

public class DownloadItemAdapter extends RecyclerView.Adapter<DownloadItemViewHolder> {
    private List<DownloadDemoItem> downloadDemoItemList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(DownloadDemoItem item);
    }

    DownloadItemAdapter(@NonNull List<DownloadDemoItem> testList, @Nullable OnItemClickListener listener) {
        downloadDemoItemList = testList;
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public DownloadItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.download_demo_item, parent, false);
        DownloadItemViewHolder holder = new DownloadItemViewHolder(view);
        holder.button.setOnClickListener(v -> {
            if (itemClickListener != null) {
                final DownloadDemoItem testItem = downloadDemoItemList.get(holder.getAdapterPosition());
                itemClickListener.onItemClick(testItem);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadItemViewHolder holder, int position) {
        final DownloadDemoItem downloadDemoItem = downloadDemoItemList.get(position);
        holder.name.setText(downloadDemoItem.getDownloadName());
        holder.progressBar.setProgress(0);
        if (downloadDemoItem.isDownloaded()) {
            holder.button.setText("已下载");
        } else {
            holder.button.setText("下载");
        }
    }

    @Override
    public int getItemCount() {
        return downloadDemoItemList.size();
    }
}
