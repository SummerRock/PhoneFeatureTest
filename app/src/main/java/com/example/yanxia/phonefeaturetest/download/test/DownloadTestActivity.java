package com.example.yanxia.phonefeaturetest.download.test;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.download.DownloadManager;
import com.example.yanxia.phonefeaturetest.download.Downloadable;
import com.example.yanxia.phonefeaturetest.download.OnDownloadUpdateListener;
import com.example.yanxia.phonefeaturetest.utils.DisplayUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadTestActivity extends AppCompatActivity implements OnDownloadUpdateListener, DownloadItemAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<DownloadDemoItem> downloadDemoItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_test);
        recyclerView = findViewById(R.id.download_demo_rv);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        downloadDemoItemList = getDemoList();
        DownloadItemAdapter downloadItemAdapter = new DownloadItemAdapter(downloadDemoItemList, this);
        recyclerView.setAdapter(downloadItemAdapter);
        recyclerView.addItemDecoration(new CustomItemDecoration(DisplayUtils.dpToPx(10)));

        for (DownloadDemoItem downloadDemoItem : downloadDemoItemList) {
            DownloadManager.getInstance().addDownloadListener(downloadDemoItem, this);
        }
    }

    private List<DownloadDemoItem> getDemoList() {
        String LOCALE_FILE_DIR_NAME = getFilesDir() + File.separator + "testBigFile";
        List<DownloadDemoItem> downloadDemoItemList = new ArrayList<>();
        downloadDemoItemList.add(new DownloadDemoItem("tokyo", "demo", "http://speedtest.tokyo.linode.com/100MB-tokyo.bin", LOCALE_FILE_DIR_NAME, "demo_file_tokyo.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("london", "demo", "http://speedtest.london.linode.com/100MB-london.bin", LOCALE_FILE_DIR_NAME, "demo_file_london.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("newark", "demo", "http://speedtest.newark.linode.com/100MB-newark.bin", LOCALE_FILE_DIR_NAME, "demo_file_newark.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("atlanta", "demo", "http://speedtest.atlanta.linode.com/100MB-atlanta.bin", LOCALE_FILE_DIR_NAME, "demo_file_atlanta.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("dallas", "demo", "http://speedtest.dallas.linode.com/100MB-dallas.bin", LOCALE_FILE_DIR_NAME, "demo_file_dallas.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("fremont", "demo", "http://speedtest.fremont.linode.com/100MB-fremont.bin", LOCALE_FILE_DIR_NAME, "demo_file_fremont.bin"));
        downloadDemoItemList.add(new DownloadDemoItem("cachefly", "demo", "http://cachefly.cachefly.net/100mb.test", LOCALE_FILE_DIR_NAME, "demo_file_cachefly.test"));
        downloadDemoItemList.add(new DownloadDemoItem("direct", "demo", "http://direct.netcloud.com/100MB.BIN", LOCALE_FILE_DIR_NAME, "demo_file_direct.bin"));
        return downloadDemoItemList;
    }

    @Override
    public void onDownloadStart(Downloadable downloadable) {
        int startPos = linearLayoutManager.findFirstVisibleItemPosition();
        int endPos = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = startPos; i < endPos; i++) {
            DownloadDemoItem downloadDemoItem = downloadDemoItemList.get(i);
            if (TextUtils.equals(downloadDemoItem.getDownloadName(), downloadable.getDownloadName())
                    && TextUtils.equals(downloadDemoItem.getDownloadType(), downloadable.getDownloadType())) {
                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                if (holder instanceof DownloadItemViewHolder) {
                    ((DownloadItemViewHolder) holder).setDownloadStart();
                }
                break;
            }
        }
    }

    @Override
    public void onDownloadProgressUpdate(Downloadable downloadable, float percent) {
        int startPos = linearLayoutManager.findFirstVisibleItemPosition();
        int endPos = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = startPos; i < endPos; i++) {
            DownloadDemoItem downloadDemoItem = downloadDemoItemList.get(i);
            if (TextUtils.equals(downloadDemoItem.getDownloadName(), downloadable.getDownloadName())
                    && TextUtils.equals(downloadDemoItem.getDownloadType(), downloadable.getDownloadType())) {
                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                if (holder instanceof DownloadItemViewHolder) {
                    ((DownloadItemViewHolder) holder).setDownloading(percent);
                }
                break;
            }
        }
    }

    @Override
    public void onDownloadSuccess(Downloadable downloadable, long downloadTime) {
        int startPos = linearLayoutManager.findFirstVisibleItemPosition();
        int endPos = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = startPos; i < endPos; i++) {
            DownloadDemoItem downloadDemoItem = downloadDemoItemList.get(i);
            if (TextUtils.equals(downloadDemoItem.getDownloadName(), downloadable.getDownloadName())
                    && TextUtils.equals(downloadDemoItem.getDownloadType(), downloadable.getDownloadType())) {
                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                if (holder instanceof DownloadItemViewHolder) {
                    ((DownloadItemViewHolder) holder).setDownloadSuccess();
                }
                break;
            }
        }
    }

    @Override
    public void onDownloadFailure(Downloadable downloadable, @NonNull Exception e) {
        int startPos = linearLayoutManager.findFirstVisibleItemPosition();
        int endPos = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = startPos; i < endPos; i++) {
            DownloadDemoItem downloadDemoItem = downloadDemoItemList.get(i);
            if (TextUtils.equals(downloadDemoItem.getDownloadName(), downloadable.getDownloadName())
                    && TextUtils.equals(downloadDemoItem.getDownloadType(), downloadable.getDownloadType())) {
                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                if (holder instanceof DownloadItemViewHolder) {
                    ((DownloadItemViewHolder) holder).setDownloadFail();
                }
                break;
            }
        }
    }

    @Override
    public void onItemClick(DownloadDemoItem item) {
        DownloadManager.getInstance().downloadItem(item);
    }

    @Override
    protected void onDestroy() {
        for (DownloadDemoItem downloadDemoItem : downloadDemoItemList) {
            DownloadManager.getInstance().removeDownloadListener(downloadDemoItem, this);
        }
        super.onDestroy();
    }

    private class CustomItemDecoration extends RecyclerView.ItemDecoration {

        private int gap;

        CustomItemDecoration(int gap) {
            this.gap = gap;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            // int position = parent.getChildAdapterPosition(view);
            outRect.set(gap, gap, gap, gap);
        }
    }
}
