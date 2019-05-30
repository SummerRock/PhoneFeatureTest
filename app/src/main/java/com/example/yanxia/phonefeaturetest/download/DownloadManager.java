package com.example.yanxia.phonefeaturetest.download;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
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

    private Handler handler;

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

        handler = new Handler(Looper.getMainLooper());

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
            Runnable runnable = () -> {
                Request request = new Request.Builder().url(downloadItem.getDownloadUrl()).build();
                Call call = client.newCall(request);
                final long startTime = SystemClock.elapsedRealtime();
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse start response: " + response.message());
                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len;
                        FileOutputStream fos = null;

                        //储存下载文件的目录
                        File dir = new File(downloadItem.getDownloadFileDirectoryPath());
                        if (!dir.exists()) {
                            if (!dir.mkdirs()) {
                                for (OnDownloadUpdateListener onDownloadUpdateListener : getAllListeners(downloadItem)) {
                                    onDownloadUpdateListener.onDownloadFailure(downloadItem, new RuntimeException("mkdirs failed!"));
                                }
                                return;
                            }
                        }
                        File file = new File(dir, downloadItem.getDownloadFileName());

                        try {
                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            fos = new FileOutputStream(file);
                            long sum = 0;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                sum += len;
                                float progress = (sum * 1.0f / total * 100);
                                //下载中更新进度条
                                Log.d(TAG, "update progress: " + String.valueOf(progress));
                                for (OnDownloadUpdateListener onDownloadUpdateListener : getAllListeners(downloadItem)) {
                                    onDownloadUpdateListener.onDownloadProgressUpdate(downloadItem, progress);
                                }
                            }
                            fos.flush();
                            //下载完成
                            for (OnDownloadUpdateListener onDownloadUpdateListener : getAllListeners(downloadItem)) {
                                onDownloadUpdateListener.onDownloadSuccess(downloadItem, 0);
                            }
                        } catch (Exception e) {
                            for (OnDownloadUpdateListener onDownloadUpdateListener : getAllListeners(downloadItem)) {
                                onDownloadUpdateListener.onDownloadFailure(downloadItem, e);
                            }
                        } finally {
                            try {
                                if (is != null) {
                                    is.close();
                                }
                                if (fos != null) {
                                    fos.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        for (OnDownloadUpdateListener onDownloadUpdateListener : getAllListeners(downloadItem)) {
                            onDownloadUpdateListener.onDownloadFailure(downloadItem, new RuntimeException("response is failed!"));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            poolExecutor.execute(runnable);
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
