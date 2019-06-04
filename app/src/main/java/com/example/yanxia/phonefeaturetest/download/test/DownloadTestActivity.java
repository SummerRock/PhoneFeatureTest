package com.example.yanxia.phonefeaturetest.download.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.download.DownloadManager;
import com.example.yanxia.phonefeaturetest.download.Downloadable;
import com.example.yanxia.phonefeaturetest.download.OnDownloadUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class DownloadTestActivity extends AppCompatActivity implements OnDownloadUpdateListener, DownloadItemAdapter.OnItemClickListener {

    private static final String LOCALE_FILE_DIR_NAME = "testBigFile";

    private LinearLayoutManager linearLayoutManager;
    private DownloadItemAdapter downloadItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_test);
        RecyclerView recyclerView = findViewById(R.id.download_demo_rv);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        downloadItemAdapter = new DownloadItemAdapter(getDemoList(), this);
        recyclerView.setAdapter(downloadItemAdapter);
    }

    private List<DownloadDemoItem> getDemoList() {
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
    public void onDownloadStart(Downloadable downloadItem) {

    }

    @Override
    public void onDownloadProgressUpdate(Downloadable downloadItem, float percent) {

    }

    @Override
    public void onDownloadSuccess(Downloadable downloadItem, long downloadTime) {

    }

    @Override
    public void onDownloadFailure(Downloadable downloadItem, @NonNull Exception e) {

    }

    @Override
    public void onItemClick(DownloadDemoItem item) {
        DownloadManager.getInstance().downloadItem(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
