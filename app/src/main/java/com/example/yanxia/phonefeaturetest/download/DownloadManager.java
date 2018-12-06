package com.example.yanxia.phonefeaturetest.download;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadManager {
    private static final String TAG = "DownloadManager";
    private volatile static DownloadManager instance;

    private ThreadPoolExecutor poolExecutor;
    private OkHttpClient client;

    private Map<Downloadable, Call> downloadingItems;
    private Map<Downloadable, List<OnDownloadUpdateListener>> downloadItemListenerMap;
    private List<OnDownloadUpdateListener> globalListeners;

    public static DownloadManager getInstance() {
        if (instance == null) {
            synchronized (DownloadManager.class) {
                if (instance == null) {
                    instance = new DownloadManager();
                }
            }
        }
        return instance;
    }

    private DownloadManager() {
        poolExecutor = new ThreadPoolExecutor(3, 5,
                1, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(128));

        client = new OkHttpClient();

        downloadingItems = new HashMap<>();
        downloadItemListenerMap = new HashMap<>();
        globalListeners = new ArrayList<>();
    }

    public void downloadItem(@NonNull Downloadable downloadItem) {
        Log.d(TAG, "downloadItem url: " + downloadItem.getDownloadUrl());
        if (downloadItem.isDownloaded()) {
            return;
        }

        if (!downloadingItems.keySet().contains(downloadItem)) {
            final long startTime = SystemClock.elapsedRealtime();
            Request request = new Request.Builder().url(downloadItem.getDownloadUrl()).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {

                }
            });
        }
    }

    private List<OnDownloadUpdateListener> getAllListeners(Downloadable downloadItem) {
        List<OnDownloadUpdateListener> downloadItemListeners = downloadItemListenerMap.get(downloadItem);
        ArrayList<OnDownloadUpdateListener> copiedListeners = new ArrayList<>();
        addListValid(downloadItemListeners, copiedListeners);
        addListValid(globalListeners, copiedListeners);
        return copiedListeners;
    }

    private void addListValid(List<OnDownloadUpdateListener> toAdd, List<OnDownloadUpdateListener> dst) {
        if (toAdd == null || dst == null) {
            return;
        }
        for (OnDownloadUpdateListener listener : toAdd) {
            if (listener != null) {
                dst.add(listener);
            }
        }
    }
}
